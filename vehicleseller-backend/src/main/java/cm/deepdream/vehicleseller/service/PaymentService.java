package cm.deepdream.vehicleseller.service;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.mail.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cm.deepdream.vehicleseller.enums.StatusBill;
import cm.deepdream.vehicleseller.enums.StatusReservation;
import cm.deepdream.vehicleseller.model.Bill;
import cm.deepdream.vehicleseller.model.Customer;
import cm.deepdream.vehicleseller.model.Payment;
import cm.deepdream.vehicleseller.model.Reservation;
import cm.deepdream.vehicleseller.repository.BillRepository;
import cm.deepdream.vehicleseller.repository.PaymentRepository;
import cm.deepdream.vehicleseller.repository.ReservationRepository;
import cm.deepdream.vehicleseller.util.EmailSender;
import cm.deepdream.vehicleseller.util.PaymentReport;
import freemarker.template.TemplateException;
import lombok.extern.log4j.Log4j2;

@Transactional
@Service
@Log4j2
public class PaymentService {
	private PaymentRepository paymentRepository ;
	private BillRepository billRepository ;
	private ReservationRepository reservationRepository ;
	private PaymentReport paymentReport ;
	private EmailSender emailSender ;
	
	
	
	
	public PaymentService(PaymentRepository paymentRepository, BillRepository billRepository,
			ReservationRepository reservationRepository, PaymentReport paymentReport, EmailSender emailSender) {
		this.paymentRepository = paymentRepository;
		this.billRepository = billRepository;
		this.reservationRepository = reservationRepository;
		this.paymentReport = paymentReport;
		this.emailSender = emailSender;
	}


	public Payment create (Payment payment) {
		Payment savedPayment = paymentRepository.save(payment) ;
		
		Bill bill = savedPayment.getBill() ;
		bill.setStatus(StatusBill.REGLE.getLabel()); 
		billRepository.save(bill) ;
		
		Reservation reservation = bill.getReservation() ;
		reservation.setStatus(StatusReservation.CONFIRMED.getLabel());
		reservationRepository.save(reservation) ;
		
		try {
			
			Path path = paymentReport.generatePayment(savedPayment) ;
			
			Customer customer = reservation.getCustomer() ;
			
			final Map<String, Object> templateModel = new HashMap<>() ;
			templateModel.put("name", customer.getFirstName()+ " "+  customer.getLastName()) ;
			templateModel.put("refFacture", bill.getReference()) ;
			
			ExecutorService executorService = Executors.newSingleThreadExecutor() ;
			executorService.execute(() -> {
				try {
					emailSender.sendMessage(customer.getEmailAddress(), 
							"Confirmation de paiement",  "add-payment.html", path.toFile().getAbsoluteFile().getAbsolutePath(), 
							 "Paiement-"+bill.getReference()+".pdf", templateModel) ;
				}catch(MessagingException |TemplateException | IOException  ex ) {
					log.error(ex.getMessage(), ex) ;
				}
			});
			
		}catch(Exception exx) {
			log.error(exx.getMessage(), exx) ;
		}
		
		
		return savedPayment ;
	}
	
	
	public Payment modify (Payment payment) {
		return paymentRepository.save(payment) ;
	}
	
	
	public void delete(Payment payment) {
		paymentRepository.delete(payment);
	}
	
	
	public Optional<Payment> get (Long id) {
		return paymentRepository.findById(id)  ;
	}
	
	
	public List<Payment> getAll () {
		Iterable<Payment> payments  = paymentRepository.findAll() ;
		List<Payment> paymentsList = new ArrayList<>() ;
		payments.forEach(paymentsList::add) ;
		return paymentsList ;
	}
}

package cm.deepdream.vehicleseller.service;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.mail.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cm.deepdream.vehicleseller.enums.StatusBill;
import cm.deepdream.vehicleseller.enums.StatusReservation;
import cm.deepdream.vehicleseller.model.Bill;
import cm.deepdream.vehicleseller.model.Customer;
import cm.deepdream.vehicleseller.model.Reservation;
import cm.deepdream.vehicleseller.repository.BillRepository;
import cm.deepdream.vehicleseller.repository.ReservationRepository;
import cm.deepdream.vehicleseller.util.BillReport;
import cm.deepdream.vehicleseller.util.EmailSender;
import freemarker.template.TemplateException;
import lombok.extern.log4j.Log4j2;
@Transactional
@Service
@Log4j2
public class BillService {
	private BillRepository billRepository ;
	private ReservationRepository reservationRepository ;
	private BillReport billReport ;
	private EmailSender emailSender ;
	
	public BillService(BillRepository billRepository, ReservationRepository reservationRepository,
			BillReport billReport, EmailSender emailSender) {
		this.billRepository = billRepository;
		this.reservationRepository = reservationRepository;
		this.billReport = billReport;
		this.emailSender = emailSender;
	}


	public Bill create (Bill bill) {
		bill.setBillDate(LocalDate.now()) ;
	    bill.setStatus(StatusBill.NON_REGLE.getLabel()) ;
		Bill savedBill = billRepository.save(bill) ;
		
		Reservation reservation = savedBill.getReservation() ;
		reservation.setStatus(StatusReservation.HANDLED.getLabel());
		reservationRepository.save(reservation) ;
		
		Customer customer = savedBill.getReservation().getCustomer() ;
		
		final Map<String, Object> templateModel = new HashMap<>() ;
		templateModel.put("name", customer.getFirstName()+ " "+  customer.getLastName()) ;
		
		try {
			
			Path path = billReport.generateBill(savedBill) ;
			
			ExecutorService executorService = Executors.newSingleThreadExecutor() ;
			executorService.execute(() -> {
				try {
					emailSender.sendMessage(customer.getEmailAddress(), 
							"Votre facture",  "add-bill.html", path.toFile().getAbsoluteFile().getAbsolutePath(), 
							 "Facture-"+bill.getReference()+".pdf", templateModel) ;
				}catch(MessagingException |TemplateException | IOException  ex ) {
					log.error(ex.getMessage(), ex) ;
				}
			});
			
		}catch(Exception exx) {
			log.error(exx.getMessage(), exx) ;
		}
		
		return savedBill ;
	}
	
	
	public Bill modify (Bill bill) {
		return billRepository.save(bill) ;
	}
	
	
	public Bill cancel (Bill bill) {
		bill.setStatus(StatusBill.ANNULE.getLabel());
		return billRepository.save(bill) ;
	}
	
	
	public void delete (Bill bill) {
		billRepository.delete(bill);
	}
	
	
	public Bill get (Long id) {
		return billRepository.findById(id).orElseThrow(NullPointerException::new) ;
	}
	
	
	public List<Bill> getAll () {
		Iterable<Bill> bills  = billRepository.findAll() ;
		List<Bill> billsList = new ArrayList<>() ;
		bills.forEach(billsList::add) ;
		return billsList ;
	}
}

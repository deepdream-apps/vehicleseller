package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.deepdream.vehicleseller.model.Payment;
import cm.deepdream.vehicleseller.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository ;
	
	public Payment create (Payment payment) {
		Payment savedPayment  = paymentRepository.save(payment) ;
		return savedPayment ;
	}
	
	
	public Payment modify (Payment payment) {
		Payment savedPayment  = paymentRepository.save(payment) ;
		return savedPayment ;
	}
	
	
	public void delete(Payment payment) {
		paymentRepository.delete(payment);
	}
	
	
	public Payment get (Long id) {
		Payment savedPayment  = paymentRepository.findById(id).orElseGet(null) ;
		return savedPayment ;
	}
	
	
	public List<Payment> getAll () {
		Iterable<Payment> payments  = paymentRepository.findAll() ;
		List<Payment> paymentsList = new ArrayList<Payment>() ;
		payments.forEach(paymentsList::add) ;
		return paymentsList ;
	}
}

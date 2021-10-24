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
		return paymentRepository.save(payment) ;
	}
	
	
	public Payment modify (Payment payment) {
		return paymentRepository.save(payment) ;
	}
	
	
	public void delete(Payment payment) {
		paymentRepository.delete(payment);
	}
	
	
	public Payment get (Long id) {
		return paymentRepository.findById(id).orElseThrow(NullPointerException::new)  ;
	}
	
	
	public List<Payment> getAll () {
		Iterable<Payment> payments  = paymentRepository.findAll() ;
		List<Payment> paymentsList = new ArrayList<>() ;
		payments.forEach(paymentsList::add) ;
		return paymentsList ;
	}
}

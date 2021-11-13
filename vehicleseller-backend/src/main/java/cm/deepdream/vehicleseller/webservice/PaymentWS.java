package cm.deepdream.vehicleseller.webservice;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cm.deepdream.vehicleseller.model.Payment;
import cm.deepdream.vehicleseller.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentWS {
	private PaymentService paymentService ;
	
	
	public PaymentWS(PaymentService paymentService) {
		this.paymentService = paymentService;
	}


	@PostMapping("/add")
	public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {
	    Payment newPayment = paymentService.create(payment) ;
	    return ResponseEntity.ok(newPayment);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment) {
		Optional<Payment> optPayment = paymentService.get(payment.getId()) ;
		if(optPayment.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		Payment existingPayment = optPayment.get() ;
		existingPayment.setAmountWithTaxes(payment.getAmountWithTaxes()) ;
		existingPayment.setBill(payment.getBill()) ;
		existingPayment.setMode(payment.getMode()) ;
		existingPayment.setPaymentDate(payment.getPaymentDate()) ;
		existingPayment.setPhoneNumber(payment.getPhoneNumber()) ;
		existingPayment.setReference(payment.getReference()) ;
		Payment upadatedDriver = paymentService.create(existingPayment) ;
		return ResponseEntity.ok(upadatedDriver) ;
	}
	
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity deletePayment(@PathVariable("id") Long id)  {
		 Optional<Payment> optPayment = paymentService.get(id) ;
	    if(optPayment.isEmpty()) {
	    	return ResponseEntity.status(400).build();
	    }
	    paymentService.delete(optPayment.get()) ;
	    return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Payment> getPayment(@PathVariable("id") Long id)  {
	    Optional<Payment> optPayment = paymentService.get(id) ;
	    if(optPayment.isEmpty()) {
	    	return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(optPayment.get()) ;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Payment>> getAllPayments()  {
	    List<Payment> listPayments = paymentService.getAll() ;
	    return ResponseEntity.ok(listPayments) ;
	}
}

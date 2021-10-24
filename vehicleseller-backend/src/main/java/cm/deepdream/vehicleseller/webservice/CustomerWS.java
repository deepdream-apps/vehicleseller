package cm.deepdream.vehicleseller.webservice;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cm.deepdream.vehicleseller.model.Customer;
import cm.deepdream.vehicleseller.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerWS {
	@Autowired
	private CustomerService customerService ;
	
	
	@PostMapping("/add")
	public ResponseEntity<Customer> addCustomer(@RequestBody  Customer customer)  {
	    Customer newCustomer = customerService.create(customer) ;
	    return ResponseEntity.ok(newCustomer) ;
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer)  {
	    Optional<Customer> optCustomer = customerService.get(customer.getId()) ;
	    if(optCustomer.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    Customer existingCustomer = optCustomer.get() ;
	    Customer upadatedCustomer = customerService.modify(existingCustomer) ;
	    return ResponseEntity.ok(upadatedCustomer);
	}
	
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity deleteCustomer(@PathVariable("id") Long id)  {
	    Optional<Customer> optCustomer = customerService.get(id) ;
	    if(optCustomer.isEmpty()) {
	    	return ResponseEntity.noContent().build();
	    }
	    customerService.delete(optCustomer.get()) ;
	    return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id)  {
	    Optional<Customer> optCustomer = customerService.get(id) ;
	    if(optCustomer.isEmpty()) {
	    	return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(optCustomer.get()) ;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Customer>> getAllCustomers()  {
	    List<Customer> listCountries = customerService.getAll() ;
	    return ResponseEntity.ok(listCountries);
	}
	
	
	@GetMapping("/exists/email-address/{emailAddress}")
	public ResponseEntity<Boolean> checkIfEmailAddressExists(@PathVariable("emailAddress") String emailAddress)  {
		Boolean emailAddressExists = customerService.checksIfEmailAddressExists(emailAddress) ;
	    return ResponseEntity.ok(emailAddressExists);
	}
}

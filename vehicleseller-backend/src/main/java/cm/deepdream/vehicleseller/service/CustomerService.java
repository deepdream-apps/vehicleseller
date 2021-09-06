package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Customer;
import cm.deepdream.vehicleseller.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository ;
	
	public Customer create (Customer customer) {
		Customer savedCustomer  = customerRepository.save(customer) ;
		return savedCustomer ;
	}
	
	
	public Customer modify (Customer customer) {
		Customer savedCustomer  = customerRepository.save(customer) ;
		return savedCustomer ;
	}
	
	
	public void delete (Customer customer) {
		customerRepository.delete(customer);
	}
	
	
	public Customer get (Long id) {
		Customer savedCustomer  = customerRepository.findById(id).orElseGet(null) ;
		return savedCustomer ;
	}
	
	
	public List<Customer> getAll () {
		Iterable<Customer> customers  = customerRepository.findAll() ;
		List<Customer> customersList = new ArrayList<Customer>() ;
		customers.forEach(customersList::add) ;
		return customersList ;
	}
}

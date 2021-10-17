package cm.deepdream.vehicleseller.service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cm.deepdream.vehicleseller.model.Customer;
import cm.deepdream.vehicleseller.model.User;
import cm.deepdream.vehicleseller.repository.CustomerRepository;
import cm.deepdream.vehicleseller.repository.UserRepository;
import cm.deepdream.vehicleseller.util.EmailSender;
import freemarker.template.TemplateException;

@Transactional
@Service
public class CustomerService {
	private final Logger logger = Logger.getLogger(CustomerService.class.getName()) ;
	@Autowired
	private CustomerRepository customerRepository ;
	
	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	private  EmailSender emailSender ;
	
	@Value("${app.contextPath}")
	private String contextPath ;
	
	public Customer create (final Customer customer) {
		final Customer savedCustomer  = customerRepository.save(customer) ;
		
		User user = new User(null, customer.getEmailAddress(), customer.getFirstName(), customer.getLastName(), 
				"Usager", null, LocalDateTime.now(), LocalDateTime.now().plusMonths(3L), "Not Active") ;
		
		final User savedUser = userRepository.save(user) ;
		
		final Map<String, Object> templateModel = new HashMap<String, Object>() ;
		templateModel.put("name", customer.getFirstName()+ " "+  customer.getLastName()) ;
		templateModel.put("link", contextPath + "/user/definepassword") ;
		try {
			ExecutorService executorService = Executors.newSingleThreadExecutor() ;
			executorService.execute(() -> {
				try {
					emailSender.sendMessage(customer.getEmailAddress(), 
							"Votre inscription", "confirm-customer-add.html", templateModel) ;
				}catch(MessagingException |TemplateException | IOException ex ) {
					logger.log(Level.SEVERE, ex.getMessage(), ex) ;
				}
			});
			
		}catch(Exception exx) {
			logger.log(Level.SEVERE, exx.getMessage(), exx) ;
		}
		
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

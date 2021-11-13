package cm.deepdream.vehicleseller.service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cm.deepdream.vehicleseller.enums.RoleUser;
import cm.deepdream.vehicleseller.enums.StatusUser;
import cm.deepdream.vehicleseller.model.Customer;
import cm.deepdream.vehicleseller.model.User;
import cm.deepdream.vehicleseller.repository.CustomerRepository;
import cm.deepdream.vehicleseller.repository.UserRepository;
import cm.deepdream.vehicleseller.util.EmailSender;
import freemarker.template.TemplateException;
import lombok.extern.log4j.Log4j2;

@Transactional
@Service
@Log4j2
public class CustomerService {
	private CustomerRepository customerRepository ;
	
	private UserRepository userRepository ;
	
	private  EmailSender emailSender ;
	
	@Value("${app.contextPath}")
	private String contextPath ;
	
	
	public CustomerService(CustomerRepository customerRepository, UserRepository userRepository,
			EmailSender emailSender) {
		super();
		this.customerRepository = customerRepository;
		this.userRepository = userRepository;
		this.emailSender = emailSender;
	}


	/**
	 * Create a customer, the related user and send a confirmation email to the user.
	 * @param customer 
	 * @return the created customer
	 */
	public Customer create (final Customer customer) {
		final Customer savedCustomer  = customerRepository.save(customer) ;
		
		User user = new User(null, customer.getEmailAddress(), customer.getFirstName(), customer.getLastName(), 
				RoleUser.CUSTOMER.getLabel(), null, LocalDateTime.now(), StatusUser.NOT_ACTIVATED.getLabel()) ;
		
		final User savedUser = userRepository.save(user) ;
		
		final Map<String, Object> templateModel = new HashMap<>() ;
		templateModel.put("name", customer.getFirstName()+ " "+  customer.getLastName()) ;
		templateModel.put("link", contextPath + "/user/definepassword/"+savedUser.getId()+"/"+savedCustomer.getEmailAddress()) ;
		try {
			ExecutorService executorService = Executors.newSingleThreadExecutor() ;
			executorService.execute(() -> {
				try {
					emailSender.sendMessage(customer.getEmailAddress(), 
							"Votre inscription", "confirm-customer-add.html", templateModel) ;
				}catch(MessagingException |TemplateException | IOException ex ) {
					log.error(ex.getMessage(), ex) ;
				}
			});
			
		}catch(Exception exx) {
			log.error(exx.getMessage(), exx) ;
		}
		
		return savedCustomer ;
	}
	
	
	public Customer modify (Customer customer) {
		return customerRepository.save(customer) ;
	}
	
	
	public void delete (Customer customer) {
		customerRepository.delete(customer);
	}
	
	
	public Optional<Customer> get (Long id) {
		return customerRepository.findById(id)  ;
	}
	
	public Optional<Customer> getByEmailAddress (String emailAddress) {
		return customerRepository.findByEmailAddress(emailAddress)  ;
	}
	
	public Boolean checksIfEmailAddressExists (String emailAddress) {
		return customerRepository.existsByEmailAddress(emailAddress) ;
	}
	
	
	public List<Customer> getAll () {
		Iterable<Customer> customers  = customerRepository.findAll() ;
		List<Customer> customersList = new ArrayList<>() ;
		customers.forEach(customersList::add) ;
		return customersList ;
	}
}

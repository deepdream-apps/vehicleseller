package cm.deepdream.vehicleseller.webservice;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
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
import cm.deepdream.vehicleseller.dto.UserPasswordDTO;
import cm.deepdream.vehicleseller.enums.StatusUser;
import cm.deepdream.vehicleseller.model.User;
import cm.deepdream.vehicleseller.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserWS {
	private Logger logger = Logger.getLogger(UserWS.class.getName()) ;
	
	private UserService userService ;
	
	public UserWS(UserService userService) {
		this.userService = userService;
	}


	@PostMapping("/add")
	public ResponseEntity<User> addUser(@RequestBody User user)  {
	    if(user.getEmailAddress() == null || user.getEmailAddress().isBlank()) {
	         return ResponseEntity.badRequest().build();
	    }
	    User newUser = userService.create(user) ;
	    return ResponseEntity.ok(newUser) ;
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User user)  {
	    Optional<User> optUser = userService.get(user.getId()) ;
	    if(optUser.isEmpty() || user.getEmailAddress() == null || user.getEmailAddress().isBlank()) {
	         return ResponseEntity.badRequest().build();
	     }
	    
	    User existingUser = optUser.get() ;
	    existingUser.setFirstName(user.getFirstName());
	    existingUser.setLastName(user.getLastName());
	    existingUser.setRoleName(user.getRoleName()) ;
	    existingUser.setStatus(user.getStatus()) ;
	    User upadatedUser = userService.modify(existingUser) ;
	    return ResponseEntity.ok(upadatedUser) ;
	}
	
	
	@PutMapping("/suspend")
	public ResponseEntity<User> suspendUser(@RequestBody User user)  {
	    Optional<User> optUser = userService.get(user.getId()) ;
	    if(optUser.isEmpty() || user.getEmailAddress() == null || user.getEmailAddress().isBlank()) {
	         return ResponseEntity.badRequest().build();
	     }
	    
	    User existingUser = optUser.get() ;
	    User upadatedUser = userService.suspend(existingUser) ;
	    return ResponseEntity.ok(upadatedUser) ;
	}
	
	
	@PutMapping("/activate")
	public ResponseEntity<User> activateUser(@RequestBody User user)  {
	    Optional<User> optUser = userService.get(user.getId()) ;
	    if(optUser.isEmpty() || user.getEmailAddress() == null || user.getEmailAddress().isBlank()) {
	         return ResponseEntity.badRequest().build();
	     }
	    
	    User existingUser = optUser.get() ;
	    User upadatedUser = userService.activate(existingUser) ;
	    return ResponseEntity.ok(upadatedUser) ;
	}
	
	
	@PutMapping("/define-password")
	public ResponseEntity<User> defineUserPassword(@RequestBody  UserPasswordDTO userPassword)  {
	    Optional<User> optUser = userService.get(userPassword.getUserId()) ;
	    if(optUser.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    User existingUser = optUser.get() ;
	    existingUser.setPassword(userPassword.getPassword()) ;
	    existingUser.setStatus(StatusUser.ACTIVATED.getLabel()) ;
	    User upadatedUser = userService.modify(existingUser) ;
	    return ResponseEntity.ok(upadatedUser);
	}
	
	
	@PutMapping("/authenticate")
	public ResponseEntity<User> authenticate(final UserPasswordDTO userPassword) {
	    Optional<User> optUser = userService.authenticate(userPassword.getEmailAddress(), userPassword.getPassword()) ;
	    if(optUser.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    return ResponseEntity.ok(optUser.get()) ;
	}
	
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity deleteUser(@PathVariable("id") Long id) {
	    Optional<User> optUser = userService.get(id) ;
	    if(optUser.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    userService.delete(optUser.get()) ;
	    return ResponseEntity.ok().build();
	}
	
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
	    Optional<User> optUser = userService.get(id) ;
	    if(optUser.isEmpty()) {
	    	return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(optUser.get());
	}
	
	

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() {
	    List<User> listCountries = userService.getAll() ;
	    return ResponseEntity.ok(listCountries) ;
	}
}

package cm.deepdream.vehicleseller.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.User;
import cm.deepdream.vehicleseller.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository ;
	
	public User create (User user) {
		user.setCreationDate(LocalDateTime.now());
		return userRepository.save(user) ;
	}
	
	
	public User modify (User user) {
		return userRepository.save(user) ;
	}
	
	
	public Optional<User> authenticate (String emailAddress, String password) {
		User user = userRepository.findByEmailAddressAndPassword(emailAddress, password);
		return Optional.ofNullable(user) ;
	}
	
	
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	
	public Optional<User> get (Long id) {
		return userRepository.findById(id)  ;
	}
	
	
	public List<User> getAll () {
		Iterable<User> countries  = userRepository.findAll() ;
		List<User> usersList = new ArrayList<>() ;
		countries.forEach(usersList::add) ;
		return usersList ;
	}
}

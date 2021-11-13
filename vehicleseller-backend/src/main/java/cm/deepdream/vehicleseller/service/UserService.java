package cm.deepdream.vehicleseller.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cm.deepdream.vehicleseller.enums.StatusUser;
import cm.deepdream.vehicleseller.model.User;
import cm.deepdream.vehicleseller.repository.UserRepository;
import lombok.extern.log4j.Log4j2;

@Transactional
@Service
@Log4j2
public class UserService {
	private UserRepository userRepository ;
	
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	public User create (User user) {
		user.setCreationDate(LocalDateTime.now());
		return userRepository.save(user) ;
	}
	
	
	public User modify (User user) {
		return userRepository.save(user) ;
	}
	
	
	public User suspend (User user) {
		user.setStatus(StatusUser.SUSPENDED.getLabel());
		return userRepository.save(user) ;
	}
	
	
	public User activate (User user) {
		user.setStatus(StatusUser.ACTIVATED.getLabel());
		return userRepository.save(user) ;
	}
	
	
	public Optional<User> authenticate (final String emailAddress, final char[] password) {
		User user = userRepository.findByEmailAddress(emailAddress);
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

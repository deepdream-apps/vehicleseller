package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.User;
import cm.deepdream.vehicleseller.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	@Autowired
	private UserRepository userRepository ;
	
	public User create (User user) {
		User savedUser  = userRepository.save(user) ;
		return savedUser ;
	}
	
	
	public User modify (User user) {
		User savedUser  = userRepository.save(user) ;
		return savedUser ;
	}
	
	
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	
	public User get (Long id) {
		User savedUser  = userRepository.findById(id).orElseGet(null) ;
		return savedUser ;
	}
	
	
	public List<User> getAll () {
		Iterable<User> countries  = userRepository.findAll() ;
		List<User> usersList = new ArrayList<User>() ;
		countries.forEach(usersList::add) ;
		return usersList ;
	}
}

package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Emptying;
import cm.deepdream.vehicleseller.repository.EmptyingRepository;

@Service
public class EmptyingService {
	@Autowired
	private EmptyingRepository emptyingRepository ;
	
	public Emptying create (Emptying emptying) {
		return emptyingRepository.save(emptying) ;
	}
	
	
	public Emptying modify (Emptying emptying) {
		return emptyingRepository.save(emptying) ;
	}
	
	
	public void delete (Emptying emptying) {
		emptyingRepository.delete(emptying);
	}
	
	
	public Emptying get (Long id) {
		return emptyingRepository.findById(id).orElseThrow(NullPointerException::new)  ;
	}
	
	
	public List<Emptying> getAll () {
		Iterable<Emptying> emptyings  = emptyingRepository.findAll() ;
		List<Emptying> emptyingsList = new ArrayList<>() ;
		emptyings.forEach(emptyingsList::add) ;
		return emptyingsList ;
	}
}

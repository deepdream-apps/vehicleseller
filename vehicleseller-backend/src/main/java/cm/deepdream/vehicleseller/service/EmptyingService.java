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
		Emptying savedEmptying  = emptyingRepository.save(emptying) ;
		return savedEmptying ;
	}
	
	
	public Emptying modify (Emptying emptying) {
		Emptying savedEmptying  = emptyingRepository.save(emptying) ;
		return savedEmptying ;
	}
	
	
	public void delete (Emptying emptying) {
		emptyingRepository.delete(emptying);
	}
	
	
	public Emptying get (Long id) {
		Emptying savedEmptying  = emptyingRepository.findById(id).orElseGet(null) ;
		return savedEmptying ;
	}
	
	
	public List<Emptying> getAll () {
		Iterable<Emptying> emptyings  = emptyingRepository.findAll() ;
		List<Emptying> emptyingsList = new ArrayList<Emptying>() ;
		emptyings.forEach(emptyingsList::add) ;
		return emptyingsList ;
	}
}

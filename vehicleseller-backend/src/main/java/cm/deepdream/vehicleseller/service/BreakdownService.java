package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Breakdown;
import cm.deepdream.vehicleseller.repository.BreakdownRepository;

@Service
public class BreakdownService {
	@Autowired
	private BreakdownRepository breakdownRepository ;
	
	public Breakdown create (Breakdown breakdown) {
		return breakdownRepository.save(breakdown) ;
	}
	
	
	public Breakdown modify (Breakdown breakdown) {
		return breakdownRepository.save(breakdown) ;
	}
	
	
	public void delete (Breakdown breakdown) {
		breakdownRepository.delete(breakdown);
	}
	
	
	public Breakdown get (Long id) {
		return breakdownRepository.findById(id).orElseThrow(NullPointerException::new) ;
	}
	
	
	public List<Breakdown> getAll () {
		Iterable<Breakdown> breakdowns  = breakdownRepository.findAll() ;
		List<Breakdown> breakdownsList = new ArrayList<>() ;
		breakdowns.forEach(breakdownsList::add) ;
		return breakdownsList ;
	}
}

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
		Breakdown savedBreakdown  = breakdownRepository.save(breakdown) ;
		return savedBreakdown ;
	}
	
	
	public Breakdown modify (Breakdown breakdown) {
		Breakdown savedBreakdown  = breakdownRepository.save(breakdown) ;
		return savedBreakdown ;
	}
	
	
	public void delete (Breakdown breakdown) {
		breakdownRepository.delete(breakdown);
	}
	
	
	public Breakdown get (Long id) {
		Breakdown savedBreakdown  = breakdownRepository.findById(id).orElseGet(null) ;
		return savedBreakdown ;
	}
	
	
	public List<Breakdown> getAll () {
		Iterable<Breakdown> breakdowns  = breakdownRepository.findAll() ;
		List<Breakdown> breakdownsList = new ArrayList<Breakdown>() ;
		breakdowns.forEach(breakdownsList::add) ;
		return breakdownsList ;
	}
}

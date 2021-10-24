package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.deepdream.vehicleseller.model.TechnicalVisit;
import cm.deepdream.vehicleseller.repository.TechnicalVisitRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TechnicalVisitService {
	@Autowired
	private TechnicalVisitRepository technicalVisitRepository ;
	
	public TechnicalVisit create (TechnicalVisit technicalVisit) {
		return technicalVisitRepository.save(technicalVisit) ;
	}
	
	
	public TechnicalVisit modify (TechnicalVisit technicalVisit) {
		return technicalVisitRepository.save(technicalVisit) ;
	}
	
	
	public void delete(TechnicalVisit technicalVisit) {
		technicalVisitRepository.delete(technicalVisit);
	}
	
	
	public TechnicalVisit get (Long id) {
		return technicalVisitRepository.findById(id).orElseThrow(NullPointerException::new)  ;
	}
	
	
	public List<TechnicalVisit> getAll () {
		Iterable<TechnicalVisit> technicalVisits  = technicalVisitRepository.findAll() ;
		List<TechnicalVisit> technicalVisitsList = new ArrayList<>() ;
		technicalVisits.forEach(technicalVisitsList::add) ;
		return technicalVisitsList ;
	}
}

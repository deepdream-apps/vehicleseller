package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Insurance;
import cm.deepdream.vehicleseller.repository.InsuranceRepository;

@Service
public class InsuranceService {
	@Autowired
	private InsuranceRepository insuranceRepository ;
	
	public Insurance create (Insurance insurance) {
		Insurance savedInsurance  = insuranceRepository.save(insurance) ;
		return savedInsurance ;
	}
	
	
	public Insurance modify (Insurance insurance) {
		Insurance savedInsurance  = insuranceRepository.save(insurance) ;
		return savedInsurance ;
	}
	
	
	public void delete (Insurance insurance) {
		insuranceRepository.delete(insurance);
	}
	
	
	public Insurance get (Long id) {
		Insurance savedInsurance  = insuranceRepository.findById(id).orElseGet(null) ;
		return savedInsurance ;
	}
	
	
	public List<Insurance> getAll () {
		Iterable<Insurance> insurances  = insuranceRepository.findAll() ;
		List<Insurance> insurancesList = new ArrayList<Insurance>() ;
		insurances.forEach(insurancesList::add) ;
		return insurancesList ;
	}
}

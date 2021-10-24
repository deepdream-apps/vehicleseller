package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.InsuranceContract;
import cm.deepdream.vehicleseller.repository.InsuranceContractRepository;

@Service
public class InsuranceContractService {
	@Autowired
	private InsuranceContractRepository insuranceContractRepository ;
	
	public InsuranceContract create (InsuranceContract insuranceContract) {
		return insuranceContractRepository.save(insuranceContract) ;
	}
	
	
	public InsuranceContract modify (InsuranceContract insuranceContract) {
		return insuranceContractRepository.save(insuranceContract) ;
	}
	
	
	public void delete (InsuranceContract insuranceContract) {
		insuranceContractRepository.delete(insuranceContract);
	}
	
	
	public InsuranceContract get (Long id) {
		return insuranceContractRepository.findById(id).orElseThrow(NullPointerException::new)  ;
	}
	
	
	public List<InsuranceContract> getAll () {
		Iterable<InsuranceContract> insuranceContracts  = insuranceContractRepository.findAll() ;
		List<InsuranceContract> insuranceContractsList = new ArrayList<>() ;
		insuranceContracts.forEach(insuranceContractsList::add) ;
		return insuranceContractsList ;
	}
}

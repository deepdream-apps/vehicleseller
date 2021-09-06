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
		InsuranceContract savedInsuranceContract  = insuranceContractRepository.save(insuranceContract) ;
		return savedInsuranceContract ;
	}
	
	
	public InsuranceContract modify (InsuranceContract insuranceContract) {
		InsuranceContract savedInsuranceContract  = insuranceContractRepository.save(insuranceContract) ;
		return savedInsuranceContract ;
	}
	
	
	public void delete (InsuranceContract insuranceContract) {
		insuranceContractRepository.delete(insuranceContract);
	}
	
	
	public InsuranceContract get (Long id) {
		InsuranceContract savedInsuranceContract  = insuranceContractRepository.findById(id).orElseGet(null) ;
		return savedInsuranceContract ;
	}
	
	
	public List<InsuranceContract> getAll () {
		Iterable<InsuranceContract> insuranceContracts  = insuranceContractRepository.findAll() ;
		List<InsuranceContract> insuranceContractsList = new ArrayList<InsuranceContract>() ;
		insuranceContracts.forEach(insuranceContractsList::add) ;
		return insuranceContractsList ;
	}
}

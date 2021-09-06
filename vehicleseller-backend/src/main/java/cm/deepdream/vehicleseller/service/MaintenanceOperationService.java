package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.deepdream.vehicleseller.model.MaintenanceOperation;
import cm.deepdream.vehicleseller.repository.MaintenanceOperationRepository;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class MaintenanceOperationService {
	@Autowired
	private MaintenanceOperationRepository maintenanceOperationRepository ;
	
	public MaintenanceOperation create (MaintenanceOperation maintenanceOperation) {
		MaintenanceOperation savedMaintenanceOperation  = maintenanceOperationRepository.save(maintenanceOperation) ;
		return savedMaintenanceOperation ;
	}
	
	
	public MaintenanceOperation modify (MaintenanceOperation maintenanceOperation) {
		MaintenanceOperation savedMaintenanceOperation  = maintenanceOperationRepository.save(maintenanceOperation) ;
		return savedMaintenanceOperation ;
	}
	
	
	public void delete(MaintenanceOperation maintenanceOperation) {
		maintenanceOperationRepository.delete(maintenanceOperation);
	}
	
	
	public MaintenanceOperation get (Long id) {
		MaintenanceOperation savedMaintenanceOperation  = maintenanceOperationRepository.findById(id).orElseGet(null) ;
		return savedMaintenanceOperation ;
	}
	
	
	public List<MaintenanceOperation> getAll () {
		Iterable<MaintenanceOperation> countries  = maintenanceOperationRepository.findAll() ;
		List<MaintenanceOperation> countriesList = new ArrayList<MaintenanceOperation>() ;
		countries.forEach(countriesList::add) ;
		return countriesList ;
	}
}

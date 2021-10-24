package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Driver;
import cm.deepdream.vehicleseller.repository.DriverRepository;

@Service
public class DriverService {

	@Autowired
	private DriverRepository driverRepository ;
	
	public Driver create (Driver driver) {
		return driverRepository.save(driver) ;
	}
	
	
	public Driver modify (Driver driver) {
		return driverRepository.save(driver) ;
	}
	
	
	public void delete (Driver driver) {
		driverRepository.delete(driver);
	}
	
	
	public Optional<Driver> get (Long id) {
		return driverRepository.findById(id)  ;
	}
	
	
	public List<Driver> getAll () {
		Iterable<Driver> drivers  = driverRepository.findAll() ;
		List<Driver> driversList = new ArrayList<>() ;
		drivers.forEach(driversList::add) ;
		return driversList ;
	}
}

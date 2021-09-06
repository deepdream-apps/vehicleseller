package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Driver;
import cm.deepdream.vehicleseller.repository.DriverRepository;

@Service
public class DriverService {
	@Autowired
	private DriverRepository driverRepository ;
	
	public Driver create (Driver driver) {
		Driver savedDriver  = driverRepository.save(driver) ;
		return savedDriver ;
	}
	
	
	public Driver modify (Driver driver) {
		Driver savedDriver  = driverRepository.save(driver) ;
		return savedDriver ;
	}
	
	
	public void delete (Driver driver) {
		driverRepository.delete(driver);
	}
	
	
	public Driver get (Long id) {
		Driver savedDriver  = driverRepository.findById(id).orElseGet(null) ;
		return savedDriver ;
	}
	
	
	public List<Driver> getAll () {
		Iterable<Driver> drivers  = driverRepository.findAll() ;
		List<Driver> driversList = new ArrayList<Driver>() ;
		drivers.forEach(driversList::add) ;
		return driversList ;
	}
}

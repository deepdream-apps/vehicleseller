package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cm.deepdream.vehicleseller.model.Driver;
import cm.deepdream.vehicleseller.repository.DriverRepository;
import lombok.extern.log4j.Log4j2;
@Transactional
@Service
@Log4j2
public class DriverService {
	private DriverRepository driverRepository ;
	
	public DriverService(DriverRepository driverRepository) {
		this.driverRepository = driverRepository;
	}

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
	
	
	public Optional<Driver> getByEmailAddress (String emailAddress) {
		return driverRepository.findByEmailAddress(emailAddress)  ;
	}
	
	
	public List<Driver> getAll () {
		Iterable<Driver> drivers  = driverRepository.findAll() ;
		List<Driver> driversList = new ArrayList<>() ;
		drivers.forEach(driversList::add) ;
		return driversList ;
	}
}

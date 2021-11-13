package cm.deepdream.vehicleseller.webservice;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cm.deepdream.vehicleseller.model.Driver;
import cm.deepdream.vehicleseller.service.DriverService;

@RestController
@RequestMapping("/api/driver")
public class DriverWS {
	private DriverService driverService ;
	
	
	public DriverWS(DriverService driverService) {
		this.driverService = driverService;
	}


	@PostMapping("/add")
	public ResponseEntity<Driver> addDriver(@RequestBody Driver driver) {
		Driver newDriver = driverService.create(driver) ;
		return ResponseEntity.ok(newDriver) ;
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Driver> updateDriver(@RequestBody Driver driver) {
	    Optional<Driver> optDriver = driverService.get(driver.getId()) ;
	    if(optDriver.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    
	    Driver existingDriver = optDriver.get() ;
	    existingDriver.setRegistrationNumber(driver.getRegistrationNumber());
	    existingDriver.setBirthDay(driver.getBirthDay());
	    existingDriver.setDriverLicence(driver.getDriverLicence());
	    existingDriver.setFirstName(driver.getFirstName());
	    existingDriver.setLastName(driver.getLastName()) ;
	    existingDriver.setDriverLicence(driver.getDriverLicence()) ;
	    existingDriver.setStatus(driver.getStatus()) ;
	    existingDriver.setPhoneNumber(driver.getPhoneNumber()) ;
	    existingDriver.setEmailAddress(driver.getEmailAddress()) ;
	    Driver upadatedDriver = driverService.create(existingDriver) ;
	    return ResponseEntity.ok(upadatedDriver) ;
	}
	
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity deleteDriver(@PathVariable("id") Long id) {
	    Optional<Driver> optDriver = driverService.get(id) ;
	    if(optDriver.isEmpty()) {
	    	return ResponseEntity.noContent().build();
	    }
	    driverService.delete(optDriver.get()) ;
	    return ResponseEntity.ok().build();
	}
	
	
	
	@GetMapping("/email-address/{emailAdress}")
	public ResponseEntity<Driver> getDriverByEmailAddress(@PathVariable("emailAdress") String emailAdress) {
	    Optional<Driver> optDriver = driverService.getByEmailAddress(emailAdress) ;
	    if(optDriver.isEmpty()) {
	    	return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(optDriver.get()) ;
	}
	
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Driver> getDriver(@PathVariable("id") Long id) {
	    Optional<Driver> optDriver = driverService.get(id) ;
	    if(optDriver.isEmpty()) {
	    	return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(optDriver.get()) ;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Driver>> getAllDrivers()  {
	    List<Driver> listCountries = driverService.getAll() ;
	    return ResponseEntity.ok(listCountries) ;
	}
}

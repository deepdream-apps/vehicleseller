package cm.deepdream.vehicleseller.webservice;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cm.deepdream.vehicleseller.model.Model;
import cm.deepdream.vehicleseller.model.Vehicle;
import cm.deepdream.vehicleseller.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/api/vehicle")
public class VehicleWS {
	@Autowired
	private VehicleService vehicleService ;

	
	@PostMapping("/add")
	public ResponseEntity<Vehicle> addVehicle(@RequestBody  Vehicle vehicle)  {
	    Vehicle newVehicle = vehicleService.create(vehicle) ;
	    return ResponseEntity.ok(newVehicle);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle) {
		Optional<Vehicle> optVehicle = vehicleService.get(vehicle.getId()) ;
	    if(optVehicle.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    Vehicle  existingVehicle = optVehicle.get() ;
	    existingVehicle.setColor(vehicle.getColor());
	    existingVehicle.setDescription(vehicle.getDescription());
	    existingVehicle.setDoors(vehicle.getDoors());
	    existingVehicle.setModel(vehicle.getModel());
	    existingVehicle.setRegistrationNumber(vehicle.getRegistrationNumber());
	    existingVehicle.setSeats(vehicle.getSeats());
	    existingVehicle.setStatus(vehicle.getStatus());
	    existingVehicle.setYear(vehicle.getYear());
	    Vehicle upadatedVehicle = vehicleService.create(existingVehicle) ;
	    return ResponseEntity.ok(upadatedVehicle) ;
	}
	
	
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity deleteVehicle(@PathVariable("id") Long id) {
	    Optional<Vehicle> optVehicle = vehicleService.get(id) ;
	    if(optVehicle.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    
	    vehicleService.delete(optVehicle.get()) ;
	    return ResponseEntity.ok().build();
	}
	
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Vehicle> getVehicle(@PathVariable("id") Long id)  {
	    Optional<Vehicle> optVehicle = vehicleService.get(id) ;
	    if(optVehicle.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    return ResponseEntity.ok(optVehicle.get()) ;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Vehicle>> getAllVehicles()  {
	    List<Vehicle> listVehicles = vehicleService.getAll() ;
	    return ResponseEntity.ok(listVehicles) ;
	}
}

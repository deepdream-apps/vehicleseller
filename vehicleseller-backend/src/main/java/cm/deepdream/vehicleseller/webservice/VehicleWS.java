package cm.deepdream.vehicleseller.webservice;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cm.deepdream.vehicleseller.dto.VehicleDTO;
import cm.deepdream.vehicleseller.service.VehicleService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/vehicle")
@Log4j2
public class VehicleWS {
	private VehicleService vehicleService ;
	

	public VehicleWS(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}


	@PostMapping("/add")
	public ResponseEntity<VehicleDTO> addVehicle( @RequestBody  VehicleDTO VehicleDTO)  {
		VehicleDTO newVehicleDTO = vehicleService.create(VehicleDTO) ;
	    return ResponseEntity.ok(newVehicleDTO);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleDTO VehicleDTO) {
		
	    VehicleDTO  updatedVehicleDTO = vehicleService.update(VehicleDTO) ;
	  
	    return ResponseEntity.ok(updatedVehicleDTO) ;
	}
	
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity deleteVehicle(@PathVariable("id") Long id) {
	    VehicleDTO vehicleDTO = VehicleDTO.builder().id(id).build() ;
	    
	    vehicleService.delete(vehicleDTO) ;
	    
	    return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<VehicleDTO> getVehicle(@PathVariable("id") Long id)  {
	    Optional<VehicleDTO> optVehicleDTO = vehicleService.get(id) ;
	    if(optVehicleDTO.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    return ResponseEntity.ok(optVehicleDTO.get()) ;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<VehicleDTO>> getAllVehicles()  {
	    List<VehicleDTO> listVehiclesDTO = vehicleService.getAll() ;
	    return ResponseEntity.ok(listVehiclesDTO) ;
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public void handleIllegalArgument(IllegalArgumentException ex) {
		log.error(ex.getMessage(), ex);
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public void handleConstraintViolation(ConstraintViolationException ex) {
		log.error(ex.getMessage(), ex);
	}
}

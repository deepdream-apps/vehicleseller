package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
import cm.deepdream.vehicleseller.model.Vehicle;
import cm.deepdream.vehicleseller.repository.VehicleRepository;

@Service
public class VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository ;
	@Autowired
	private AmazonS3 amazonS3 ;
	
	public Vehicle create (Vehicle vehicle) {
		return vehicleRepository.save(vehicle) ;
	}
	
	
	public Vehicle modify (Vehicle vehicle) {
		return vehicleRepository.save(vehicle) ;
	}
	
	
	public void delete(Vehicle vehicle) {
		vehicleRepository.delete(vehicle);
	}
	
	
	public Optional<Vehicle> get (Long id) {
		return vehicleRepository.findById(id)  ;
	}
	

	
	
	public List<Vehicle> getAll () {
		Iterable<Vehicle> vehicles  = vehicleRepository.findAll() ;
		List<Vehicle> vehiclesList = new ArrayList<>() ;
		vehicles.forEach(vehiclesList::add) ;
		return vehiclesList ;
	}
}

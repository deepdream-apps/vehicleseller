package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.deepdream.vehicleseller.model.Vehicle;
import cm.deepdream.vehicleseller.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository ;
	
	public Vehicle create (Vehicle vehicle) {
		Vehicle savedVehicle  = vehicleRepository.save(vehicle) ;
		return savedVehicle ;
	}
	
	
	public Vehicle modify (Vehicle vehicle) {
		Vehicle savedVehicle  = vehicleRepository.save(vehicle) ;
		return savedVehicle ;
	}
	
	
	public void delete(Vehicle vehicle) {
		vehicleRepository.delete(vehicle);
	}
	
	
	public Vehicle get (Long id) {
		Vehicle savedVehicle  = vehicleRepository.findById(id).orElseGet(null) ;
		return savedVehicle ;
	}
	
	
	public List<Vehicle> getAll () {
		Iterable<Vehicle> vehicles  = vehicleRepository.findAll() ;
		List<Vehicle> vehiclesList = new ArrayList<Vehicle>() ;
		vehicles.forEach(vehiclesList::add) ;
		return vehiclesList ;
	}
}

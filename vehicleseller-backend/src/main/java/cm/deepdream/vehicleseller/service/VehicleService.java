package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Brand;
import cm.deepdream.vehicleseller.model.Model;
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
	
	
	public List<Vehicle> get (Brand brand, Model model, Long yearFrom, String fuel, Long yearTo, Long mileageMin, Long mileageMax, 
			Long priceMin, Long priceMax, int choice) {
		List<Vehicle> listVehicles = null ;
		switch (choice) {
			case 0 :{
				listVehicles = vehicleRepository.findByBrandAndFuelAndYearBetweenAndMileageBetweenAndPriceBetween(brand, 
						fuel, yearFrom, yearTo, mileageMin, mileageMax, priceMin, priceMax) ;
				break ;
			}
			
			case 1 :{
				listVehicles = vehicleRepository.findByModelAndFuelAndYearBetweenAndMileageBetweenAndPriceBetween(model, 
						fuel, yearFrom, yearTo, mileageMin, mileageMax, priceMin, priceMax) ;
				break ;
			}
			
			case 2 :{
				listVehicles = vehicleRepository.findByFuelAndYearBetweenAndMileageBetweenAndPriceBetween( 
						fuel, yearFrom, yearTo, mileageMin, mileageMax, priceMin, priceMax) ;
				break ;
			}
			
			case 3 :{
				listVehicles = vehicleRepository.findByBrandAndYearBetweenAndMileageBetweenAndPriceBetween(brand, 
						yearFrom, yearTo, mileageMin, mileageMax, priceMin, priceMax) ;
				break ;
			}
			
			case 4 :{
				listVehicles = vehicleRepository.findByModelAndYearBetweenAndMileageBetweenAndPriceBetween(model, 
						yearFrom, yearTo, mileageMin, mileageMax, priceMin, priceMax) ;
				break ;
			}
			
			case 5 :{
				listVehicles = vehicleRepository.findByYearBetweenAndMileageBetweenAndPriceBetween( 
						yearFrom, yearTo, mileageMin, mileageMax, priceMin, priceMax) ;
				break ;
			}
		}
		return listVehicles ;
	}
	
	
	public List<Vehicle> getAll () {
		Iterable<Vehicle> vehicles  = vehicleRepository.findAll() ;
		List<Vehicle> vehiclesList = new ArrayList<Vehicle>() ;
		vehicles.forEach(vehiclesList::add) ;
		return vehiclesList ;
	}
}

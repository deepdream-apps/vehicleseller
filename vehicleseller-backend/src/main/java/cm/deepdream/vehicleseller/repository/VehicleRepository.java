package cm.deepdream.vehicleseller.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.Brand;
import cm.deepdream.vehicleseller.model.Model;
import cm.deepdream.vehicleseller.model.Vehicle;
@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long>{
	public List<Vehicle> findByBrandAndFuelAndYearBetweenAndMileageBetweenAndPriceBetween(Brand brand, String fuel, 
			Long yearFrom, Long yearTo, Long mileageMin, Long mileageMax, Long priceMin, Long priceMax) ;
	public List<Vehicle> findByModelAndFuelAndYearBetweenAndMileageBetweenAndPriceBetween(Model model, String fuel, 
			Long yearFrom, Long yearTo, Long mileageMin, Long mileageMax, Long priceMin, Long priceMax) ;
	public List<Vehicle> findByFuelAndYearBetweenAndMileageBetweenAndPriceBetween(String fuel, Long yearFrom, Long yearTo, Long mileageMin, Long mileageMax, 
			Long priceMin, Long priceMax) ;
	public List<Vehicle> findByBrandAndYearBetweenAndMileageBetweenAndPriceBetween(Brand brand, Long yearFrom, 
			Long yearTo, Long mileageMin, Long mileageMax, Long priceMin, Long priceMax) ;
	public List<Vehicle> findByModelAndYearBetweenAndMileageBetweenAndPriceBetween(Model model, Long yearFrom, 
			Long yearTo, Long mileageMin, Long mileageMax, Long priceMin, Long priceMax) ;
	public List<Vehicle> findByYearBetweenAndMileageBetweenAndPriceBetween(Long yearFrom, Long yearTo, Long mileageMin, Long mileageMax, 
			Long priceMin, Long priceMax) ;
}

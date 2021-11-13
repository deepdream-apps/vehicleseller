package cm.deepdream.vehicleseller.repository;import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.Vehicle;
@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long>{
	
}

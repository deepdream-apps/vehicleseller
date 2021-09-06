package cm.deepdream.vehicleseller.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.Brand;
import cm.deepdream.vehicleseller.model.Model;
import cm.deepdream.vehicleseller.model.Vehicle;
@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long>{
	
}

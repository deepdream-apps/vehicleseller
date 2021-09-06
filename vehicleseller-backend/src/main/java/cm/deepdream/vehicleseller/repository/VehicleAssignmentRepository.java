package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.VehicleAssignment;
@Repository
public interface VehicleAssignmentRepository extends CrudRepository<VehicleAssignment, Long>{

}

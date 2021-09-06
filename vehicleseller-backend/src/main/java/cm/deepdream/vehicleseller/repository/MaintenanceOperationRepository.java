package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.MaintenanceOperation;
@Repository
public interface MaintenanceOperationRepository extends CrudRepository<MaintenanceOperation, Long>{

}

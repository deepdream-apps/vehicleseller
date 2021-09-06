package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.Insurance;
@Repository
public interface InsuranceRepository extends CrudRepository<Insurance, Long>{

}

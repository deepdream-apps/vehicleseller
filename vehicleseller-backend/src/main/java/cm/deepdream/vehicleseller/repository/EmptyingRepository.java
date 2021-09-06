package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.Emptying;
@Repository
public interface EmptyingRepository extends CrudRepository<Emptying, Long>{

}

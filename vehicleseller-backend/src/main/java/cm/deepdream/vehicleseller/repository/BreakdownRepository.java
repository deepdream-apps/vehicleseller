package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.Breakdown;
@Repository
public interface BreakdownRepository extends CrudRepository<Breakdown, Long>{

}

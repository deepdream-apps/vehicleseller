package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import cm.deepdream.vehicleseller.model.Race;
@Transactional
@Repository
public interface RaceRepository extends CrudRepository<Race, Long>{

}

package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.Country;
@Repository
public interface CountryRepository extends CrudRepository<Country, Long>{

}

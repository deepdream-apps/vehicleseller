package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.Brand;
@Repository
public interface BrandRepository extends CrudRepository<Brand, Long>{

}

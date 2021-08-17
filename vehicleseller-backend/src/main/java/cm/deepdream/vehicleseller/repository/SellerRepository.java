package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.Seller;
@Repository
public interface SellerRepository extends CrudRepository<Seller, Long>{

}

package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.Bill;
@Repository
public interface BillRepository extends CrudRepository<Bill, Long>{

}

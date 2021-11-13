package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import cm.deepdream.vehicleseller.model.Bill;
@Transactional
@Repository
public interface BillRepository extends CrudRepository<Bill, Long>{

}

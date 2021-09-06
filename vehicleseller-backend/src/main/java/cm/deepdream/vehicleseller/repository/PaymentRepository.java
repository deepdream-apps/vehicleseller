package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.Payment;
@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long>{

}

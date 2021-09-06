package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.InsuranceContract;
@Repository
public interface InsuranceContractRepository extends CrudRepository<InsuranceContract, Long> {

}

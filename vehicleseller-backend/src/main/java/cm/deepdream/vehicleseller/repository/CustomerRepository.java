package cm.deepdream.vehicleseller.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cm.deepdream.vehicleseller.model.Customer;
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	public boolean existsByEmailAddress(String emailAddress) ;
	public Optional<Customer> findByEmailAddress (String emailAddress) ;
}

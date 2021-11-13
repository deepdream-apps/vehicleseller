package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	public User findByEmailAddress (String emailAddress)  ;
}

package cm.deepdream.vehicleseller.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cm.deepdream.vehicleseller.model.Reservation;
@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long>{

}

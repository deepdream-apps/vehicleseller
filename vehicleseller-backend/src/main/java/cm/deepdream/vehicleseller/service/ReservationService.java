package cm.deepdream.vehicleseller.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Reservation;
import cm.deepdream.vehicleseller.repository.ReservationRepository;

@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository ;
	
	public Reservation create (Reservation reservation) {
		reservation.setDate(LocalDateTime.now());
		return reservationRepository.save(reservation) ;
	}
	
	
	public Reservation modify (Reservation reservation) {
		return reservationRepository.save(reservation) ;
	}
	
	
	public void delete (Reservation reservation) {
		reservationRepository.delete(reservation);
	}
	
	
	public Optional<Reservation> get (Long id) {
		return reservationRepository.findById(id)  ;
	}
	
	
	public List<Reservation> getAll () {
		Iterable<Reservation> reservations  = reservationRepository.findAll() ;
		List<Reservation> reservationsList = new ArrayList<>() ;
		reservations.forEach(reservationsList::add) ;
		return reservationsList ;
	}
}

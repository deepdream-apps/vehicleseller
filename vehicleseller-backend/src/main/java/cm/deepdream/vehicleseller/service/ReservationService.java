package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Reservation;
import cm.deepdream.vehicleseller.repository.ReservationRepository;

@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository ;
	
	public Reservation create (Reservation reservation) {
		Reservation savedReservation  = reservationRepository.save(reservation) ;
		return savedReservation ;
	}
	
	
	public Reservation modify (Reservation reservation) {
		Reservation savedReservation  = reservationRepository.save(reservation) ;
		return savedReservation ;
	}
	
	
	public void delete (Reservation reservation) {
		reservationRepository.delete(reservation);
	}
	
	
	public Reservation get (Long id) {
		Reservation savedReservation  = reservationRepository.findById(id).orElseGet(null) ;
		return savedReservation ;
	}
	
	
	public List<Reservation> getAll () {
		Iterable<Reservation> reservations  = reservationRepository.findAll() ;
		List<Reservation> reservationsList = new ArrayList<Reservation>() ;
		reservations.forEach(reservationsList::add) ;
		return reservationsList ;
	}
}

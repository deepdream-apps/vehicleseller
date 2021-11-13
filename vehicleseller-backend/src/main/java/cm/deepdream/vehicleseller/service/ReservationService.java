package cm.deepdream.vehicleseller.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cm.deepdream.vehicleseller.enums.StatusReservation;
import cm.deepdream.vehicleseller.model.Reservation;
import cm.deepdream.vehicleseller.repository.ReservationRepository;
import lombok.extern.log4j.Log4j2;
@Transactional
@Service
@Log4j2
public class ReservationService {
	private ReservationRepository reservationRepository ;
	
	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public Reservation create (Reservation reservation) {
		reservation.setDate(LocalDateTime.now());
		return reservationRepository.save(reservation) ;
	}
	
	public Reservation modify (Reservation reservation) {
		return reservationRepository.save(reservation) ;
	}
	
	public Reservation cancel (Reservation reservation) {
		reservation.setStatus(StatusReservation.CANCELLED.getLabel()) ;
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

package cm.deepdream.vehicleseller.webservice;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cm.deepdream.vehicleseller.enums.StatusReservation;
import cm.deepdream.vehicleseller.model.Reservation;
import cm.deepdream.vehicleseller.service.ReservationService;
@RestController
@RequestMapping("/api/reservation")
public class ReservationWS {
	Logger logger = Logger.getLogger(ReservationWS.class.getName()) ;
	private ReservationService reservationService ;
	
	
	public ReservationWS(ReservationService reservationService) {
		this.reservationService = reservationService;
	}


	@PostMapping("/add")
	public ResponseEntity<Reservation> addReservation(@RequestBody  Reservation reservation){
		logger.info("Reservation = "+ reservation) ;
		reservation.setDate(LocalDateTime.now()) ;
		reservation.setStatus(StatusReservation.WAITING.getLabel()) ;
		reservation.setVehicle(null) ;
	    Reservation newReservation = reservationService.create(reservation) ;
	    return ResponseEntity.ok(newReservation) ;
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation){
	    Optional<Reservation> optReservation = reservationService.get(reservation.getId()) ;
	    if(optReservation.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    
	    Reservation existingRservation = optReservation.get() ;
	    Reservation upadatedReservation = reservationService.modify(existingRservation) ;
	    return ResponseEntity.ok(upadatedReservation);
	}
	
	
	@PutMapping("/cancel")
	public ResponseEntity<Reservation> cancelReservation(@RequestBody Reservation reservation){
		logger.log(Level.INFO, "Cancel the reservation "+reservation) ;
	    Optional<Reservation> optReservation = reservationService.get(reservation.getId()) ;
	    if(optReservation.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    
	    Reservation existingRservation = optReservation.get() ;
	    Reservation upadatedReservation = reservationService.cancel(existingRservation) ;
	    return ResponseEntity.ok(upadatedReservation);
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity deleteReservation(@PathVariable("id") Long id)  {
		Optional<Reservation> optReservation = reservationService.get(id) ;
	    
		if(optReservation.isEmpty()) {
			return ResponseEntity.noContent().build();
	    }
	    reservationService.delete(optReservation.get()) ;
	    return ResponseEntity.ok().build();
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity deleteReservation(@RequestBody Reservation reservation)  {
		logger.log(Level.INFO, "Delete the reservation "+reservation) ;
	    reservationService.delete(reservation) ;
	    return ResponseEntity.ok().build();
	}
	
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Reservation> getReservation(@PathVariable("id") Long id) {
	    Optional<Reservation> optReservation = reservationService.get(id) ;
	    if(optReservation.isEmpty()) {
	    	return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(optReservation.get()) ;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Reservation>> getAllReservations()  {
	    List<Reservation> listReservations = reservationService.getAll() ;
	    return ResponseEntity.ok(listReservations);
	}
}

package cm.deepdream.vehicleseller.webservice;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private ReservationService reservationService ;
	
	
	@PostMapping("/add")
	public ResponseEntity<Reservation> addReservation(@RequestBody  Reservation reservation)  {
		reservation.setDate(LocalDateTime.now()) ;
		reservation.setStatus(StatusReservation.WAITING.getLabel()) ;
		reservation.setCustomer(null) ;
		reservation.setVehicle(null) ;
	    Reservation newReservation = reservationService.create(reservation) ;
	    return ResponseEntity.ok(newReservation) ;
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) throws URISyntaxException {
	    Optional<Reservation> optReservation = reservationService.get(reservation.getId()) ;
	    if(optReservation.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    
	    Reservation existingRservation = optReservation.get() ;
	    Reservation upadatedReservation = reservationService.create(existingRservation) ;
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

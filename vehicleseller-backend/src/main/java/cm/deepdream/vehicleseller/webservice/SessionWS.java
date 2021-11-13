package cm.deepdream.vehicleseller.webservice;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cm.deepdream.vehicleseller.model.Session;
import cm.deepdream.vehicleseller.service.SessionService;

@RestController
@RequestMapping("/api/session")
public class SessionWS {
	private SessionService sessionService ;
	

	public SessionWS(SessionService sessionService) {
		this.sessionService = sessionService;
	}


	@PostMapping("/add")
	public ResponseEntity<Session> addSession(@RequestBody Session session) {
		Session newSession = sessionService.create(session) ;
		return ResponseEntity.ok(newSession) ;
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Session> updateSession(@RequestBody Session session) {
	    Optional<Session> optSession = sessionService.get(session.getId()) ;
	    if(optSession.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    Session existingSession = optSession.get() ;
	    existingSession.setDriver(session.getDriver()) ;
	    existingSession.setEndDate(session.getEndDate()) ;
	    existingSession.setMileageAtBegin(session.getMileageAtBegin()) ;
	    existingSession.setMileageAtEnd(session.getMileageAtEnd()) ;
	    existingSession.setStartDate(session.getStartDate()) ;
	    existingSession.setStatus(session.getStatus()) ;
	    Session upadatedSession = sessionService.modify(existingSession) ;
	    return ResponseEntity.ok(upadatedSession) ;
	}
	
	
	@PutMapping("/end")
	public ResponseEntity<Session> endSession(@RequestBody Session session) {
	    Optional<Session> optSession = sessionService.get(session.getId()) ;
	    if(optSession.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    Session existingSession = optSession.get() ;
	    Session upadatedSession = sessionService.end(existingSession) ;
	    return ResponseEntity.ok(upadatedSession) ;
	}
	
	
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity deleteSession(@PathVariable("id") Long id) {
	    Optional<Session> optSession = sessionService.get(id) ;
	    if(optSession.isEmpty()) {
	    	return ResponseEntity.noContent().build();
	    }
	    sessionService.delete(optSession.get()) ;
	    return ResponseEntity.ok().build();
	}
	

	@GetMapping("/id/{id}")
	public ResponseEntity<Session> getSession(@PathVariable("id") Long id) {
	    Optional<Session> optSession = sessionService.get(id) ;
	    if(optSession.isEmpty()) {
	    	return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(optSession.get()) ;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Session>> getAllSessions()  {
	    List<Session> listCountries = sessionService.getAll() ;
	    return ResponseEntity.ok(listCountries) ;
	}
}

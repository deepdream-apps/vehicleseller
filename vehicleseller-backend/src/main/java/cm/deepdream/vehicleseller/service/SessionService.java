package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cm.deepdream.vehicleseller.enums.StatusSession;
import cm.deepdream.vehicleseller.model.Session;
import cm.deepdream.vehicleseller.model.Vehicle;
import cm.deepdream.vehicleseller.repository.SessionRepository;
import cm.deepdream.vehicleseller.repository.VehicleRepository;
import lombok.extern.log4j.Log4j2;

@Transactional
@Service
@Log4j2
public class SessionService {
	private Logger logger = Logger.getLogger(SessionService.class.getName()) ;
	private SessionRepository sessionRepository ;
	private VehicleRepository vehicleRepository ;
	
	
	
	public SessionService(SessionRepository sessionRepository, VehicleRepository vehicleRepository) {
		this.sessionRepository = sessionRepository;
		this.vehicleRepository = vehicleRepository;
	}


	public Session create (Session session) {
		logger.info("Session = "+session);
		Vehicle vehicle = session.getVehicle() ;
		vehicle.setMileage(session.getMileageAtBegin());
		vehicleRepository.save(vehicle) ;
		
		session.setStatus(StatusSession.EN_COURS.getLabel());
		return sessionRepository.save(session) ;
	}
	
	
	public Session modify (Session session) {
		return sessionRepository.save(session) ;
	}
	
	public Session end (Session session) {
		Vehicle vehicle = session.getVehicle() ;
		vehicle.setMileage(session.getMileageAtEnd());
		vehicleRepository.save(vehicle) ;
		
		session.setStatus(StatusSession.TERMINE.getLabel());
		return sessionRepository.save(session) ;
	}
	
	
	public void delete(Session Session) {
		sessionRepository.delete(Session);
	}
	
	
	public Optional<cm.deepdream.vehicleseller.model.Session> get (Long id) {
		return sessionRepository.findById(id) ;
	}
	
	
	public List<Session> getAll () {
		Iterable<Session> countries  = sessionRepository.findAll() ;
		List<Session> countriesList = new ArrayList<>() ;
		countries.forEach(countriesList::add) ;
		return countriesList ;
	}
}

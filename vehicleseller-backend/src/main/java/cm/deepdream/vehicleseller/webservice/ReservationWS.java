package cm.deepdream.vehicleseller.webservice;
import java.net.URISyntaxException;
import java.util.List;
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
import cm.deepdream.vehicleseller.model.Reservation;
import cm.deepdream.vehicleseller.service.ReservationService;

@Path("/api/reservation")
public class ReservationWS {
	@Autowired
	private ReservationService reservationService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addReservation(Reservation reservation) throws URISyntaxException {
	    Reservation newReservation = reservationService.create(reservation) ;
	    return Response.ok(newReservation).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateReservation(@PathParam("id") Long id, Reservation reservation) throws URISyntaxException {
	    Reservation existingReservation = reservationService.get(id) ;
	    if(existingReservation == null) {
	    	return Response.status(400).build();
	    }
	    Reservation upadatedReservation = reservationService.create(existingReservation) ;
	    return Response.ok(upadatedReservation).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteReservation(@PathParam("id") Long id) throws URISyntaxException {
	    Reservation existingReservation = reservationService.get(id) ;
	    if(existingReservation == null) {
	    	return Response.status(400).build();
	    }
	    reservationService.delete(existingReservation) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getReservation(@PathParam("id") Long id) throws URISyntaxException {
	    Reservation existingReservation = reservationService.get(id) ;
	    if(existingReservation == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingReservation).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllReservations() throws URISyntaxException {
	    List<Reservation> listReservations = reservationService.getAll() ;
	    return Response.ok(listReservations).build();
	}
}

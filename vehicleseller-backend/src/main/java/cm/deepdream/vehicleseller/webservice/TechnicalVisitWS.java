package cm.deepdream.vehicleseller.webservice;
import java.net.URISyntaxException;
import java.util.List;
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
import cm.deepdream.vehicleseller.model.TechnicalVisit;
import cm.deepdream.vehicleseller.service.TechnicalVisitService;

@Path("/api/technical-visit")
@Singleton
public class TechnicalVisitWS {
	@Autowired
	private TechnicalVisitService technicalVisitService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addTechnicalVisit(TechnicalVisit technicalVisit) throws URISyntaxException {
	    TechnicalVisit newTechnicalVisit = technicalVisitService.create(technicalVisit) ;
	    return Response.ok(newTechnicalVisit).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateTechnicalVisit(@PathParam("id") Long id, TechnicalVisit technicalVisit) throws URISyntaxException {
	    TechnicalVisit existingTechnicalVisit = technicalVisitService.get(id) ;
	    if(existingTechnicalVisit == null) {
	    	return Response.status(400).build();
	    }
	    TechnicalVisit upadatedTechnicalVisit = technicalVisitService.create(existingTechnicalVisit) ;
	    return Response.ok(upadatedTechnicalVisit).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteTechnicalVisit(@PathParam("id") Long id) throws URISyntaxException {
	    TechnicalVisit existingTechnicalVisit = technicalVisitService.get(id) ;
	    if(existingTechnicalVisit == null) {
	    	return Response.status(400).build();
	    }
	    technicalVisitService.delete(existingTechnicalVisit) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getTechnicalVisit(@PathParam("id") Long id) throws URISyntaxException {
	    TechnicalVisit existingTechnicalVisit = technicalVisitService.get(id) ;
	    if(existingTechnicalVisit == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingTechnicalVisit).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllTechnicalVisits() throws URISyntaxException {
	    List<TechnicalVisit> listTechnicalVisits = technicalVisitService.getAll() ;
	    return Response.ok(listTechnicalVisits).build();
	}
}

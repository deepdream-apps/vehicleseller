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
import cm.deepdream.vehicleseller.model.Emptying;
import cm.deepdream.vehicleseller.service.EmptyingService;

@Path("/api/emptying")
@Singleton
public class EmptyingWS {
	@Autowired
	private EmptyingService emptyingService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addEmptying(Emptying emptying) throws URISyntaxException {
	    Emptying newEmptying = emptyingService.create(emptying) ;
	    return Response.ok(newEmptying).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateEmptying(@PathParam("id") Long id, Emptying emptying) throws URISyntaxException {
	    Emptying existingEmptying = emptyingService.get(id) ;
	    if(existingEmptying == null) {
	    	return Response.status(400).build();
	    }
	    Emptying upadatedEmptying = emptyingService.create(existingEmptying) ;
	    return Response.ok(upadatedEmptying).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteEmptying(@PathParam("id") Long id) throws URISyntaxException {
	    Emptying existingEmptying = emptyingService.get(id) ;
	    if(existingEmptying == null) {
	    	return Response.status(400).build();
	    }
	    emptyingService.delete(existingEmptying) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getEmptying(@PathParam("id") Long id) throws URISyntaxException {
	    Emptying existingEmptying = emptyingService.get(id) ;
	    if(existingEmptying == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingEmptying).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllEmptyings() throws URISyntaxException {
	    List<Emptying> listEmptyings = emptyingService.getAll() ;
	    return Response.ok(listEmptyings).build();
	}
}

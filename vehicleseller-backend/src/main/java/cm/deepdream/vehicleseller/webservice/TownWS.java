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
import cm.deepdream.vehicleseller.model.Town;
import cm.deepdream.vehicleseller.service.TownService;
import lombok.extern.slf4j.Slf4j;

@Path("/api/town")
@Slf4j
public class TownWS {
	@Autowired
	private TownService townService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addTown(Town town) throws URISyntaxException {
	    if(town.getLabel() == null || town.getLabel().equals("")) {
	            return Response.status(400).entity("Please provide all mandatory inputs").build();
	     }
	    Town newTown = townService.create(town) ;
	    return Response.ok(newTown).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateTown(@PathParam("id") Long id, Town town) throws URISyntaxException {
	    if(town.getLabel() == null || town.getLabel().equals("")) {
	         return Response.status(400).build();
	     }
	    Town existingTown = townService.get(id) ;
	    if(existingTown == null) {
	    	return Response.status(400).build();
	    }
	    existingTown.setLabel(town.getLabel());
	    existingTown.setCode(town.getCode());
	    Town upadatedTown = townService.create(existingTown) ;
	    return Response.ok(upadatedTown).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteTown(@PathParam("id") Long id) throws URISyntaxException {
	    Town existingTown = townService.get(id) ;
	    if(existingTown == null) {
	    	return Response.status(400).build();
	    }
	    townService.delete(existingTown) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getTown(@PathParam("id") Long id) throws URISyntaxException {
	    Town existingTown = townService.get(id) ;
	    if(existingTown == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingTown).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllTowns() throws URISyntaxException {
	    List<Town> listTowns = townService.getAll() ;
	    return Response.ok(listTowns).build();
	}
}

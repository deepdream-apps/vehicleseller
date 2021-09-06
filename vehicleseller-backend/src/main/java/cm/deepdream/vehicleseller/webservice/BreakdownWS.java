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
import cm.deepdream.vehicleseller.model.Breakdown;
import cm.deepdream.vehicleseller.service.BreakdownService;

@Path("/api/breakdown")
public class BreakdownWS {
	@Autowired
	private BreakdownService breakdownService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addBreakdown(Breakdown breakdown) throws URISyntaxException {
	    if(breakdown.getLabel() == null || breakdown.getLabel().equals("")) {
	            return Response.status(400).entity("Please provide all mandatory inputs").build();
	     }
	    Breakdown newBreakdown = breakdownService.create(breakdown) ;
	    return Response.ok(newBreakdown).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateBreakdown(@PathParam("id") Long id, Breakdown breakdown) throws URISyntaxException {
	    if(breakdown.getLabel() == null || breakdown.getLabel().equals("")) {
	         return Response.status(400).build();
	     }
	    Breakdown existingBreakdown = breakdownService.get(id) ;
	    if(existingBreakdown == null) {
	    	return Response.status(400).build();
	    }
	    existingBreakdown.setLabel(breakdown.getLabel());
	    Breakdown upadatedBreakdown = breakdownService.create(existingBreakdown) ;
	    return Response.ok(upadatedBreakdown).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteBreakdown(@PathParam("id") Long id) throws URISyntaxException {
	    Breakdown existingBreakdown = breakdownService.get(id) ;
	    if(existingBreakdown == null) {
	    	return Response.status(400).build();
	    }
	    breakdownService.delete(existingBreakdown) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getBreakdown(@PathParam("id") Long id) throws URISyntaxException {
	    Breakdown existingBreakdown = breakdownService.get(id) ;
	    if(existingBreakdown == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingBreakdown).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllBreakdowns() throws URISyntaxException {
	    List<Breakdown> listBreakdowns = breakdownService.getAll() ;
	    return Response.ok(listBreakdowns).build();
	}
}

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
import org.springframework.core.env.Environment;
import cm.deepdream.vehicleseller.model.VehicleAssignment;
import cm.deepdream.vehicleseller.service.VehicleAssignmentService;

@Path("/api/vehicle-assignment")
public class VehicleAssignmentWS {
	@Autowired
	private VehicleAssignmentService vehicleAssignmentService ;
	@Autowired
	private Environment env ;
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addVehicleAssignment(VehicleAssignment vehicleAssignment) throws URISyntaxException {
	   
	    VehicleAssignment newVehicleAssignment = vehicleAssignmentService.create(vehicleAssignment) ;
	    return Response.ok(newVehicleAssignment).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateVehicleAssignment(@PathParam("id") Long id, VehicleAssignment vehicleAssignment) throws URISyntaxException {
	  
	    VehicleAssignment existingVehicleAssignment = vehicleAssignmentService.get(id) ;
	    if(existingVehicleAssignment == null) {
	    	return Response.status(400).build();
	    }
	    VehicleAssignment upadatedVehicleAssignment = vehicleAssignmentService.create(existingVehicleAssignment) ;
	    return Response.ok(upadatedVehicleAssignment).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteVehicleAssignment(@PathParam("id") Long id) throws URISyntaxException {
	    VehicleAssignment existingVehicleAssignment = vehicleAssignmentService.get(id) ;
	    if(existingVehicleAssignment == null) {
	    	return Response.status(400).build();
	    }
	    vehicleAssignmentService.delete(existingVehicleAssignment) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getVehicleAssignment(@PathParam("id") Long id) throws URISyntaxException {
	    VehicleAssignment existingVehicleAssignment = vehicleAssignmentService.get(id) ;
	    if(existingVehicleAssignment == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingVehicleAssignment).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllVehicleAssignments() throws URISyntaxException {
	    List<VehicleAssignment> listVehicleAssignments = vehicleAssignmentService.getAll() ;
	    return Response.ok(listVehicleAssignments).build();
	}
}

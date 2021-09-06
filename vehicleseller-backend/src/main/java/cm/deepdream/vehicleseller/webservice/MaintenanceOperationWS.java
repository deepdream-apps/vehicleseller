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
import cm.deepdream.vehicleseller.model.MaintenanceOperation;
import cm.deepdream.vehicleseller.service.MaintenanceOperationService;
import lombok.extern.slf4j.Slf4j;

@Path("/api/maintenance-operation")
public class MaintenanceOperationWS {
	@Autowired
	private MaintenanceOperationService maintenanceOperationService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addMaintenanceOperation(MaintenanceOperation maintenanceOperation) throws URISyntaxException {
	    MaintenanceOperation newMaintenanceOperation = maintenanceOperationService.create(maintenanceOperation) ;
	    return Response.ok(newMaintenanceOperation).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateMaintenanceOperation(@PathParam("id") Long id, MaintenanceOperation maintenanceOperation) throws URISyntaxException {
	    MaintenanceOperation existingMaintenanceOperation = maintenanceOperationService.get(id) ;
	    if(existingMaintenanceOperation == null) {
	    	return Response.status(400).build();
	    }
	    MaintenanceOperation upadatedMaintenanceOperation = maintenanceOperationService.create(existingMaintenanceOperation) ;
	    return Response.ok(upadatedMaintenanceOperation).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteMaintenanceOperation(@PathParam("id") Long id) throws URISyntaxException {
	    MaintenanceOperation existingMaintenanceOperation = maintenanceOperationService.get(id) ;
	    if(existingMaintenanceOperation == null) {
	    	return Response.status(400).build();
	    }
	    maintenanceOperationService.delete(existingMaintenanceOperation) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getMaintenanceOperation(@PathParam("id") Long id) throws URISyntaxException {
	    MaintenanceOperation existingMaintenanceOperation = maintenanceOperationService.get(id) ;
	    if(existingMaintenanceOperation == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingMaintenanceOperation).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllMaintenanceOperations() throws URISyntaxException {
	    List<MaintenanceOperation> listMaintenanceOperations = maintenanceOperationService.getAll() ;
	    return Response.ok(listMaintenanceOperations).build();
	}
}

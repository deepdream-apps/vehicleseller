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
import cm.deepdream.vehicleseller.model.Driver;
import cm.deepdream.vehicleseller.service.DriverService;
import lombok.extern.slf4j.Slf4j;

@Path("/api/driver")
@Slf4j
public class DriverWS {
	@Autowired
	private DriverService driverService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addDriver(Driver driver) throws URISyntaxException {
	    Driver newDriver = driverService.create(driver) ;
	    return Response.ok(newDriver).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateDriver(@PathParam("id") Long id, Driver driver) throws URISyntaxException {
	    Driver existingDriver = driverService.get(id) ;
	    if(existingDriver == null) {
	    	return Response.status(400).build();
	    }
	    Driver upadatedDriver = driverService.create(existingDriver) ;
	    return Response.ok(upadatedDriver).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteDriver(@PathParam("id") Long id) throws URISyntaxException {
	    Driver existingDriver = driverService.get(id) ;
	    if(existingDriver == null) {
	    	return Response.status(400).build();
	    }
	    driverService.delete(existingDriver) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getDriver(@PathParam("id") Long id) throws URISyntaxException {
	    Driver existingDriver = driverService.get(id) ;
	    if(existingDriver == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingDriver).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllDrivers() throws URISyntaxException {
	    List<Driver> listCountries = driverService.getAll() ;
	    return Response.ok(listCountries).build();
	}
}

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

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import cm.deepdream.vehicleseller.model.Brand;
import cm.deepdream.vehicleseller.model.Model;
import cm.deepdream.vehicleseller.model.Vehicle;
import cm.deepdream.vehicleseller.service.VehicleService;
import lombok.extern.slf4j.Slf4j;

@Path("/api/vehicle")
@Slf4j
public class VehicleWS {
	@Autowired
	private VehicleService vehicleService ;
	@Autowired
	private Environment env ;
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addVehicle(Vehicle vehicle) throws URISyntaxException {
	    if(vehicle.getLabel() == null || vehicle.getLabel().equals("")) {
	            return Response.status(400).entity("Please provide all mandatory inputs").build();
	     }
	    Vehicle newVehicle = vehicleService.create(vehicle) ;
	    return Response.ok(newVehicle).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateVehicle(@PathParam("id") Long id, Vehicle vehicle) throws URISyntaxException {
	    if(vehicle.getLabel() == null || vehicle.getLabel().equals("")) {
	         return Response.status(400).build();
	     }
	    Vehicle existingVehicle = vehicleService.get(id) ;
	    if(existingVehicle == null) {
	    	return Response.status(400).build();
	    }
	    existingVehicle.setLabel(vehicle.getLabel());
	    Vehicle upadatedVehicle = vehicleService.create(existingVehicle) ;
	    return Response.ok(upadatedVehicle).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteVehicle(@PathParam("id") Long id) throws URISyntaxException {
	    Vehicle existingVehicle = vehicleService.get(id) ;
	    if(existingVehicle == null) {
	    	return Response.status(400).build();
	    }
	    vehicleService.delete(existingVehicle) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getVehicle(@PathParam("id") Long id) throws URISyntaxException {
	    Vehicle existingVehicle = vehicleService.get(id) ;
	    if(existingVehicle == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingVehicle).build();
	}
	
	
	@GET
	@Path("/search/{brandId}/{modelId}/{fuel}/{yearFrom}/{yearTo}/{mileageMin}/{mileageMax}/{priceMin}/{priceMax}")
	@Produces("application/json")
	public Response getVehicles(@PathParam("brandId") Long brandId, @PathParam("modelId") Long modelId,
			@PathParam("fuel") String fuel, @PathParam("yearFrom") Long yearFrom, @PathParam("yearTo") Long yearTo,
			@PathParam("mileageMin") Long mileageMin, @PathParam("mileageMax") Long mileageMax, @PathParam("priceMin") Long priceMin, 
			@PathParam("priceMax") Long priceMax) throws URISyntaxException {
		
		int choice = - 1 ;
		
		Brand brand = new Brand() ;
		brand.setId(brandId);
		
		Model model = new Model() ;
		model.setId(modelId);
		
		List<Vehicle> listVehicles = vehicleService.get(brand, model, yearFrom, fuel, yearTo, 
				mileageMin, mileageMax, priceMin, priceMax, choice) ;
	    return Response.ok(listVehicles).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllVehicles() throws URISyntaxException {
	    List<Vehicle> listVehicles = vehicleService.getAll() ;
	    return Response.ok(listVehicles).build();
	}
}

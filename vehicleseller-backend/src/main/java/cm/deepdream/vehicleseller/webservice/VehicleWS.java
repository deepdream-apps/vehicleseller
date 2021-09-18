package cm.deepdream.vehicleseller.webservice;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import cm.deepdream.vehicleseller.model.Brand;
import cm.deepdream.vehicleseller.model.Model;
import cm.deepdream.vehicleseller.model.Vehicle;
import cm.deepdream.vehicleseller.service.VehicleService;

@Path("/api/vehicle")
public class VehicleWS {
	private Logger loggger = Logger.getLogger(VehicleWS.class.getName()) ;
	@Autowired
	private VehicleService vehicleService ;
	@Autowired
	private Environment env ;
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addVehicle(Vehicle vehicle) throws URISyntaxException {
		loggger.info("Add vehicle "+vehicle);
	    Vehicle newVehicle = vehicleService.create(vehicle) ;
	    return Response.ok(newVehicle).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateVehicle(@PathParam("id") Long id, Vehicle vehicle) throws URISyntaxException {
	    Vehicle existingVehicle = vehicleService.get(id) ;
	    if(existingVehicle == null) {
	    	return Response.status(400).build();
	    }
	    
	    existingVehicle.setColor(vehicle.getColor());
	    existingVehicle.setDescription(vehicle.getDescription());
	    existingVehicle.setDoors(vehicle.getDoors());
	    existingVehicle.setModel(vehicle.getModel());
	    existingVehicle.setPicture(vehicle.getPicture());
	    existingVehicle.setRegistrationNumber(vehicle.getRegistrationNumber());
	    existingVehicle.setSeats(vehicle.getSeats());
	    existingVehicle.setStatus(vehicle.getStatus());
	    existingVehicle.setYear(vehicle.getYear());
	    Vehicle upadatedVehicle = vehicleService.create(existingVehicle) ;
	    return Response.ok(upadatedVehicle).build();
	}
	
	
	
	@DELETE
	@Path("/id/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
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
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVehicle(@PathParam("id") Long id) throws URISyntaxException {
	    Vehicle existingVehicle = vehicleService.get(id) ;
	    if(existingVehicle == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingVehicle).build();
	}
	
	
	@GET
	@Path("/search/{brandId}/{modelId}/{fuel}/{yearFrom}/{yearTo}/{mileageMin}/{mileageMax}/{priceMin}/{priceMax}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVehicles(@PathParam("brandId") Long brandId, @PathParam("modelId") Long modelId,
			@PathParam("fuel") String fuel, @PathParam("yearFrom") Long yearFrom, @PathParam("yearTo") Long yearTo,
			@PathParam("mileageMin") Long mileageMin, @PathParam("mileageMax") Long mileageMax, @PathParam("priceMin") Long priceMin, 
			@PathParam("priceMax") Long priceMax) throws URISyntaxException {
		
		int choice = - 1 ;
		
		Brand brand = new Brand() ;
		brand.setId(brandId);
		
		Model model = new Model() ;
		model.setId(modelId);
		
		if(brandId != 0 && modelId == 0 && ! fuel.equals("empty")) {
			choice = 0 ;  
		}else if(brandId != 0 && modelId == 0 && ! fuel.equals("empty")) {
			choice = 1 ;  
		}else if(brandId != 0 && modelId == 0 && ! fuel.equals("empty")) {
			choice = 2 ;  
		}else if(brandId != 0 && modelId == 0 && ! fuel.equals("empty")) {
			choice = 3 ;  
		}else if(brandId != 0 && modelId == 0 && ! fuel.equals("empty")) {
			choice = 4 ;  
		} else if(brandId != 0 && modelId == 0 && ! fuel.equals("empty")) {
			choice = 5 ;  
		}
		
		List<Vehicle> listVehicles = vehicleService.get(brand, model, yearFrom, fuel, yearTo, 
				mileageMin, mileageMax, priceMin, priceMax, choice) ;
	    return Response.ok(listVehicles).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllVehicles() throws URISyntaxException {
	    List<Vehicle> listVehicles = vehicleService.getAll() ;
	    return Response.ok(listVehicles).build();
	}
}

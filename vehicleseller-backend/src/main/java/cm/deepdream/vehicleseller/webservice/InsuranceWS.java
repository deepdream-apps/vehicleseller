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
import cm.deepdream.vehicleseller.model.Insurance;
import cm.deepdream.vehicleseller.service.InsuranceService;
@Path("/api/insurance")
@Singleton
public class InsuranceWS {
	@Autowired
	private InsuranceService insuranceService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addInsurance(Insurance insurance) throws URISyntaxException {
	    if(insurance.getLabel() == null || insurance.getLabel().equals("")) {
	            return Response.status(400).entity("Please provide all mandatory inputs").build();
	     }
	    Insurance newInsurance = insuranceService.create(insurance) ;
	    return Response.ok(newInsurance).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateInsurance(@PathParam("id") Long id, Insurance insurance) throws URISyntaxException {
	    if(insurance.getLabel() == null || insurance.getLabel().equals("")) {
	         return Response.status(400).build();
	     }
	    Insurance existingInsurance = insuranceService.get(id) ;
	    if(existingInsurance == null) {
	    	return Response.status(400).build();
	    }
	    existingInsurance.setLabel(insurance.getLabel());
	    Insurance upadatedInsurance = insuranceService.create(existingInsurance) ;
	    return Response.ok(upadatedInsurance).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteInsurance(@PathParam("id") Long id) throws URISyntaxException {
	    Insurance existingInsurance = insuranceService.get(id) ;
	    if(existingInsurance == null) {
	    	return Response.status(400).build();
	    }
	    insuranceService.delete(existingInsurance) ;
	    return Response.ok(1).build();
	}
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getInsurance(@PathParam("id") Long id) throws URISyntaxException {
	    Insurance existingInsurance = insuranceService.get(id) ;
	    if(existingInsurance == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingInsurance).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllInsurances() throws URISyntaxException {
	    List<Insurance> listInsurances = insuranceService.getAll() ;
	    return Response.ok(listInsurances).build();
	}
}

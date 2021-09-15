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
import cm.deepdream.vehicleseller.model.InsuranceContract;
import cm.deepdream.vehicleseller.service.InsuranceContractService;
@Path("/api/insurance-contract")
@Singleton
public class InsuranceContractWS {
	@Autowired
	private InsuranceContractService insuranceContractService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addInsuranceContract(InsuranceContract insuranceContract) throws URISyntaxException {
	    InsuranceContract newInsuranceContract = insuranceContractService.create(insuranceContract) ;
	    return Response.ok(newInsuranceContract).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateInsuranceContract(@PathParam("id") Long id, InsuranceContract insuranceContract) throws URISyntaxException {
	    InsuranceContract existingInsuranceContract = insuranceContractService.get(id) ;
	    if(existingInsuranceContract == null) {
	    	return Response.status(400).build();
	    }
	    InsuranceContract upadatedInsuranceContract = insuranceContractService.create(existingInsuranceContract) ;
	    return Response.ok(upadatedInsuranceContract).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteInsuranceContract(@PathParam("id") Long id) throws URISyntaxException {
	    InsuranceContract existingInsuranceContract = insuranceContractService.get(id) ;
	    if(existingInsuranceContract == null) {
	    	return Response.status(400).build();
	    }
	    insuranceContractService.delete(existingInsuranceContract) ;
	    return Response.ok(1).build();
	}
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getInsuranceContract(@PathParam("id") Long id) throws URISyntaxException {
	    InsuranceContract existingInsuranceContract = insuranceContractService.get(id) ;
	    if(existingInsuranceContract == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingInsuranceContract).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllInsuranceContracts() throws URISyntaxException {
	    List<InsuranceContract> listInsuranceContracts = insuranceContractService.getAll() ;
	    return Response.ok(listInsuranceContracts).build();
	}
}

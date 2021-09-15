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
import cm.deepdream.vehicleseller.model.Customer;
import cm.deepdream.vehicleseller.service.CustomerService;
@Path("/api/customer")
@Singleton
public class CustomerWS {
	@Autowired
	private CustomerService customerService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addCustomer(Customer customer) throws URISyntaxException {
	    Customer newCustomer = customerService.create(customer) ;
	    return Response.ok(newCustomer).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateCustomer(@PathParam("id") Long id, Customer customer) throws URISyntaxException {
	    Customer existingCustomer = customerService.get(id) ;
	    if(existingCustomer == null) {
	    	return Response.status(400).build();
	    }
	    Customer upadatedCustomer = customerService.create(existingCustomer) ;
	    return Response.ok(upadatedCustomer).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteCustomer(@PathParam("id") Long id) throws URISyntaxException {
	    Customer existingCustomer = customerService.get(id) ;
	    if(existingCustomer == null) {
	    	return Response.status(400).build();
	    }
	    customerService.delete(existingCustomer) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getCustomer(@PathParam("id") Long id) throws URISyntaxException {
	    Customer existingCustomer = customerService.get(id) ;
	    if(existingCustomer == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingCustomer).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllCustomers() throws URISyntaxException {
	    List<Customer> listCountries = customerService.getAll() ;
	    return Response.ok(listCountries).build();
	}
}

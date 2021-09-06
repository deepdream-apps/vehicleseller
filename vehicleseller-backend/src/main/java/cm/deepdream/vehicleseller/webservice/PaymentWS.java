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
import cm.deepdream.vehicleseller.model.Payment;
import cm.deepdream.vehicleseller.service.PaymentService;

@Path("/api/payment")
public class PaymentWS {
	@Autowired
	private PaymentService paymentService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPayment(Payment payment) throws URISyntaxException {
	    Payment newPayment = paymentService.create(payment) ;
	    return Response.ok(newPayment).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updatePayment(@PathParam("id") Long id, Payment payment) throws URISyntaxException {
	    Payment existingPayment = paymentService.get(id) ;
	    if(existingPayment == null) {
	    	return Response.status(400).build();
	    }
	    Payment upadatedPayment = paymentService.create(existingPayment) ;
	    return Response.ok(upadatedPayment).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deletePayment(@PathParam("id") Long id) throws URISyntaxException {
	    Payment existingPayment = paymentService.get(id) ;
	    if(existingPayment == null) {
	    	return Response.status(400).build();
	    }
	    paymentService.delete(existingPayment) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getPayment(@PathParam("id") Long id) throws URISyntaxException {
	    Payment existingPayment = paymentService.get(id) ;
	    if(existingPayment == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingPayment).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllPayments() throws URISyntaxException {
	    List<Payment> listPayments = paymentService.getAll() ;
	    return Response.ok(listPayments).build();
	}
}

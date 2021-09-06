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
import cm.deepdream.vehicleseller.model.Bill;
import cm.deepdream.vehicleseller.service.BillService;

@Path("/api/bill")
public class BillWS {
	@Autowired
	private BillService billService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addBill(Bill bill) throws URISyntaxException {
	    if(bill.getLabel() == null || bill.getLabel().equals("")) {
	            return Response.status(400).entity("Please provide all mandatory inputs").build();
	     }
	    Bill newBill = billService.create(bill) ;
	    return Response.ok(newBill).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateBill(@PathParam("id") Long id, Bill bill) throws URISyntaxException {
	    if(bill.getLabel() == null || bill.getLabel().equals("")) {
	         return Response.status(400).build();
	     }
	    Bill existingBill = billService.get(id) ;
	    if(existingBill == null) {
	    	return Response.status(400).build();
	    }
	    existingBill.setLabel(bill.getLabel());
	    Bill upadatedBill = billService.create(existingBill) ;
	    return Response.ok(upadatedBill).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteBill(@PathParam("id") Long id) throws URISyntaxException {
	    Bill existingBill = billService.get(id) ;
	    if(existingBill == null) {
	    	return Response.status(400).build();
	    }
	    billService.delete(existingBill) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getBill(@PathParam("id") Long id) throws URISyntaxException {
	    Bill existingBill = billService.get(id) ;
	    if(existingBill == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingBill).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllBills() throws URISyntaxException {
	    List<Bill> listBills = billService.getAll() ;
	    return Response.ok(listBills).build();
	}
}

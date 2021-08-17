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
import cm.deepdream.vehicleseller.model.Seller;
import cm.deepdream.vehicleseller.service.SellerService;
import lombok.extern.slf4j.Slf4j;

@Path("/api/seller")
@Slf4j
public class SellerWS {
	@Autowired
	private SellerService sellerService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSeller(Seller seller) throws URISyntaxException {
	    if(seller.getLabel() == null || seller.getLabel().equals("")) {
	            return Response.status(400).entity("Please provide all mandatory inputs").build();
	     }
	    Seller newSeller = sellerService.create(seller) ;
	    return Response.ok(newSeller).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateSeller(@PathParam("id") Long id, Seller seller) throws URISyntaxException {
	    if(seller.getLabel() == null || seller.getLabel().equals("")) {
	         return Response.status(400).build();
	     }
	    Seller existingSeller = sellerService.get(id) ;
	    if(existingSeller == null) {
	    	return Response.status(400).build();
	    }
	    existingSeller.setLabel(seller.getLabel());
	    Seller upadatedSeller = sellerService.create(existingSeller) ;
	    return Response.ok(upadatedSeller).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteSeller(@PathParam("id") Long id) throws URISyntaxException {
	    Seller existingSeller = sellerService.get(id) ;
	    if(existingSeller == null) {
	    	return Response.status(400).build();
	    }
	    sellerService.delete(existingSeller) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getSeller(@PathParam("id") Long id) throws URISyntaxException {
	    Seller existingSeller = sellerService.get(id) ;
	    if(existingSeller == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingSeller).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllSellers() throws URISyntaxException {
	    List<Seller> listSellers = sellerService.getAll() ;
	    return Response.ok(listSellers).build();
	}
}

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import cm.deepdream.vehicleseller.model.Brand;
import cm.deepdream.vehicleseller.service.BrandService;

@Path("/api/brand")
public class BrandWS {
	@Autowired
	private BrandService brandService ;
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBrand(Brand brand) throws URISyntaxException {
	    if(brand.getLabel() == null || brand.getLabel().equals("")) {
	            return Response.status(400).entity("Please provide all mandatory inputs").build();
	     }
	    Brand newBrand = brandService.create(brand) ;
	    return Response.ok(newBrand).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBrand(@PathParam("id") Long id, Brand brand) throws URISyntaxException {
	    if(brand.getLabel() == null || brand.getLabel().equals("")) {
	         return Response.status(400).build();
	     }
	    Brand existingBrand = brandService.get(id) ;
	    if(existingBrand == null) {
	    	return Response.status(400).build();
	    }
	    existingBrand.setLabel(brand.getLabel());
	    existingBrand.setDescription(brand.getDescription());
	    Brand upadatedBrand = brandService.create(existingBrand) ;
	    return Response.ok(upadatedBrand).build();
	}
	
	
	
	@DELETE
	@Path("/id/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteBrand(@PathParam("id") Long id) throws URISyntaxException {
	    Brand existingBrand = brandService.get(id) ;
	    if(existingBrand == null) {
	    	return Response.status(400).build();
	    }
	    brandService.delete(existingBrand) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBrand(@PathParam("id") Long id) throws URISyntaxException {
	    Brand existingBrand = brandService.get(id) ;
	    if(existingBrand == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingBrand).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBrands() throws URISyntaxException {
	    List<Brand> listBrands = brandService.getAll() ;
	    return Response.ok(listBrands).build();
	}
}

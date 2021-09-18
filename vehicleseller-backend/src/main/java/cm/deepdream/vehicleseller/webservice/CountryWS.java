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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import cm.deepdream.vehicleseller.model.Country;
import cm.deepdream.vehicleseller.service.CountryService;

@Path("/api/country")
@Singleton
public class CountryWS {
	@Autowired
	private CountryService countryService ;
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCountry(Country country) throws URISyntaxException {
	    if(country.getLabel() == null || country.getLabel().equals("")) {
	          return Response.status(400).entity("Please provide all mandatory inputs").build();
	     }
	    Country newCountry = countryService.create(country) ;
	    return Response.ok(newCountry).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCountry(@PathParam("id") Long id, Country country) throws URISyntaxException {
	    if(country.getLabel() == null || country.getLabel().equals("")) {
	         return Response.status(400).build();
	     }
	    Country existingCountry = countryService.get(id) ;
	    if(existingCountry == null) {
	    	return Response.status(400).build();
	    }
	    existingCountry.setLabel(country.getLabel());
	    existingCountry.setCode(country.getCode());
	    Country upadatedCountry = countryService.create(existingCountry) ;
	    return Response.ok(upadatedCountry).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteCountry(@PathParam("id") Long id) throws URISyntaxException {
	    Country existingCountry = countryService.get(id) ;
	    if(existingCountry == null) {
	    	return Response.status(400).build();
	    }
	    countryService.delete(existingCountry) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCountry(@PathParam("id") Long id) throws URISyntaxException {
	    Country existingCountry = countryService.get(id) ;
	    if(existingCountry == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingCountry).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCountrys() throws URISyntaxException {
	    List<Country> listCountries = countryService.getAll() ;
	    return Response.ok(listCountries).build();
	}
}

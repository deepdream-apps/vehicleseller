package cm.deepdream.vehicleseller.webservice;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.core.io.ClassPathResource;


@Path("/api/list")
@Singleton
public class ListItemWS {
	private Logger logger = Logger.getLogger(ListItemWS.class.getName()) ;
	
	
	@GET
	@Path("/countries")
	@Produces("application/json")
	public Response getAllCountries() throws URISyntaxException {
	    try(Stream<String> lines = Files.lines(Paths.get(new ClassPathResource(
	    	      "data/countries.dat").getFile().getAbsolutePath()))){
	    	List<String> listCountries = lines
	    			.distinct()
	    			.sorted()
	    			.map(String::trim)
	    			.collect(Collectors.toList()) ;
		    return Response.ok(listCountries).build();
	    }catch(IOException ex) {
	    	logger.log(Level.SEVERE, ex.getMessage(), ex);
	    	return Response.serverError().build() ;
	    }
	}
	
	
	@GET
	@Path("/professions")
	@Produces("application/json")
	public Response getAllProfessions() throws URISyntaxException {
	    try(Stream<String> lines = Files.lines(Paths.get(new ClassPathResource(
	    	      "data/professions.dat").getFile().getAbsolutePath()))){
	    	List<String> listProfessions = lines
	    			.distinct()
	    			.map(String::trim)
	    			.collect(Collectors.toList()) ;
		    return Response.ok(listProfessions).build();
	    }catch(IOException ex) {
	    	logger.log(Level.SEVERE, ex.getMessage(), ex);
	    	return Response.serverError().build() ;
	    }
	}
	
	
	@GET
	@Path("/nationalities")
	@Produces("application/json")
	public Response getAllNationalties() throws URISyntaxException {
	    try(Stream<String> lines = Files.lines(Paths.get(new ClassPathResource(
	    	      "data/nationalities.dat").getFile().getAbsolutePath()))){
	    	List<String> listNationalities = lines
	    			.distinct()
	    			.sorted()
	    			.map(String::trim)
	    			.collect(Collectors.toList()) ;
		    return Response.ok(listNationalities).build();
	    }catch(IOException ex) {
	    	logger.log(Level.SEVERE, ex.getMessage(), ex);
	    	return Response.serverError().build() ;
	    }
	}
}

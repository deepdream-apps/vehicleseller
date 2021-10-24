package cm.deepdream.vehicleseller.webservice;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/list")
public class ListItemWS {
	private Logger logger = Logger.getLogger(ListItemWS.class.getName()) ;
	
	
	@GetMapping("/countries")
	public ResponseEntity<List<String>> getAllCountries() {
	    try(Stream<String> lines = Files.lines(Paths.get(new ClassPathResource(
	    	      "data/countries.dat").getFile().getAbsolutePath()))){
	    	List<String> listCountries = lines
	    			.distinct()
	    			.sorted()
	    			.map(String::trim)
	    			.collect(Collectors.toList()) ;
		    return ResponseEntity.ok(listCountries);
	    }catch(IOException ex) {
	    	logger.log(Level.SEVERE, ex.getMessage(), ex);
	    	return ResponseEntity.noContent().build() ;
	    }
	}
	
	
	@GetMapping("/professions")
	public ResponseEntity<List<String>> getAllProfessions()  {
	    try(Stream<String> lines = Files.lines(Paths.get(new ClassPathResource(
	    	      "data/professions.dat").getFile().getAbsolutePath()))){
	    	List<String> listProfessions = lines
	    			.distinct()
	    			.map(String::trim)
	    			.collect(Collectors.toList()) ;
		    return ResponseEntity.ok(listProfessions) ;
	    }catch(IOException ex) {
	    	logger.log(Level.SEVERE, ex.getMessage(), ex);
	    	return ResponseEntity.noContent().build() ;
	    }
	}
	
	
	@GetMapping("/nationalities")
	public ResponseEntity<List<String>> getAllNationalties()  {
	    try(Stream<String> lines = Files.lines(Paths.get(new ClassPathResource(
	    	      "data/nationalities.dat").getFile().getAbsolutePath()))){
	    	List<String> listNationalities = lines
	    			.distinct()
	    			.sorted()
	    			.map(String::trim)
	    			.collect(Collectors.toList()) ;
		    return ResponseEntity.ok(listNationalities);
	    }catch(IOException ex) {
	    	logger.log(Level.SEVERE, ex.getMessage(), ex);
	    	return ResponseEntity.noContent().build() ;
	    }
	}
	
	

	
	@GetMapping("/categories")
	public ResponseEntity<List<String>> getAllCategories() {
	    try(Stream<String> lines = Files.lines(Paths.get(new ClassPathResource(
	    	      "data/categories.dat").getFile().getAbsolutePath()))){
	    	List<String> listCategories = lines
	    			.distinct()
	    			.sorted()
	    			.map(String::trim)
	    			.collect(Collectors.toList()) ;
		    return ResponseEntity.ok(listCategories);
	    }catch(IOException ex) {
	    	logger.log(Level.SEVERE, ex.getMessage(), ex);
	    	return ResponseEntity.noContent().build() ;
	    }
	}
	
	
	
	@GetMapping("/prestations")
	public ResponseEntity<List<String>> getAllPrestations()  {
	    try(Stream<String> lines = Files.lines(Paths.get(new ClassPathResource(
	    	      "data/prestations.dat").getFile().getAbsolutePath()))){
	    	List<String> listPrestations = lines
	    			.distinct()
	    			.sorted()
	    			.map(String::trim)
	    			.collect(Collectors.toList()) ;
		    return ResponseEntity.ok(listPrestations);
	    }catch(IOException ex) {
	    	logger.log(Level.SEVERE, ex.getMessage(), ex);
	    	return ResponseEntity.noContent().build() ;
	    }
	}
	
	
	@GetMapping("/towns")
	public ResponseEntity<List<String>> getAllTowns() {
	    try(Stream<String> lines = Files.lines(Paths.get(new ClassPathResource(
	    	      "data/towns.dat").getFile().getAbsolutePath()))){
	    	List<String> listTowns = lines
	    			.distinct()
	    			.sorted()
	    			.map(String::trim)
	    			.collect(Collectors.toList()) ;
		    return ResponseEntity.ok(listTowns) ;
	    }catch(IOException ex) {
	    	logger.log(Level.SEVERE, ex.getMessage(), ex);
	    	return ResponseEntity.noContent().build() ;
	    }
	}
}

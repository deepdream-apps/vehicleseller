package cm.deepdream.vehicleseller.webservice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cm.deepdream.vehicleseller.model.Country;
import cm.deepdream.vehicleseller.service.CountryService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/country")
public class CountryWS {
	@Autowired
	private CountryService countryService ;
	
	@ApiOperation(value = "Add a country")
	@PostMapping(value = "/add", consumes = "application/json")
	public ResponseEntity<Country> addCountry(@RequestBody  Country country) {
	    if(country.getLabel() == null || country.getLabel().isBlank()) {
	          return ResponseEntity.status(400).build();
	     }
	    
	    Country newCountry = countryService.create(country) ;
	    return ResponseEntity.ok(newCountry);
	}
	
	
	@ApiOperation(value = "Update a country")
	@PutMapping(value = "/update", consumes = "application/json")
	public  ResponseEntity<Country> updateCountry(@RequestBody  Country country)  {
	    if(country.getLabel() == null || country.getLabel().equals("")) {
	    	return ResponseEntity.status(400).build();
	     }
	    
	    Country existingCountry = countryService.get(country.getId()) ;
	    
	    if(existingCountry == null) {
	    	return ResponseEntity.status(400).build();
	    }
	    
	    existingCountry.setLabel(country.getLabel());
	    existingCountry.setCode(country.getCode());
	    Country upadatedCountry = countryService.create(existingCountry) ;
	    return ResponseEntity.ok(upadatedCountry) ;
	}
	
	
	@ApiOperation(value = "Delete a country")
	@DeleteMapping("/{id}")
	public ResponseEntity<Country> deleteCountry(@PathVariable("id") Long id)  {
	    Country existingCountry = countryService.get(id) ;
	    
	    if(existingCountry == null) {
	    	return ResponseEntity.status(400).build();
	    }
	    
	    countryService.delete(existingCountry) ;
	    return ResponseEntity.ok(existingCountry) ;
	}
	
	
	@ApiOperation(value = "Get a country with id")
	@GetMapping("/id/{id}")
	public ResponseEntity<Country> getCountry(@PathVariable("id") Long id) {
	   try {
		   Country existingCountry = countryService.get(id) ;
		   return ResponseEntity.ok(existingCountry) ;
	   }catch(NullPointerException ex) {
		   return ResponseEntity.noContent().build();
	   }
	}
	
	@ApiOperation(value = "Get all countries")
	@GetMapping("/all")
	public ResponseEntity<List<Country>> getAllCountries() {
	    List<Country> listCountries = countryService.getAll() ;
	    return ResponseEntity.ok(listCountries) ;
	}
}

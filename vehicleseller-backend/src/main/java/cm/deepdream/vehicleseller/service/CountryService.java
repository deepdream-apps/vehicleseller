package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Country;
import cm.deepdream.vehicleseller.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CountryService {
	@Autowired
	private CountryRepository countryRepository ;
	
	public Country create (Country country) {
		return countryRepository.save(country) ;
	}
	
	
	public Country modify (Country country) {
		return countryRepository.save(country) ;
	}
	
	
	public void delete(Country country) {
		countryRepository.delete(country);
	}
	
	
	public Country get (Long id) {
		return countryRepository.findById(id).orElseThrow(NullPointerException::new) ;
	}
	
	
	public List<Country> getAll () {
		Iterable<Country> countries  = countryRepository.findAll() ;
		List<Country> countriesList = new ArrayList<>() ;
		countries.forEach(countriesList::add) ;
		return countriesList ;
	}

}

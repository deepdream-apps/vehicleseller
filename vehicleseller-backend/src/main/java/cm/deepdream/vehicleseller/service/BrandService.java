package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Brand;
import cm.deepdream.vehicleseller.repository.BrandRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandService {
	@Autowired
	private BrandRepository brandRepository ;
	
	public Brand create (Brand brand) {
		Brand savedBrand  = brandRepository.save(brand) ;
		return savedBrand ;
	}
	
	
	public Brand modify (Brand brand) {
		Brand savedBrand  = brandRepository.save(brand) ;
		return savedBrand ;
	}
	
	
	public void delete (Brand brand) {
		brandRepository.delete(brand);
	}
	
	
	public Brand get (Long id) {
		Brand savedBrand  = brandRepository.findById(id).orElseGet(null) ;
		return savedBrand ;
	}
	
	
	public List<Brand> getAll () {
		Iterable<Brand> brands  = brandRepository.findAll() ;
		List<Brand> brandsList = new ArrayList<Brand>() ;
		brands.forEach(brandsList::add) ;
		return brandsList ;
	}
}

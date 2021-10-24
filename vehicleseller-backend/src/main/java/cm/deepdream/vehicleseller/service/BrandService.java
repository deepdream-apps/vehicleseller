package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Brand;
import cm.deepdream.vehicleseller.repository.BrandRepository;
@Transactional
@Service
public class BrandService {
	@Autowired
	private BrandRepository brandRepository ;
	
	public Brand create (Brand brand) {
		return brandRepository.save(brand) ;
	}
	
	
	public Brand modify (Brand brand) {
		return brandRepository.save(brand) ;
	}
	
	
	public void delete (Brand brand) {
		brandRepository.delete(brand);
	}
	
	
	public Brand get (Long id) {
		return brandRepository.findById(id).orElseThrow(NullPointerException::new)  ;
	}
	
	
	public List<Brand> getAll () {
		Iterable<Brand> brands  = brandRepository.findAll() ;
		List<Brand> brandsList = new ArrayList<>() ;
		brands.forEach(brandsList::add) ;
		return brandsList ;
	}
}

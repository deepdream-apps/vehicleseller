package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Seller;
import cm.deepdream.vehicleseller.repository.SellerRepository;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class SellerService {
	@Autowired
	private SellerRepository SellerRepository ;
	
	public Seller create (Seller Seller) {
		Seller savedSeller  = SellerRepository.save(Seller) ;
		return savedSeller ;
	}
	
	
	public Seller modify (Seller Seller) {
		Seller savedSeller  = SellerRepository.save(Seller) ;
		return savedSeller ;
	}
	
	
	public void delete(Seller Seller) {
		SellerRepository.delete(Seller);
	}
	
	
	public Seller get (Long id) {
		Seller savedSeller  = SellerRepository.findById(id).orElseGet(null) ;
		return savedSeller ;
	}
	
	
	public List<Seller> getAll () {
		Iterable<Seller> countries  = SellerRepository.findAll() ;
		List<Seller> countriesList = new ArrayList<Seller>() ;
		countries.forEach(countriesList::add) ;
		return countriesList ;
	}
}

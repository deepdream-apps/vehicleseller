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
	private SellerRepository sellerRepository ;
	
	public Seller create (Seller seller) {
		return sellerRepository.save(seller) ;
	}
	
	
	public Seller modify (Seller seller) {
		return sellerRepository.save(seller) ;
	}
	
	
	public void delete(Seller Seller) {
		sellerRepository.delete(Seller);
	}
	
	
	public Seller get (Long id) {
		return sellerRepository.findById(id).orElseThrow(NullPointerException::new)  ;
	}
	
	
	public List<Seller> getAll () {
		Iterable<Seller> countries  = sellerRepository.findAll() ;
		List<Seller> countriesList = new ArrayList<>() ;
		countries.forEach(countriesList::add) ;
		return countriesList ;
	}
}

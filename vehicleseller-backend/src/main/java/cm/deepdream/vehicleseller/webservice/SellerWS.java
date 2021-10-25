package cm.deepdream.vehicleseller.webservice;
import java.util.List;
import java.util.Optional;
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
import cm.deepdream.vehicleseller.model.Seller;
import cm.deepdream.vehicleseller.service.SellerService;
@RestController
@RequestMapping("/api/seller")
public class SellerWS {
	@Autowired
	private SellerService sellerService ;
	
	
	
	@PostMapping("/add")
	public ResponseEntity<Seller> addSeller(@RequestBody  Seller seller) {
	    if(seller.getLabel() == null || seller.getLabel().isBlank()) {
	            return ResponseEntity.badRequest().build();
	     }
	    Seller newSeller = sellerService.create(seller) ;
	    return ResponseEntity.ok(newSeller);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Seller> updateSeller(@RequestBody Seller seller) {
	    if(seller.getLabel() == null || seller.getLabel().isBlank()) {
	         return ResponseEntity.status(400).build();
	     }
	    Optional<Seller> optSeller = sellerService.get(seller.getId()) ;
	    if(optSeller.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    
	    Seller existingSeller = optSeller.get() ;
	    existingSeller.setLabel(seller.getLabel());
	    Seller upadatedSeller = sellerService.create(existingSeller) ;
	    return ResponseEntity.ok(upadatedSeller) ;
	}
	
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity deleteSeller(@PathVariable("id") Long id) {
	    Optional<Seller> optSeller = sellerService.get(id) ;
	    if(optSeller.isEmpty()) {
	    	return ResponseEntity.status(400).build();
	    }
	    sellerService.delete(optSeller.get()) ;
	    return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Seller> getSeller(@PathVariable("id") Long id) {
	    Optional<Seller> optSeller = sellerService.get(id) ;
	    if(optSeller.isEmpty()) {
	    	return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(optSeller.get()) ;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Seller>> getAllSellers() {
	    List<Seller> listSellers = sellerService.getAll() ;
	    return ResponseEntity.ok(listSellers) ;
	}
}

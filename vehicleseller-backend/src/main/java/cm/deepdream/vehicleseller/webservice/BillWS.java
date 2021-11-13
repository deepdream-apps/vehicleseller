package cm.deepdream.vehicleseller.webservice;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cm.deepdream.vehicleseller.model.Bill;
import cm.deepdream.vehicleseller.service.BillService;
@Transactional
@RestController
@RequestMapping("/api/bill")
public class BillWS {
	private BillService billService ;
	
	public BillWS(BillService billService) {
		this.billService = billService;
	}


	@PostMapping("/add")
	public ResponseEntity<Bill> addBill(@RequestBody  Bill bill) {
	    if(bill.getLabel() == null || bill.getLabel().isBlank()) {
	         return ResponseEntity.badRequest().build() ;
	     }
	    Bill newBill = billService.create(bill) ;
	    return ResponseEntity.ok(newBill);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Bill> updateBill(@RequestBody Bill bill)  {
	    if(bill.getLabel() == null || bill.getLabel().isBlank()) {
	         return ResponseEntity.badRequest().build() ;
	    }
	    Bill existingBill = billService.get(bill.getId()) ;
	    if(existingBill == null) {
	    	return ResponseEntity.badRequest().build() ;
	    }
	    existingBill.setLabel(bill.getLabel());
	    Bill upadatedBill = billService.modify(existingBill) ;
	    return ResponseEntity.ok(upadatedBill) ;
	}
	
	
	
	@PutMapping("/cancel")
	public ResponseEntity<Bill> cancelBill(@RequestBody Bill bill)  {
	    if(bill.getLabel() == null || bill.getLabel().isBlank()) {
	         return ResponseEntity.badRequest().build() ;
	    }
	    Bill existingBill = billService.get(bill.getId()) ;
	    if(existingBill == null) {
	    	return ResponseEntity.badRequest().build() ;
	    }
	    existingBill.setLabel(bill.getLabel());
	    Bill upadatedBill = billService.cancel(existingBill) ;
	    return ResponseEntity.ok(upadatedBill) ;
	}
	
	
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity deleteBill(@PathVariable("id") Long id) {
	    Bill existingBill = billService.get(id) ;
	    if(existingBill == null) {
	    	return ResponseEntity.badRequest().build() ;
	    }
	    billService.delete(existingBill) ;
	    return ResponseEntity.ok().build();
	}
	
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Bill> getBill(@PathVariable("id") Long id) {
		try {
			Bill existingBill = billService.get(id) ;
			return ResponseEntity.ok(existingBill) ;
		}catch(NullPointerException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Bill>> getAllBills()  {
	    List<Bill> listBills = billService.getAll() ;
	    return ResponseEntity.ok(listBills);
	}
}

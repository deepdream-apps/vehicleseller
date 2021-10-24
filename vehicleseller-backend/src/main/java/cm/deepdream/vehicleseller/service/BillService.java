package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Bill;
import cm.deepdream.vehicleseller.repository.BillRepository;

@Service
public class BillService {
	@Autowired
	private BillRepository billRepository ;
	
	public Bill create (Bill bill) {
		return billRepository.save(bill) ;
	}
	
	
	public Bill modify (Bill bill) {
		return billRepository.save(bill) ;
	}
	
	
	public void delete (Bill bill) {
		billRepository.delete(bill);
	}
	
	
	public Bill get (Long id) {
		return billRepository.findById(id).orElseThrow(NullPointerException::new) ;
	}
	
	
	public List<Bill> getAll () {
		Iterable<Bill> bills  = billRepository.findAll() ;
		List<Bill> billsList = new ArrayList<>() ;
		bills.forEach(billsList::add) ;
		return billsList ;
	}
}

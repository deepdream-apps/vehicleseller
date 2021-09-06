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
		Bill savedBill  = billRepository.save(bill) ;
		return savedBill ;
	}
	
	
	public Bill modify (Bill bill) {
		Bill savedBill  = billRepository.save(bill) ;
		return savedBill ;
	}
	
	
	public void delete (Bill bill) {
		billRepository.delete(bill);
	}
	
	
	public Bill get (Long id) {
		Bill savedBill  = billRepository.findById(id).orElseGet(null) ;
		return savedBill ;
	}
	
	
	public List<Bill> getAll () {
		Iterable<Bill> bills  = billRepository.findAll() ;
		List<Bill> billsList = new ArrayList<Bill>() ;
		bills.forEach(billsList::add) ;
		return billsList ;
	}
}

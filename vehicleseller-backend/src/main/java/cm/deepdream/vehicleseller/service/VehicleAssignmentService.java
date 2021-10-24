package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.deepdream.vehicleseller.model.VehicleAssignment;
import cm.deepdream.vehicleseller.repository.VehicleAssignmentRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VehicleAssignmentService {
	@Autowired
	private VehicleAssignmentRepository vehicleAssignmentRepository ;
	
	public VehicleAssignment create (VehicleAssignment vehicleAssignment) {
		return vehicleAssignmentRepository.save(vehicleAssignment) ;
	}
	
	
	public VehicleAssignment modify (VehicleAssignment vehicleAssignment) {
		return vehicleAssignmentRepository.save(vehicleAssignment) ;
	}
	
	
	public void delete(VehicleAssignment vehicleAssignment) {
		vehicleAssignmentRepository.delete(vehicleAssignment);
	}
	
	
	public VehicleAssignment get (Long id) {
		return vehicleAssignmentRepository.findById(id).orElseThrow(NullPointerException::new)  ;
	}
	
	
	public List<VehicleAssignment> getAll () {
		Iterable<VehicleAssignment> vehicleAssignments  = vehicleAssignmentRepository.findAll() ;
		List<VehicleAssignment> vehicleAssignmentsList = new ArrayList<>() ;
		vehicleAssignments.forEach(vehicleAssignmentsList::add) ;
		return vehicleAssignmentsList ;
	}
}

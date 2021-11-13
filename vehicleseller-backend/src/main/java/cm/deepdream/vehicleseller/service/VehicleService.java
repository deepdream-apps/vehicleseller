package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.amazonaws.services.s3.AmazonS3;

import cm.deepdream.vehicleseller.dto.ModelDTO;
import cm.deepdream.vehicleseller.dto.VehicleDTO;
import cm.deepdream.vehicleseller.model.Model;
import cm.deepdream.vehicleseller.model.Vehicle;
import cm.deepdream.vehicleseller.repository.VehicleRepository;

@Transactional
@Service
public class VehicleService {
	private VehicleRepository vehicleRepository ;
	private AmazonS3 amazonS3 ;
	
	
	public VehicleService(VehicleRepository vehicleRepository, AmazonS3 amazonS3) {
		this.vehicleRepository = vehicleRepository;
		this.amazonS3 = amazonS3;
	}


	public VehicleDTO create (VehicleDTO vehicleDTO) {
		
		Vehicle vehicle = Vehicle.builder()
				.category(vehicleDTO.getCategory())
				.chassisNumber(vehicleDTO.getChassisNumber())
				.color(vehicleDTO.getChassisNumber())
				.description(vehicleDTO.getDescription())
				.doors(vehicleDTO.getDoors()) 
				.imageName(vehicleDTO.getImageName()) 
				.mileage(vehicleDTO.getMileage())
				.model(new Model(vehicleDTO.getModel().getId(), null, null))
				.registrationNumber(vehicleDTO.getRegistrationNumber())
				.seats(vehicleDTO.getSeats())
				.status(vehicleDTO.getStatus()) 
				.year(vehicleDTO.getYear())
				.build() ;
		
		Vehicle addedVehicle =  vehicleRepository.save(vehicle) ;
		
		return VehicleDTO.builder()
				.id(addedVehicle.getId())
				.category(addedVehicle.getCategory())
				.chassisNumber(addedVehicle.getChassisNumber())
				.color(addedVehicle.getChassisNumber())
				.description(addedVehicle.getDescription())
				.doors(addedVehicle.getDoors()) 
				.imageName(addedVehicle.getImageName()) 
				.mileage(vehicleDTO.getMileage())
				.model(new ModelDTO(addedVehicle.getModel().getId(), addedVehicle.getModel().getLabel(), addedVehicle.getModel().getLabelBrand()))
				.registrationNumber(addedVehicle.getRegistrationNumber())
				.seats(addedVehicle.getSeats())
				.status(addedVehicle.getStatus()) 
				.year(addedVehicle.getYear())
				.build()  ;
	}
	
	
	public VehicleDTO update (VehicleDTO vehicleDTO) {
		
		Optional<Vehicle> optVehicle = vehicleRepository.findById(vehicleDTO.getId()) ;
		
		if(optVehicle.isEmpty()) {
			throw new IllegalArgumentException("Error ! Bad vehicle object submitted") ;
		}
		
		Vehicle existingVehicle = optVehicle.get() ;
		
		existingVehicle.setColor(vehicleDTO.getColor());
		existingVehicle.setDescription(vehicleDTO.getDescription());
		existingVehicle.setDoors(vehicleDTO.getDoors());
		existingVehicle.setModel(new Model(vehicleDTO.getModel().getId(), null, null));
		existingVehicle.setRegistrationNumber(vehicleDTO.getRegistrationNumber());
		existingVehicle.setSeats(vehicleDTO.getSeats());
		existingVehicle.setStatus(vehicleDTO.getStatus());
		existingVehicle.setYear(vehicleDTO.getYear());
		existingVehicle.setDescription(vehicleDTO.getDescription()) ;
		
		Vehicle upadatedVehicle = vehicleRepository.save(existingVehicle) ;
		
		return VehicleDTO.builder()
				.id(upadatedVehicle.getId())
				.category(upadatedVehicle.getCategory())
				.chassisNumber(upadatedVehicle.getChassisNumber())
				.color(upadatedVehicle.getChassisNumber())
				.description(upadatedVehicle.getDescription())
				.doors(upadatedVehicle.getDoors()) 
				.imageName(upadatedVehicle.getImageName()) 
				.mileage(vehicleDTO.getMileage())
				.model(new ModelDTO(upadatedVehicle.getModel().getId(), upadatedVehicle.getModel().getLabel(), upadatedVehicle.getModel().getLabelBrand()))
				.registrationNumber(upadatedVehicle.getRegistrationNumber())
				.seats(upadatedVehicle.getSeats())
				.status(upadatedVehicle.getStatus()) 
				.year(upadatedVehicle.getYear())
				.build()  ;
	}
	
	
	public void delete(VehicleDTO vehicleDTO) {
		Vehicle vehicle = Vehicle.builder()
				.id(vehicleDTO.getId())
				.build() ;
		vehicleRepository.delete(vehicle) ;
	}
	
	
	public Optional<VehicleDTO> get (Long id) {
		Optional<Vehicle> optVehicle = vehicleRepository.findById(id)  ;
		
		if(optVehicle.isEmpty()) {
			throw new IllegalArgumentException("Error ! Bad vehicle object submitted") ;
		}
		
		Vehicle existingVehicle = optVehicle.get() ;
		
		VehicleDTO vehicleDTO = VehicleDTO.builder()
									.id(existingVehicle.getId())
									.category(existingVehicle.getCategory())
									.chassisNumber(existingVehicle.getChassisNumber())
									.color(existingVehicle.getChassisNumber())
									.description(existingVehicle.getDescription())
									.doors(existingVehicle.getDoors()) 
									.imageName(existingVehicle.getImageName()) 
									.mileage(existingVehicle.getMileage())
									.model(new ModelDTO(existingVehicle.getModel().getId(), existingVehicle.getModel().getLabel(), existingVehicle.getModel().getLabelBrand()))
									.registrationNumber(existingVehicle.getRegistrationNumber())
									.seats(existingVehicle.getSeats())
									.status(existingVehicle.getStatus()) 
									.year(existingVehicle.getYear())
									.build()  ;
		
		return Optional.of(vehicleDTO) ;
	}
	

	
	
	public List<VehicleDTO> getAll () {
		Iterable<Vehicle> listVehicles  = vehicleRepository.findAll() ;
		
		final List<VehicleDTO> listVehiclesDTO = new ArrayList<>() ;
		
		listVehicles.forEach(vehicle -> {
			VehicleDTO vehicleDTO = VehicleDTO.builder()
					.id(vehicle.getId())
					.category(vehicle.getCategory())
					.chassisNumber(vehicle.getChassisNumber())
					.color(vehicle.getChassisNumber())
					.description(vehicle.getDescription())
					.doors(vehicle.getDoors()) 
					.imageName(vehicle.getImageName()) 
					.mileage(vehicle.getMileage())
					.model(new ModelDTO(vehicle.getModel().getId(), vehicle.getModel().getLabel(), vehicle.getModel().getLabelBrand()))
					.registrationNumber(vehicle.getRegistrationNumber())
					.seats(vehicle.getSeats())
					.status(vehicle.getStatus()) 
					.year(vehicle.getYear())
					.build()  ;
			listVehiclesDTO.add(vehicleDTO) ;
		}) ;
		
		return listVehiclesDTO ;
	}
}

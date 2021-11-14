package cm.deepdream.vehicleseller.webservice;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import cm.deepdream.vehicleseller.dto.ModelDTO;
import cm.deepdream.vehicleseller.dto.VehicleDTO;

@Transactional
@AutoConfigureTestEntityManager
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VehicleWSIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate ;

	
	@Test
	public void addVehicle_success() throws Exception {
		ModelDTO modelDTO = ModelDTO.builder()
				.label("Toyota RAV4")
				.labelBrand("Toyota")
				.build() ;
		
		VehicleDTO vehicleDTO = VehicleDTO.builder().category("Lux").chassisNumber("TR-OT-1T-KM").color("Red")
				.description("Best car").doors(4).imageName("").mileage(123000).model(modelDTO)
				.registrationNumber("CE690ID").seats(5).status("En Service").year(2015)
				.build() ;

	   ResponseEntity<VehicleDTO>	response =  restTemplate.postForEntity("/api/vehicle/add", vehicleDTO, VehicleDTO.class) ;
	   
	   VehicleDTO vehicleDTOResult = response.getBody() ;
	   
	   assertTrue(response.getStatusCodeValue() ==  HttpStatus.SC_OK
			   && vehicleDTOResult.getId() != null );
	}
	
	
	@Test
	public void updateVehicle_success() throws Exception {
		ModelDTO modelDTO = ModelDTO.builder()
				.label("Toyota RAV4")
				.labelBrand("Toyota")
				.build() ;
		
		VehicleDTO vehicleDTO = VehicleDTO.builder().id(1L).category("Lux").chassisNumber("TR-OT-1T-KM").color("Red")
				.description("Best car").doors(4).imageName("").mileage(123000).model(modelDTO)
				.registrationNumber("CE690ID").seats(5).status("En Service").year(2015)
				.build() ;

	   ResponseEntity<VehicleDTO>	response =  restTemplate.exchange("/api/vehicle/update",  HttpMethod.PUT, new HttpEntity<VehicleDTO>(vehicleDTO), VehicleDTO.class) ;
	   
	   VehicleDTO vehicleDTOResult = response.getBody() ;
	   
	   assertTrue(response.getStatusCodeValue() ==  HttpStatus.SC_OK
			   && vehicleDTOResult.getId() != null );
	}
	
}

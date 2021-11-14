package cm.deepdream.vehicleseller.webservice;
import org.junit.jupiter.api.Test;

import static org.mockito.Matchers.anyLong;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import cm.deepdream.vehicleseller.dto.ModelDTO;
import cm.deepdream.vehicleseller.dto.VehicleDTO;
import cm.deepdream.vehicleseller.service.VehicleService;

@WebMvcTest(VehicleWS.class)
public class VehicleWSUnitTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private VehicleService vehicleService ;
	
	@Autowired
	private ObjectMapper objectMapper ;
	
	@Test
	public void addVehicle_success() throws Exception {
	
		ModelDTO modelDTO = ModelDTO.builder()
				.label("Toyota RAV4")
				.labelBrand("Toyota")
				.build() ;
		
		VehicleDTO vehicleDTO = VehicleDTO.builder().category("Lux").chassisNumber("TR-OT-1T-KM").color("Red")
									.description("").doors(4).imageName("").mileage(123000).model(modelDTO)
									.registrationNumber("").seats(5).status("En Service").year(2015)
									.build() ;
		
		VehicleDTO returnedVehicleDTO = VehicleDTO.builder().id(anyLong()).category("Lux").chassisNumber("TR-OT-1T-KM").color("Red")
				.description("").doors(4).imageName("").mileage(123000).model(modelDTO)
				.registrationNumber("").seats(5).status("En Service").year(2015)
				.build() ;
		
		Mockito.when(vehicleService.create(vehicleDTO)).thenReturn(returnedVehicleDTO) ;
		
		RequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/vehicle/add")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(vehicleDTO));
		
		mockMvc.perform(mockRequest)
        		.andExpect(status().isOk());
		
	}
	
	
	
	
	@Test
	public void updateVehicle_success() throws Exception {
	
		ModelDTO modelDTO = ModelDTO.builder()
				.label("Toyota RAV4")
				.labelBrand("Toyota")
				.build() ;
		
		VehicleDTO vehicleDTO = VehicleDTO.builder().category("Lux").chassisNumber("TR-OT-1T-KM").color("Red")
									.description("").doors(4).imageName("").mileage(123000).model(modelDTO)
									.registrationNumber("").seats(5).status("En Service").year(2015)
									.build() ;
		
		VehicleDTO returnedVehicleDTO = VehicleDTO.builder().category("Lux").chassisNumber("TR-OT-1T-KM").color("Red")
				.description("").doors(4).imageName("").mileage(123000).model(modelDTO)
				.registrationNumber("").seats(5).status("En Service").year(2015)
				.build() ;
		
		Mockito.when(vehicleService.update(vehicleDTO)).thenReturn(returnedVehicleDTO) ;
		
		RequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/vehicle/update")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(vehicleDTO));
		
		mockMvc.perform(mockRequest)
        		.andExpect(status().isOk());
		
	}
	
	
	
	@Test
	public void deleteVehicle_success() throws Exception {

		VehicleDTO vehicleDTO = VehicleDTO.builder().id(1L).build() ;
		
		Mockito.doNothing().when(vehicleService).delete(vehicleDTO) ;
		
		RequestBuilder mockRequest = MockMvcRequestBuilders.delete("/api/vehicle/id/"+vehicleDTO.getId())
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(mockRequest)
        		.andExpect(status().isOk());
		
	}
	
	
	@Test
	public void getVehicle_success() throws Exception {
	
		ModelDTO modelDTO = ModelDTO.builder()
				.label("Toyota RAV4")
				.labelBrand("Toyota")
				.build() ;

		VehicleDTO vehicleDTO = VehicleDTO.builder().id(10000L).category("Lux").chassisNumber("TR-OT-1T-KM").color("Red")
				.description("").doors(4).imageName("").mileage(123000).model(modelDTO)
				.registrationNumber("").seats(5).status("En Service").year(2015)
				.build() ;
		
		Mockito.when(vehicleService.get(vehicleDTO.getId())).thenReturn(Optional.of(vehicleDTO)) ;
		
		RequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/vehicle/id/"+vehicleDTO.getId())
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON) 
	            ;
		
		mockMvc.perform(mockRequest)
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$", notNullValue()))
        		.andExpect(jsonPath("$.category", is(vehicleDTO.getCategory())))
        		.andExpect(jsonPath("$.chassisNumber", is(vehicleDTO.getChassisNumber())))
        		.andExpect(jsonPath("$.color", is(vehicleDTO.getColor())))
        		.andExpect(jsonPath("$.registrationNumber", is(vehicleDTO.getRegistrationNumber()))) 
        		.andExpect(jsonPath("$.seats", is(vehicleDTO.getSeats())))
        		.andExpect(jsonPath("$.status", is(vehicleDTO.getStatus())))
        		.andExpect(jsonPath("$.year", is(vehicleDTO.getYear())));
		
	}
	
	
	@Test
	public void getAll_Success() throws Exception {
	
		ModelDTO modelDTO = ModelDTO.builder()
				.label("Toyota RAV4")
				.labelBrand("Toyota")
				.build() ;

		VehicleDTO vehicleDTO_1 = VehicleDTO.builder().id(10000L).category("Lux").chassisNumber("TR-OT-1T-KM").color("Red")
				.description("").doors(4).imageName("").mileage(123000).model(modelDTO)
				.registrationNumber("").seats(5).status("En Service").year(2015)
				.build() ;
		
		VehicleDTO vehicleDTO_2 = VehicleDTO.builder().id(10000L).category("Lux").chassisNumber("TR-OT-1T-KM").color("Red")
				.description("").doors(4).imageName("").mileage(123000).model(modelDTO)
				.registrationNumber("").seats(5).status("En Service").year(2015)
				.build() ;
		
		List<VehicleDTO> listVehiclesDTO = Arrays.asList(vehicleDTO_1, vehicleDTO_2) ;
		
		Mockito.when(vehicleService.getAll()).thenReturn(listVehiclesDTO) ;
		
		RequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/vehicle/all")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON) ;
		
		mockMvc.perform(mockRequest)
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$", hasSize(2)))
        		.andExpect(jsonPath("$[0].category", is(vehicleDTO_1.getCategory())))
        		.andExpect(jsonPath("$[0].chassisNumber", is(vehicleDTO_1.getChassisNumber())))
        		.andExpect(jsonPath("$[0].color", is(vehicleDTO_1.getColor())))
        		.andExpect(jsonPath("$[0].registrationNumber", is(vehicleDTO_1.getRegistrationNumber()))) 
        		.andExpect(jsonPath("$[0].seats", is(vehicleDTO_1.getSeats())))
        		.andExpect(jsonPath("$[0].status", is(vehicleDTO_1.getStatus())))
        		.andExpect(jsonPath("$[0].year", is(vehicleDTO_1.getYear())));
		
	}

}

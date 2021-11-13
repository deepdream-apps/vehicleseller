package cm.deepdream.vehicleseller.webservice;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;

import com.fasterxml.jackson.databind.ObjectMapper;

import cm.deepdream.vehicleseller.dto.ModelDTO;
import cm.deepdream.vehicleseller.service.ModelService;

@WebMvcTest(ModelWS.class)
public class ModelWSUnitTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private ModelService modelService ;
	
	@Autowired
	private ObjectMapper objectMapper ;
	
	@Test
	public void addModel_success() throws Exception {
	
		ModelDTO modelDTO_1 = ModelDTO.builder()
				.label("Toyota RAV4")
				.labelBrand("Toyota")
				.build() ;
		
		ModelDTO modelDTO_2 = ModelDTO.builder()
				.id(1L)
				.label("Toyota RAV4")
				.labelBrand("Toyota")
				.build() ;
		
		Mockito.when(modelService.create(modelDTO_1)).thenReturn(modelDTO_2) ;
		
		RequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/model/add")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(modelDTO_1));
		
		mockMvc.perform(mockRequest)
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$", notNullValue()))
        		.andExpect(jsonPath("$.id", notNullValue()))
        		.andExpect(jsonPath("$.label", is("Toyota RAV4")))
        		.andExpect(jsonPath("$.labelBrand", is("Toyota")));
		
	}
	
	
	@Test
	public void addModel_withBlankLabel() throws Exception {
	
		ModelDTO modelDTO_1 = ModelDTO.builder()
				.label("")
				.labelBrand("Toyota")
				.build() ;
		
		RequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/model/add")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(modelDTO_1));
		
		mockMvc.perform(mockRequest)
        		.andExpect(status().isBadRequest()) ;
		
		Mockito.verify(modelService, times(0)).create(modelDTO_1) ;
	}
	
	
	@Test
	public void updateModel_success() throws Exception {
	
		ModelDTO modelDTO = ModelDTO.builder()
				.id(1L)
				.label("Toyota RAV4")
				.labelBrand("Toyota")
				.build() ;
		
		Mockito.when(modelService.update(modelDTO)).thenReturn(modelDTO) ;
		
		RequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/model/update")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(modelDTO));
		
		mockMvc.perform(mockRequest)
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$", notNullValue()))
        		.andExpect(jsonPath("$.id", is(1)))
        		.andExpect(jsonPath("$.label", is("Toyota RAV4")))
        		.andExpect(jsonPath("$.labelBrand", is("Toyota")));
	}
	
	
	@Test
	public void deleteModel_success() throws Exception {
	
		ModelDTO modelDTO = ModelDTO.builder()
				.id(1L)
				.build() ;
		
		ModelService modelService = Mockito.mock(ModelService.class) ;
		
		Mockito.doNothing().when(modelService).delete(modelDTO);
		
		RequestBuilder mockRequest = MockMvcRequestBuilders.delete("/api/model/id/1")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON) ;
		
		mockMvc.perform(mockRequest)
        		.andExpect(status().isOk()) ;
	}
	
	
	@Test
	public void getModelById_success() throws Exception {
	
		ModelDTO modelDTO = ModelDTO.builder()
				.id(2L)
				.label("Mercedes Benz 500")
				.labelBrand("Mercedes")
				.build() ;
		
		Mockito.when(modelService.get(2L)).thenReturn(Optional.of(modelDTO)) ;
		
		RequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/model/id/2")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON) ;
		
		mockMvc.perform(mockRequest)
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$", notNullValue()))
        		.andExpect(jsonPath("$.id", is(2)))
        		.andExpect(jsonPath("$.label", is("Mercedes Benz 500")))
        		.andExpect(jsonPath("$.labelBrand", is("Mercedes")));
	}
	
	
	@Test
	public void getModelById_notfound() throws Exception {
	
		ModelDTO modelDTO = ModelDTO.builder()
				.id(20012L)
				.label("Mercedes Benz 500")
				.labelBrand("Mercedes")
				.build() ;
		
		Mockito.when(modelService.get(modelDTO.getId())).thenReturn(Optional.empty()) ;
		
		RequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/model/id/2")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON) ;
		
		mockMvc.perform(mockRequest)
        		.andExpect(status().isNoContent()) ;
	}
	
	
	@Test
	public void getAllModels_success() throws Exception {
	
		ModelDTO modelDTO_1 = ModelDTO.builder()
				.id(1L)
				.label("Toyota RAV4")
				.labelBrand("Toyota")
				.build() ;
		
		ModelDTO modelDTO_2 = ModelDTO.builder()
				.id(2L)
				.label("Mercedes Benz 500")
				.labelBrand("Mercedes")
				.build() ;
		
		ModelDTO modelDTO_3 = ModelDTO.builder()
				.id(3L)
				.label("Peugeot 504")
				.labelBrand("Peugeot")
				.build() ;
		
		List<ModelDTO> listModesDTO = List.of(modelDTO_1, modelDTO_2, modelDTO_3) ;
		
		Mockito.when(modelService.getAll()).thenReturn((listModesDTO)) ;
		
		RequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/model/all")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON) ;
		
		mockMvc.perform(mockRequest)
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$", hasSize(3)))
        		.andExpect(jsonPath("$[0].label", is("Toyota RAV4")))
        		.andExpect(jsonPath("$[1].label", is("Mercedes Benz 500")))
        		.andExpect(jsonPath("$[2].label", is("Peugeot 504"))) ;
	}

}

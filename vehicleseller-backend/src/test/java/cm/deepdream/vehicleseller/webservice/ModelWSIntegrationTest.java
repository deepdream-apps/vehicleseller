package cm.deepdream.vehicleseller.webservice;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import cm.deepdream.vehicleseller.dto.ModelDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ModelWSIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate ;

	@Test
	public void addModel_success() throws Exception {
		ModelDTO modelDTOEntry = ModelDTO.builder()
				.label("Toyota RAV4")
				.labelBrand("Toyota")
				.build() ;
		
	   ResponseEntity<ModelDTO>	 response =  restTemplate.postForEntity("/api/model/add", modelDTOEntry, ModelDTO.class) ;
	   
	   ModelDTO modelDTOResult = response.getBody() ;
	   
	   assertTrue(response.getStatusCodeValue() ==  HttpStatus.SC_OK
			   && modelDTOResult.getId() != null);
	}
	
	
	
}

package cm.deepdream.vehicleseller.webservice;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import cm.deepdream.vehicleseller.dto.ModelDTO;
import cm.deepdream.vehicleseller.model.Model;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Transactional
@AutoConfigureTestEntityManager
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ModelWSIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate ;
	
	@Autowired
	private TestEntityManager entityManager ;
	
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
	
	
	@Test
	public void updateModel_success() throws Exception {
		Model modelEntry = Model.builder()
				.label("Mercedes Benz 500")
				.labelBrand("Mercedes")
				.build() ;
		
	   Model newModel = entityManager.persist(modelEntry) ;
	   
	   entityManager.getEntityManager().getTransaction().commit(); 
	   
	   
	   ModelDTO modelDTOEntry = ModelDTO.builder()
			    .id(newModel.getId())
				.label("Mercedes Benz 506")
				.labelBrand("Mercedes Brand")
				.build() ;
	  
	  ResponseEntity<ModelDTO> response =   restTemplate.exchange("/api/model/update", HttpMethod.PUT, new HttpEntity<ModelDTO>(modelDTOEntry), ModelDTO.class) ;
	  
	  ModelDTO updatedModelDTO = response.getBody() ;
	  
	  log.info("modelDTOEntry = " + modelDTOEntry) ;
	  
	  assertTrue(modelDTOEntry.getId() == updatedModelDTO.getId()
			   && modelDTOEntry.getLabel().equals(updatedModelDTO.getLabel())
			   && modelDTOEntry.getLabelBrand().equals(updatedModelDTO.getLabelBrand()));
	}
}

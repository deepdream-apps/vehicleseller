package cm.deepdream.vehicleseller.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import cm.deepdream.vehicleseller.config.VehicleSellerTestConfig;
import cm.deepdream.vehicleseller.model.Brand;
import cm.deepdream.vehicleseller.model.Model;

@Import(VehicleSellerTestConfig.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ModelWebServiceTest {
	@LocalServerPort
	private int serverPort ;
	@Autowired
	private TestRestTemplate restTemplate ;
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	
	@Test
	public void testAdd() {
		Brand  testedBrand = new Brand(1L, null, "Toyota", "Toyota is the best in Africa") ;
		Model testedModel = new Model(1L, "Corolla", testedBrand, "Corolla is the most popular Toyota brand") ;
		ResponseEntity<Model> response =  restTemplate.postForEntity("/api/model/add", testedModel, Model.class) ;
		Model returnedModel = jdbcTemplate.queryForObject("select * from model where id=?", 
				new Object[] {1L}, new ModelRowMapper(jdbcTemplate)) ;
		assertTrue(response.getStatusCode() == HttpStatus.OK  && returnedModel.equals(testedModel));
	}
	
	
	@Test
	public void testUpdate() {
		Brand testedBrand1 = new Brand(2L, null, "Mercedes", "Mercedes is the second in Africa") ;
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)", 
				2L, testedBrand1.getLabel(),  testedBrand1.getDescription());
		
		Brand testedBrand2 = new Brand(3L, null, "Wolsvagen", "Wolsvagen is the second in Africa") ;
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)", 
				3L, testedBrand2.getLabel(),  testedBrand2.getDescription());
		
		Model testedModel = new Model(2L, "Mercedes 500", testedBrand1, "Mercedes 500 is the best Mercedes brand for me") ;
		testedModel.setBrand(testedBrand1);
		jdbcTemplate.update("insert into model (id, label, description, id_brand) values (?, ?, ?, ?)", 
				3L, testedModel.getLabel(),  testedModel.getDescription(), testedModel.getBrand());
		
		restTemplate.put("/api/model/update/{id}", testedModel, 2L) ;
		
		Model returnedModel = jdbcTemplate.queryForObject("select * from model where id=?", 
				new Object[] {2L}, new ModelRowMapper(jdbcTemplate)) ;
		
		assertTrue(testedModel.getLabel().equals(returnedModel.getLabel()) &&
				testedModel.getDescription().equals(returnedModel.getDescription()) && 
				testedModel.getBrand().equals(returnedModel.getBrand())) ;
	}
	
	
	@Test
	public void testGet() {
		Brand  testedBrand = new Brand(4L, null, "Peugeot", "Peugeot is a french brand") ;
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)",
				testedBrand.getId(), testedBrand.getLabel(),  testedBrand.getDescription());
		
		Model  testedModel = new Model(3L, "Peugeot 600", testedBrand, "Peugeot 600 is a french brand") ;
		jdbcTemplate.update("insert into brand (id, label, description, id_brand) values (?, ?, ?, ?)",
				testedModel.getId(), testedModel.getLabel(),  testedModel.getDescription(), testedModel.getBrand());

		Model returnedModel = restTemplate.getForObject("/api/model/id/{id}",  Model.class, 3L) ;
		assertTrue(returnedModel != null && returnedModel.equals(testedModel));
	}
	
	
	@Test
	public void testGetAll() {
		List<Model> listModels = restTemplate.getForObject("/api/model/all", List.class) ;
		
		List<Model> listModels2  = jdbcTemplate.query("select * from model", new ModelRowMapper(jdbcTemplate)) ;
		assertEquals(listModels.size(), listModels2.size());
	}
	
	
	
	public class ModelRowMapper implements RowMapper<Model> {
		private JdbcTemplate jdbcTemplate ;
		
		public ModelRowMapper(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate ;
		}
		
	    @Override
	    public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Model model = new Model();
	    	model.setId(rs.getLong("id"));
	    	model.setLabel(rs.getString("label"));
	    	model.setDescription(rs.getString("description")) ;
	    	Brand brand = jdbcTemplate.queryForObject("select * from brand where id= ?",  
	    			Brand.class, rs.getLong("id_brand")) ;
	    	model.setBrand(brand);
	        return model;
	    }
	}
	

}

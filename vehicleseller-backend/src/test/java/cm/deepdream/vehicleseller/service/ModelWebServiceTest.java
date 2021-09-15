package cm.deepdream.vehicleseller.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNull;
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
		Brand  testedBrand = new Brand(101L, null, "Toyota 101", "Toyota is the best in Africa") ;
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)", 
				testedBrand.getId(), testedBrand.getLabel(),  testedBrand.getDescription());
		
		Model testedModel = new Model(101L, "Corolla 101", testedBrand, "Corolla is the most popular Toyota model") ;
		ResponseEntity<Model> response =  restTemplate.postForEntity("/api/model/add", testedModel, Model.class) ;
		Model returnedModel = null ;
		try {
			returnedModel = jdbcTemplate.queryForObject("select * from model where id=?", 
				new Object[] {testedModel.getId()}, new ModelRowMapper(jdbcTemplate)) ;
		}catch(Exception ex) {}
		assertTrue(testedModel.equals(returnedModel));
	}
	
	
	@Test
	public void testUpdate() {
		Brand testedBrand1 = new Brand(102L, null, "Mercedes 102", "Mercedes is the second in Africa") ;
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)", 
				testedBrand1.getId(), testedBrand1.getLabel(),  testedBrand1.getDescription());
		
		Brand testedBrand2 = new Brand(103L, null, "Wolsvagen 103", "Wolsvagen is the second in Africa") ;
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)", 
				testedBrand2.getId(), testedBrand2.getLabel(),  testedBrand2.getDescription());
		
		Model testedModel = new Model(102L, "Mercedes 500 102", testedBrand1, 
				"Mercedes 500 is the best Mercedes brand for me") ;
		
		jdbcTemplate.update("insert into model (id, label, description, id_brand) values (?, ?, ?, ?)", 
				testedModel.getId(), testedModel.getLabel(),  testedModel.getDescription(), testedModel.getBrand().getId());
		testedModel.setLabel("Mercedes 600");
		testedModel.setDescription("Mercedes 600 is the best Mercedes brand for me");
		testedModel.setBrand(testedBrand2);
		
		restTemplate.put("/api/model/update/{id}", testedModel, testedModel.getId()) ;
		
		Model returnedModel = jdbcTemplate.queryForObject("select * from model where id=?", 
				new Object[] {testedModel.getId()}, new ModelRowMapper(jdbcTemplate)) ;
		
		assertTrue(testedModel.getLabel().equals(returnedModel.getLabel()) &&
				testedModel.getDescription().equals(returnedModel.getDescription()) &&
				returnedModel.getBrand().equals(testedModel.getBrand())) ;
	}
	
	
	@Test
	public void testDelete() {
		Brand  testedBrand = new Brand(104L, null, "Renault 104", "Peugeot is a french brand") ;
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)",
				testedBrand.getId(), testedBrand.getLabel(),  testedBrand.getDescription());
		
		Model  testedModel = new Model(103L, "Peugeot 600 103", testedBrand, "Peugeot 600 is a french brand") ;
		jdbcTemplate.update("insert into model (id, label, description, id_brand) values (?, ?, ?, ?)",
				testedModel.getId(), testedModel.getLabel(),  testedModel.getDescription(), testedModel.getBrand().getId());

		restTemplate.delete("/api/model/id/{id}",  testedModel.getId()) ;
		
		Model returnedModel = null ;
		try {
			returnedModel = jdbcTemplate.queryForObject("select * from model where id=?", 
				new Object[] {testedModel.getId()}, new ModelRowMapper(jdbcTemplate)) ;
		}catch(Exception ex) {}
		
		assertNull(returnedModel);
	}
	
	
	@Test
	public void testGet() {
		Brand  testedBrand = new Brand(105L, null, "Peugeot 105 105", "Peugeot is a french brand") ;
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)",
				testedBrand.getId(), testedBrand.getLabel(),  testedBrand.getDescription());
		
		Model  testedModel = new Model(104L, "Peugeot 600 104", testedBrand, "Peugeot 600 is a french brand") ;
		jdbcTemplate.update("insert into model (id, label, description, id_brand) values (?, ?, ?, ?)",
				testedModel.getId(), testedModel.getLabel(),  testedModel.getDescription(), testedModel.getBrand().getId());

		Model returnedModel = restTemplate.getForObject("/api/model/id/{id}",  Model.class, testedModel.getId()) ;
		assertTrue(returnedModel != null && 
				returnedModel.equals(testedModel) &&
				returnedModel.getBrand().equals(testedModel.getBrand()));
	}
	
	
	
	
	@Test
	public void testGetAll() {
		List<Model> listModels = restTemplate.getForObject("/api/model/all", List.class) ;
		
		List<Model> listModels2  = jdbcTemplate.query("select * from model", new ModelRowMapper(jdbcTemplate)) ;
		assertEquals(listModels.size(), listModels2.size());
	}
	
	
	
	public static class ModelRowMapper implements RowMapper<Model> {
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
	    			new Object[] {rs.getLong("id_brand")}, new BrandWebServiceTest.BrandRowMapper()) ;
	    	model.setBrand(brand);
	        return model;
	    }
	}
	

}

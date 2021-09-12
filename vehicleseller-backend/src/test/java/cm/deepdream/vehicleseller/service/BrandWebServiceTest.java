package cm.deepdream.vehicleseller.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import cm.deepdream.vehicleseller.config.VehicleSellerTestConfig;
import cm.deepdream.vehicleseller.model.Brand;

@Import(VehicleSellerTestConfig.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class BrandWebServiceTest {
	@LocalServerPort
	private int serverPort ;
	@Autowired
	private TestRestTemplate restTemplate ;
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	
	@Test
	public void testAdd() {
		Brand  testedBrand = new Brand(1L, null, "Toyota", "Toyota is the best in Africa") ;
		ResponseEntity<Brand> response =   restTemplate.postForEntity("/api/brand/add", testedBrand, Brand.class) ;
		Brand returnedBrand = jdbcTemplate.queryForObject("select * from brand where id=?", new Object[] {1L}, Brand.class) ;
		assertTrue(response.getStatusCode() == HttpStatus.OK  && returnedBrand.equals(testedBrand));
	}
	
	
	@Test
	public void testUpdate() {
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)", 
				2L, "Mercedes",  "Mercedes is the second in Africa");
		Brand testedBrand = new Brand(2L, null, "Mercedes", "Mercedes is the second in Africa") ;
		testedBrand.setLabel("KIA");
		testedBrand.setDescription("KIA is the second in Africa");
		restTemplate.put("/api/brand/update/{id}", testedBrand, Brand.class, 2L) ;
		
		Brand returnedBrand = jdbcTemplate.queryForObject("select * from brand where id=?", new Object[] {2L}, Brand.class) ;
		assertTrue(testedBrand.getLabel().equals(returnedBrand.getLabel()) &&
				testedBrand.getDescription().equals(returnedBrand.getDescription())) ;
	}
	
	
	@Test
	public void testGet() {
		Brand  testedBrand = new Brand(3L, null, "Peugeot", "Peugeot is a french brand") ;
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)",
				3L, "Peugeot",  "Peugeot is a french brand");

		Brand returnedBrand = restTemplate.getForObject("/api/brand/id/{id}",  Brand.class, 3L) ;
		assertTrue(returnedBrand != null && returnedBrand.equals(testedBrand));
	}
	
	
	/*@Test
	public void testGetAll() {
		List<Brand> listBrands = restTemplate.getForObject("/api/brand/all", List.class) ;
		
		Query query =  entityManager.createQuery("select b from Brand b") ;
		List<Brand> listBrands2  = query.getResultList() ;
		assertEquals(listBrands2.size(), listBrands.size());
	}*/

}

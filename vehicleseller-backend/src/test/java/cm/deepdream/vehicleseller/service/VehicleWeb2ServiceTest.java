package cm.deepdream.vehicleseller.service;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import cm.deepdream.vehicleseller.VehicleSellerTestConfig;
import cm.deepdream.vehicleseller.model.Model;
import cm.deepdream.vehicleseller.model.Picture;
import cm.deepdream.vehicleseller.model.Vehicle;

@Import(VehicleSellerTestConfig.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class VehicleWeb2ServiceTest {
	private Logger logger = Logger.getLogger(VehicleWeb2ServiceTest.class.getName()) ;
	@LocalServerPort
	private int serverPort ;
	@Autowired
	private TestRestTemplate restTemplate ;
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	
	@Test
	public void testAdd() {
		Model testedModel = new Model(111L, "Corolla 111", "Toyota 111") ;
		jdbcTemplate.update("insert into model (id, label, label_brand) values (?, ?, ?)",
				testedModel.getId(), testedModel.getLabel(),  testedModel.getLabelBrand());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("files", new File("dashboard.png"));
		body.add("registrationNumber", "CE690ID");
		body.add("category", "Premium");
		body.add("color", "Bleu");
		body.add("mileage", 10000);
		body.add("year", 2014);
		body.add("doors", 4);
		body.add("seats", 4);
		body.add("description", "");

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		
		ResponseEntity<Vehicle> response =  restTemplate.postForEntity("/api/vehicle/add-upload", requestEntity, Vehicle.class) ;
		
		assertTrue(response.getStatusCode() == HttpStatus.OK);
	}
	
	
	
	public static class VehicleRowMapper implements RowMapper<Vehicle> {
		private JdbcTemplate jdbcTemplate ;
		
		public VehicleRowMapper(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate ;
		}
		
	    @Override
	    public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Vehicle vehicle = new Vehicle();
	    	vehicle.setId(rs.getLong("id"));
	    	vehicle.setRegistrationNumber(rs.getString("registration_number"));
	    	vehicle.setColor(rs.getString("color")) ;
	    	vehicle.setYear(rs.getInt("year"));
	    	vehicle.setStatus(rs.getString("status"));
	    	vehicle.setDoors(rs.getInt("doors"));
	    	vehicle.setSeats(rs.getInt("seats"));
	    	return vehicle ;
	    }
	}
	

}

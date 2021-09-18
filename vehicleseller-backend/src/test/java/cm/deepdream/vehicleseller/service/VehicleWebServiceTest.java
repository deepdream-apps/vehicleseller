package cm.deepdream.vehicleseller.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertNotNull;
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
import cm.deepdream.vehicleseller.model.Picture;
import cm.deepdream.vehicleseller.model.Vehicle;

@Import(VehicleSellerTestConfig.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class VehicleWebServiceTest {
	@LocalServerPort
	private int serverPort ;
	@Autowired
	private TestRestTemplate restTemplate ;
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	
	/*@Test
	public void testAdd() {
		Brand  testedBrand = new Brand(111L, null, "Toyota 111", "Toyota is the best in Africa") ;
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)", 
				testedBrand.getId(), testedBrand.getLabel(),  testedBrand.getDescription());
		
		Model testedModel = new Model(111L, "Corolla 111", testedBrand, "Corolla is the most popular Toyota model") ;
		
		Picture picture = new Picture("", "", "", "", null) ;
		Vehicle testedVehicle = new Vehicle(111L, "CE690ID", testedModel, "GRAY", 2002, "Used", 4, 5,
				picture, "My car is a nice car. Believe me !") ;
		ResponseEntity<Model> response =  restTemplate.postForEntity("/api/vehicle/add", testedModel, Model.class) ;
		Vehicle returnedVehicle = null ;
		try {
			returnedVehicle = jdbcTemplate.queryForObject("select * from model where id=?", 
				new Object[] {testedVehicle.getId()}, new VehicleRowMapper(jdbcTemplate)) ;
		}catch(Exception ex) {}
		assertTrue(response.getStatusCode() == HttpStatus.OK &&  testedVehicle.equals(returnedVehicle));
	}
	
	
	@Test
	public void testUpdate() {
		Brand testedBrand1 = new Brand(112L, null, "Mercedes 112", "Mercedes is the second in Africa") ;
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)", 
				testedBrand1.getId(), testedBrand1.getLabel(),  testedBrand1.getDescription());
		
		Brand testedBrand2 = new Brand(113L, null, "Wolsvagen 113", "Wolsvagen is the second in Africa") ;
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)", 
				testedBrand2.getId(), testedBrand2.getLabel(),  testedBrand2.getDescription());
		
		Model testedModel1 = new Model(112L, "Mercedes 500 112L", testedBrand1, 
				"Mercedes 500 is the best Mercedes brand for me") ;
		
		Model testedModel2 = new Model(113L, "Wolsvagen 113L", testedBrand1, 
				"Wolsvagen 113L is the best Wolsvagen brand for me") ;
		
		Picture picture = new Picture("", "", "", "", null) ;
		Vehicle testedVehicle = new Vehicle(111L, "CE295HN", testedModel1, "BLACK", 2002, "Used", 4, 5,
				null, "My car is a nice car. Believe me !") ;
		
		testedVehicle.setModel(testedModel2);
		testedVehicle.setRegistrationNumber("LT295HM");
		testedVehicle.setColor("BLUE");
		testedVehicle.setYear(2004);
		testedVehicle.setStatus("New");
		testedVehicle.setSeats(2);
		testedVehicle.setDoors(2);
		testedVehicle.setPicture(picture);
		testedVehicle.setDescription("My car is so nice.");
		
		jdbcTemplate.update("insert into vehicle (id, registration_number, id_model, color, year, status, doors, seats, description) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)", 
				testedVehicle.getId(), testedVehicle.getRegistrationNumber(),  testedVehicle.getModel().getId(), testedVehicle.getColor(),
				testedVehicle.getYear(), testedVehicle.getStatus(), testedVehicle.getDoors(), testedVehicle.getSeats(), testedVehicle.getDescription());
		
		restTemplate.put("/api/vehicle/update/{id}", testedVehicle, testedVehicle.getId()) ;
		
		Vehicle returnedVehicle = jdbcTemplate.queryForObject("select * from vehicle where id=?", 
				new Object[] {testedVehicle.getId()}, new VehicleRowMapper(jdbcTemplate)) ;
		
		assertTrue(testedVehicle.equals(returnedVehicle)) ;
	}
	
	
	@Test
	public void testDelete() {
		Brand  testedBrand = new Brand(114L, null, "Renault 114L", "Peugeot is a french brand") ;
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)",
				testedBrand.getId(), testedBrand.getLabel(),  testedBrand.getDescription());
		
		Model  testedModel = new Model(113L, "Peugeot 600 113L", testedBrand, "Peugeot 600 is a french brand") ;
		jdbcTemplate.update("insert into model (id, label, description, id_brand) values (?, ?, ?, ?)",
				testedModel.getId(), testedModel.getLabel(),  testedModel.getDescription(), testedModel.getBrand().getId());
		
		Picture picture = new Picture("", "", "", "", null) ;
		Vehicle testedVehicle = new Vehicle(111L, "CE275HN", testedModel, "BLACK", 2019, "New", 4, 5,
				picture, "My car is a new. Trust it !") ;
		
		restTemplate.delete("/api/vehicle/id/{id}",  testedModel.getId()) ;
		
		Vehicle returnedVehicle = null ;
		try {
			returnedVehicle = jdbcTemplate.queryForObject("select * from vehicle where id=?", 
				new Object[] {testedVehicle.getId()}, new VehicleRowMapper(jdbcTemplate)) ;
		}catch(Exception ex) {}
		
		assertNotNull(returnedVehicle);
	}
	
	
	@Test
	public void testGet() {
		Brand  testedBrand = new Brand(115L, null, "Peugeot 105 115L", "Peugeot is a french brand") ;
		jdbcTemplate.update("insert into brand (id, label, description) values (?, ?, ?)",
				testedBrand.getId(), testedBrand.getLabel(),  testedBrand.getDescription());
		
		Model  testedModel = new Model(114L, "Peugeot 600 114L", testedBrand, "Peugeot 600 is a french brand") ;
		jdbcTemplate.update("insert into model (id, label, description, id_brand) values (?, ?, ?, ?)",
				testedModel.getId(), testedModel.getLabel(),  testedModel.getDescription(), testedModel.getBrand().getId());
		
		Picture picture = new Picture("", "", "", "", null) ;
		Vehicle testedVehicle = new Vehicle(111L, "CE275HN", testedModel, "BLACK", 2019, "New", 4, 5,
				picture, "My car is a new. Trust it !") ;

		Vehicle returnedVehicle = restTemplate.getForObject("/api/vehicle/id/{id}",  Vehicle.class, testedModel.getId()) ;
		assertTrue(returnedVehicle != null && 
				returnedVehicle.equals(testedVehicle) );
	}
	
	
	
	@Test
	public void testGetAll() {
		List<Vehicle> listVehicles = restTemplate.getForObject("/api/vehicle/all", List.class) ;
		
		List<Vehicle> listVehicles2  = jdbcTemplate.query("select * from vehicle", new VehicleRowMapper(jdbcTemplate)) ;
		assertEquals(listVehicles.size(), listVehicles2.size());
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
	    	vehicle.setRegistrationNumber(rs.getString("registrationNumber"));
	    	vehicle.setColor(rs.getString("color")) ;
	    	vehicle.setYear(rs.getInt("year"));
	    	vehicle.setStatus(rs.getString("status"));
	    	vehicle.setDoors(rs.getInt("doors"));
	    	vehicle.setSeats(rs.getInt("seats"));
	    	Model model = jdbcTemplate.queryForObject("select * from model where id= ?",  
	    			new Object[] {rs.getLong("id_model")}, new ModelWebServiceTest.ModelRowMapper(jdbcTemplate)) ;
	        vehicle.setModel(model);
	    	return vehicle ;
	    }
	}
	*/

}

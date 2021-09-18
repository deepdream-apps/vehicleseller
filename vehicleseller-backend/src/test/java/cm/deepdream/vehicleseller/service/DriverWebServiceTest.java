package cm.deepdream.vehicleseller.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import cm.deepdream.vehicleseller.model.Driver;
import cm.deepdream.vehicleseller.model.DriverLicence;
import cm.deepdream.vehicleseller.model.Picture;

@Import(VehicleSellerTestConfig.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class DriverWebServiceTest {
	@LocalServerPort
	private int serverPort ;
	@Autowired
	private TestRestTemplate restTemplate ;
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	
	@Test
	public void testAdd() {
		
		DriverLicence driverLicence = new DriverLicence("100L", LocalDate.of(2020, 1, 12), LocalDate.of(2030, 1, 12)) ;
		Driver  testedDriver = new Driver(120L, "21M01", "Christian", "Owodo", 
				"Homme", LocalDate.of(1980, 1, 12), LocalDate.of(2021, 4, 1),driverLicence, new Picture()) ;
		ResponseEntity<Driver> response =   restTemplate.postForEntity("/api/driver/add", testedDriver, Driver.class) ;
		Driver returnedDriver = jdbcTemplate.queryForObject("select * from driver where id=?", 
				new Object[] {testedDriver.getId()}, new DriverRowMapper()) ;
		assertTrue(response.getStatusCode() == HttpStatus.OK  && returnedDriver.getId() == returnedDriver.getId()
				&& returnedDriver.getRegistrationNumber().equals(testedDriver.getRegistrationNumber())
				&& returnedDriver.getFirstName().equals(testedDriver.getFirstName())
				&& returnedDriver.getLastName().equals(testedDriver.getLastName())
				&& returnedDriver.getSex().equals(testedDriver.getSex()));
	}
	
	
	@Test
	public void testUpdate() {
		DriverLicence driverLicence = new DriverLicence("120L", LocalDate.of(2018, 1, 12), LocalDate.of(2028, 1, 12)) ;
		Driver  testedDriver = new Driver(121L, "21M02", "Herve", "Yombi", 
				"Homme", LocalDate.of(1982, 11, 10), LocalDate.of(2019, 4, 10),driverLicence, new Picture()) ;
		
		jdbcTemplate.update("insert into driver (id, birth_day, expiration_date, issue_date, licence_id, first_name, last_name, content_type, filename, path, sub_path,registration_number, sex, recruit_day) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
				testedDriver.getId(), testedDriver.getBirthDay(), testedDriver.getDriverLicence().getExpirationDate(), 
				testedDriver.getDriverLicence().getIssueDate(), testedDriver.getDriverLicence().getLicenceId(), 
				testedDriver.getFirstName(), testedDriver.getLastName(), testedDriver.getPhoto().getContentType(),
				testedDriver.getPhoto().getFileName(), testedDriver.getPhoto().getPath(), testedDriver.getPhoto().getSubPath(),
				testedDriver.getRegistrationNumber(),  testedDriver.getSex(), testedDriver.getRecruitDay());
	
		DriverLicence driverLicence2 = new DriverLicence("121L", LocalDate.of(2021, 1, 15), LocalDate.of(2031, 1, 15)) ;
		testedDriver.setRegistrationNumber("21S02");
		testedDriver.setLastName("Herve Bertrand");
		testedDriver.setFirstName("Yombi Anguisso");
		testedDriver.setDriverLicence(driverLicence2);
		
		restTemplate.put("/api/driver/update/{id}", testedDriver, testedDriver.getId()) ;
		
		Driver returnedDriver = jdbcTemplate.queryForObject("select * from driver where id=?", 
				new Object[] {testedDriver.getId()}, new DriverRowMapper()) ;
		assertTrue(returnedDriver.getId() == returnedDriver.getId()
				&& returnedDriver.getRegistrationNumber().equals(testedDriver.getRegistrationNumber())
				&& returnedDriver.getFirstName().equals(testedDriver.getFirstName())
				&& returnedDriver.getLastName().equals(testedDriver.getLastName())
				&& returnedDriver.getSex().equals(testedDriver.getSex()));
	}
	
	
	@Test
	public void testGet() {
		DriverLicence driverLicence = new DriverLicence("123L", LocalDate.of(2016, 11, 12), LocalDate.of(2026, 1, 12)) ;
		Driver  testedDriver = new Driver(122L, "21M02", "Moussa", "Yaya", 
				"Homme", LocalDate.of(1992, 12, 10), LocalDate.of(2019, 4, 10),driverLicence, new Picture()) ;
		
		jdbcTemplate.update("insert into driver (id, birth_day, expiration_date, issue_date, licence_id, first_name, last_name, content_type, filename, path, sub_path,registration_number, sex, recruit_day) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
				testedDriver.getId(), testedDriver.getBirthDay(), testedDriver.getDriverLicence().getExpirationDate(), 
				testedDriver.getDriverLicence().getIssueDate(), testedDriver.getDriverLicence().getLicenceId(), 
				testedDriver.getFirstName(), testedDriver.getLastName(), testedDriver.getPhoto().getContentType(),
				testedDriver.getPhoto().getFileName(), testedDriver.getPhoto().getPath(), testedDriver.getPhoto().getSubPath(),
				testedDriver.getRegistrationNumber(),  testedDriver.getSex(), testedDriver.getRecruitDay());
	
		Driver returnedDriver = restTemplate.getForObject("/api/driver/id/{id}",  Driver.class, testedDriver.getId()) ;
		
		assertTrue(returnedDriver != null && returnedDriver.getId() == returnedDriver.getId()
				&& returnedDriver.getRegistrationNumber().equals(testedDriver.getRegistrationNumber())
				&& returnedDriver.getFirstName().equals(testedDriver.getFirstName())
				&& returnedDriver.getLastName().equals(testedDriver.getLastName())
				&& returnedDriver.getSex().equals(testedDriver.getSex()));
	}
	
	
	@Test
	public void testDelete() {
		DriverLicence driverLicence = new DriverLicence("124L", LocalDate.of(2016, 11, 12), LocalDate.of(2026, 1, 12)) ;
		Driver  testedDriver = new Driver(122L, "21M04", "Bertrand", "Yaleu", 
				"Homme", LocalDate.of(1992, 12, 10), LocalDate.of(2019, 4, 10),driverLicence, new Picture()) ;
		
		restTemplate.delete("/api/driver/id/{id}", testedDriver.getId()) ;
		
		Driver returnedBrand = null ;
		try {
			returnedBrand = jdbcTemplate.queryForObject("/api/brand/id/{id}", Driver.class, testedDriver.getId()) ;
		}catch(Exception ex) {} ;
		assertNull(returnedBrand);
	}
	
	
	@Test
	public void testGetAll() {
		List<Driver> listDrivers = restTemplate.getForObject("/api/driver/all", List.class) ;
		List<Driver> listDrivers2  = jdbcTemplate.query("select * from driver", new DriverRowMapper()) ;
		assertEquals(listDrivers.size(), listDrivers2.size());
	}
	
	public static class DriverRowMapper implements RowMapper<Driver> {
	    @Override
	    public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Driver driver = new Driver();
	    	driver.setId(rs.getLong("id"));
	    	driver.setRegistrationNumber(rs.getString("registration_number"));
	    	driver.setFirstName(rs.getString("first_name")) ;
	    	driver.setLastName(rs.getString("last_name")) ;
	    	driver.setSex(rs.getString("sex"));
	        return driver;
	    }
	}

}

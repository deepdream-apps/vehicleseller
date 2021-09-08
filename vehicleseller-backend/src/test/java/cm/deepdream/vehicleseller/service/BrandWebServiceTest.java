package cm.deepdream.vehicleseller.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import cm.deepdream.vehicleseller.config.VehicleSellerTestConfig;
@ActiveProfiles("test")
@Import(VehicleSellerTestConfig.class)
@SpringBootTest
public class BrandWebServiceTest {
	@Value("${server.port}")
	private int serverPort ;
	
	@BeforeEach
	public void setUp() {
		
	}
	
	
	public void testAdd() {
		
	}
	
	public void testUpdate() {
		
	}
	
	
	public void testGet() {
		
	}

}

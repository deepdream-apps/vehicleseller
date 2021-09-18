package cm.deepdream.vehicleseller;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
@RestController
@SpringBootApplication
public class VehiclesellerBackendApplication {
	@Autowired
	private Environment env ;
	@Value("${app.vehicleseller.aws.accessKey}")
	private String accessKey ;
	@Value("${app.vehicleseller.aws.secretKey}")
	private String secretKey ;
	@Value("${app.vehicleseller.aws.regionCode}")
	private String regionCode ;
	
	
	public VehiclesellerBackendApplication() {
		super() ;
	}


	public static void main(String[] args) {
		SpringApplication.run(VehiclesellerBackendApplication.class, args);
	}
	
	
	@Bean
    public AmazonS3  s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(regionCode)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				        .allowedOrigins("*")
				        .allowedOrigins("http://localhost:3002")
				        .allowedMethods("GET", "POST", "PUT", "DELETE")
				        .allowCredentials(false)
				        .maxAge(3600);
			}
		};
	}
	
	@GetMapping("/user")
    public Principal user(Principal p){
        return p;
    }
	
	//https://howtodoinjava.com/spring-boot2/spring-cors-configuration/

}

package cm.deepdream.vehicleseller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
public class VehiclesellerBackendApplication {
	@Value("${app.vehicleseller.aws.accessKey}")
	private String accessKey ;
	@Value("${app.vehicleseller.aws.secretKey}")
	private String secretKey ;
	@Value("${app.vehicleseller.aws.regionCode}")
	private String regionCode ;
	
	NullPointerException dd ;
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
				        .allowedOrigins("http://localhost:3000");
			}
			
		};
	}
	
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate() ;
	}
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo())
        		.select()
                	.apis(RequestHandlerSelectors.basePackage("cm.deepdream.vehicleseller.webservice"))
                	.paths(p -> p.startsWith("/api"))
                .build();
    }
	
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Vehicle Seller API")
				.description("JavaInUse API reference for developers")
				.termsOfServiceUrl("http://javainuse.com")
				//.contact("javainuse@gmail.com").license("JavaInUse License")
				.licenseUrl("javainuse@gmail.com").version("1.0").build();
	}

}

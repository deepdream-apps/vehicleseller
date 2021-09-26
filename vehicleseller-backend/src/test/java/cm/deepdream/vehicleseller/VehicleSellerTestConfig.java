package cm.deepdream.vehicleseller;
import java.util.Arrays;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

@TestConfiguration
public class VehicleSellerTestConfig {
	@Autowired
	private Environment env ;
	
	
	 @Bean
	 public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
	    dataSource.setUrl(env.getProperty("spring.datasource.url"));
	    dataSource.setUsername(env.getProperty("spring.datasource.username"));
	    dataSource.setPassword(env.getProperty("spring.datasource.password"));
	    return dataSource;
	 }
	 
	 
	 /*@Bean
	 public OAuth2RestTemplate oAuth2RestTemplate1() {
		 ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails() ;
		 resource.setAccessTokenUri("https://www.googleapis.com/oauth2/v3/token");
		 resource.setClientId(env.getProperty("security.oauth2.client.client-id"));
		 resource.setClientSecret(env.getProperty("security.oauth2.client.client-secret"));
		 resource.setGrantType("implicit");
		 resource.setUsername("silvere.djam@gmail.com");
		 resource.setPassword("Jean17:300");
		 resource.setAuthenticationScheme(AuthenticationScheme.form);
		 OAuth2RestTemplate restTemplate = new OAuth2RestTemplate (resource) ;
	     return restTemplate ;
	 }*/
	 
	 @Bean
	 public OAuth2RestTemplate oAuth2RestTemplate2() {
		 ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
	     
	     resourceDetails.setClientId(env.getProperty("security.oauth2.client.client-id"));
	     resourceDetails.setClientSecret(env.getProperty("security.oauth2.client.client-secret"));
	     resourceDetails.setGrantType("client_credentials");
	     resourceDetails.setScope(Arrays.asList("read", "write"));
	     resourceDetails.setTokenName("oauth_token");
	     resourceDetails.setAuthenticationScheme(AuthenticationScheme.form);
	     resourceDetails.setScope(Arrays.asList("email","profile"));
	     resourceDetails.setAccessTokenUri("https://www.googleapis.com/oauth2/v3/token");
	     DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

	     OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
	     restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
	     return restTemplate ;
	 }
}

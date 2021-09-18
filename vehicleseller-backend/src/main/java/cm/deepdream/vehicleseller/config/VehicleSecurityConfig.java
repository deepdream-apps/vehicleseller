package cm.deepdream.vehicleseller.config;

//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
public class VehicleSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .csrf().disable()
          .authorizeRequests()
          .antMatchers("/login")
          .permitAll()
          .anyRequest()
          .authenticated() ;
    }
	
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationMgr) throws Exception {
	     authenticationMgr.inMemoryAuthentication().withUser("admin").password("admin")
	          .authorities("ROLE_ADMIN");
	}

}

package cm.deepdream.vehicleseller.config;

//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;


//@Configuration
//@EnableWebSecurity
//@EnableOAuth2Sso
public class VehicleSecurityConfig {}

/*public class VehicleSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
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
*/
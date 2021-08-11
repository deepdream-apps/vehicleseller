package cm.deepdream.vehicleseller.config;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import cm.deepdream.vehicleseller.webservice.BrandWS;
import cm.deepdream.vehicleseller.webservice.CountryWS;
import cm.deepdream.vehicleseller.webservice.ModelWS;
import cm.deepdream.vehicleseller.webservice.TownWS;
import cm.deepdream.vehicleseller.webservice.UserWS;
import cm.deepdream.vehicleseller.webservice.VehicleWS;
@Component
public class VehicleSellerConfig extends ResourceConfig{
	
	public VehicleSellerConfig(Class<?>...classes ) {
		register(BrandWS.class) ;
		register(ModelWS.class) ;
		register(CountryWS.class) ;
		register(TownWS.class) ;
		register(VehicleWS.class) ;
		register(UserWS.class) ;
	}
	
}

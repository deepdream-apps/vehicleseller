package cm.deepdream.vehicleseller.config;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class VehicleSellerConfig extends ResourceConfig{
	
	public VehicleSellerConfig() {
		packages("cm.deepdream.vehicleseller.webservice") ;
		register(MultiPartFeature.class);
	}
	
	
	
}

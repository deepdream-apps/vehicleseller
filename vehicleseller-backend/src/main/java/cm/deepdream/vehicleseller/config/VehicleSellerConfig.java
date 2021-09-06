package cm.deepdream.vehicleseller.config;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import cm.deepdream.vehicleseller.webservice.BillWS;
import cm.deepdream.vehicleseller.webservice.BrandWS;
import cm.deepdream.vehicleseller.webservice.BreakdownWS;
import cm.deepdream.vehicleseller.webservice.CountryWS;
import cm.deepdream.vehicleseller.webservice.CustomerWS;
import cm.deepdream.vehicleseller.webservice.DriverWS;
import cm.deepdream.vehicleseller.webservice.EmptyingWS;
import cm.deepdream.vehicleseller.webservice.InsuranceContractWS;
import cm.deepdream.vehicleseller.webservice.InsuranceWS;
import cm.deepdream.vehicleseller.webservice.MaintenanceOperationWS;
import cm.deepdream.vehicleseller.webservice.ModelWS;
import cm.deepdream.vehicleseller.webservice.PaymentWS;
import cm.deepdream.vehicleseller.webservice.ReservationWS;
import cm.deepdream.vehicleseller.webservice.SellerWS;
import cm.deepdream.vehicleseller.webservice.TechnicalVisitWS;
import cm.deepdream.vehicleseller.webservice.TownWS;
import cm.deepdream.vehicleseller.webservice.UserWS;
import cm.deepdream.vehicleseller.webservice.VehicleAssignmentWS;
import cm.deepdream.vehicleseller.webservice.VehicleWS;
@Component
public class VehicleSellerConfig extends ResourceConfig{
	
	public VehicleSellerConfig(Class<?>...classes ) {
		register(BillWS.class) ;
		register(BrandWS.class) ;
		register(BreakdownWS.class) ;
		register(CountryWS.class) ;
		register(CustomerWS.class) ;
		register(DriverWS.class) ;
		register(EmptyingWS.class) ;
		register(InsuranceContractWS.class) ;
		register(InsuranceWS.class) ;
		register(MaintenanceOperationWS.class) ;
		register(ModelWS.class) ;
		register(PaymentWS.class) ;
		register(ReservationWS.class) ;
		register(SellerWS.class) ;
		register(TechnicalVisitWS.class) ;
		register(TownWS.class) ;
		register(UserWS.class) ;
		register(VehicleAssignmentWS.class) ;
		register(VehicleWS.class) ;
	}
	
	
	
}

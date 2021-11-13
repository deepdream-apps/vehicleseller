package cm.deepdream.vehicleseller.dto;
import java.math.BigDecimal;
import java.time.LocalDate;

import cm.deepdream.vehicleseller.model.Bill;
import cm.deepdream.vehicleseller.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDTO {
	private Long id ;

	private String registrationNumber ;

	private String chassisNumber ;

	private ModelDTO model ;
	
	private String category ;

	private String color ;

	private Integer year ;
	
	private Integer mileage ;

	private String status ;

	private Integer doors ;

	private Integer seats ;

	private String description ;

	private String imageName ;
}

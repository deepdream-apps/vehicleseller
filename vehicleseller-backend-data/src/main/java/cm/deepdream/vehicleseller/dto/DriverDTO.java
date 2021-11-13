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
public class DriverDTO {
	private Long id ;
	
	private String registrationNumber ;
	
	private String firstName ;

	private String lastName ;

	private String status ;

	private LocalDate birthDay ;

	private LocalDate recruitDay ;

	private String emailAddress ;

	private String phoneNumber ;

	private DriverLicenceDTO driverLicence ;

	private PictureDTO photo ;
}

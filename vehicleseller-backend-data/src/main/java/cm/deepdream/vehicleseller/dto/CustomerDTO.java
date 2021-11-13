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
public class CustomerDTO {
	private Long id ;

	private String firstName ;

	private String lastName ;
	
	private String gender ;

	private LocalDate birthDay ;

	private LocalDate subscriptionDate ;

	private String town ;

	private String country ;

	private String emailAddress ;

	private String phoneNumber ;

	private String nationality ;

	private String profession ;
	
}

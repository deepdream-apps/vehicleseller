package cm.deepdream.vehicleseller.dto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class UserDTO {
	private Long id ;

	private  String emailAddress ;

	private String firstName ;

	private String lastName ;

	private String roleName ;

	private char[] password ;

	private LocalDateTime creationDate ;

	private String status ;
	
}

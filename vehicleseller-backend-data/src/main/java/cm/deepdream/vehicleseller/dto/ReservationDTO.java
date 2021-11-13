package cm.deepdream.vehicleseller.dto;
import java.time.LocalDate ;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {
	private Long id ;
	
	private VehicleDTO vehicle ;
	
	private CustomerDTO customer ;

	private LocalDateTime date ;

	private LocalDate startDate ;

	private String duration ;

	private String category ;

	private String prestation ;

	private String departureCity ;

	private String arrivalCity ;

	private String status ;

	private String description ;
}

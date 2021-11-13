package cm.deepdream.vehicleseller.dto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionDTO {
	private Long id ;

	private DriverDTO driver ;

	private VehicleDTO vehicle ;

	private LocalDateTime startDate ;

	private LocalDateTime endDate ;

	private Integer mileageAtBegin ;

	private Integer mileageAtEnd ;

	private String status ;
}

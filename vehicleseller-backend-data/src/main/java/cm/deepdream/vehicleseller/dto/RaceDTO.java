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
public class RaceDTO {
	private Long id ;
	
	private VehicleDTO vehicle ;
	
	private DriverDTO driver ;
	
	private CustomerDTO customer ;
	
	private LocalDateTime startingDate ;
	
	private LocalDateTime endingDate ;
	
	private String status ;
}

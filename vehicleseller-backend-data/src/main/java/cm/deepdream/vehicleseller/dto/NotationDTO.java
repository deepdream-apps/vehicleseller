package cm.deepdream.vehicleseller.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotationDTO {
	private Long id ;
	
	private RaceDTO race ;
	
	private Integer vehicleNotation ;
	
	private Integer driverNotation ;
	
	private String observations ;
}

package cm.deepdream.vehicleseller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO {
	private Long id ;

	private RaceDTO race ;

	private String category ;

	private String description ;
	
}

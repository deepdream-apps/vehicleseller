package cm.deepdream.vehicleseller.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelDTO {
	private Long id ;

	private String label ;

	private String labelBrand ;

}

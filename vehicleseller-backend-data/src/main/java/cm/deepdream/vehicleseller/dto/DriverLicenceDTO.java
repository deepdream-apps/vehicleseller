package cm.deepdream.vehicleseller.dto;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverLicenceDTO {
	private String licenceId ;

	private LocalDate issueDate ;

	private LocalDate expirationDate ;

}

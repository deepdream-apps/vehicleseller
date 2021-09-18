package cm.deepdream.vehicleseller.model;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverLicence {
	@Column(name = "licence_id")
	private String licenceId ;
	
	@Past
	@Column(name = "issue_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate issueDate ;
	
	@Column(name = "expiration_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expirationDate ;

}

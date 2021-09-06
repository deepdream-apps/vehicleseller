package cm.deepdream.vehicleseller.model;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emptying {
	@Id
	@Column(name = "id")
	private Long id ;
	
	@Column(name = "company")
	private String company ;
	
	@Column(name = "visit_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate visitDate ;
	
	@Column(name = "expiry_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiryDate ;
	
	@Column(name = "status")
	private String status ;
	
	@Column(name = "observations")
	private String observations ;
}

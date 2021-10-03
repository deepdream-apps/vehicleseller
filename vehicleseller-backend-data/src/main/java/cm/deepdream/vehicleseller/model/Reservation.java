package cm.deepdream.vehicleseller.model;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	@Id
	@Column(name = "id")
	private Long id ;

	@ManyToOne
	@JoinColumn (name = "id_vehicle")
	private Vehicle vehicle ;
	
	@ManyToOne
	@JoinColumn (name = "id_insurance")
	private Customer customer ;
	
	@Column(name = "start_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime startDate ;
	
	@Column(name = "end_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime endDate ;
	
	/*Cancelled, Done, Confirmed*/
	@Column(name = "status")
	private String status ;
	
	@Column(name = "description")
	private String description ;
}

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
public class VehicleAssignment {
	@Id
	@Column(name = "id")
	private Long id ;

	@ManyToOne
	@JoinColumn (name = "id_vehicle")
	private Vehicle vehicle ;
	
	@ManyToOne
	@JoinColumn (name = "id_driver")
	private Driver driver ;
	
	@Column(name = "start_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime startDate ;
	
	@Column(name = "end_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime endDate ;
}

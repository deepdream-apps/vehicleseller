package cm.deepdream.vehicleseller.model;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id ;
	
	@NotNull
	@ManyToOne
	@JoinColumn (name = "id_driver")
	private Driver driver ;
	
	@ManyToOne
	@JoinColumn (name = "id_vehicle")
	private Vehicle vehicle ;
	
	@Column(name = "start_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDate ;
	
	@Column(name = "end_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDate ;
	
	@Column(name = "mileage_begin")
	private Integer mileageAtBegin ;
	
	@Column(name = "mileage_end")
	private Integer mileageAtEnd ;
	
	@Column(name = "status")
	private String status ;
}

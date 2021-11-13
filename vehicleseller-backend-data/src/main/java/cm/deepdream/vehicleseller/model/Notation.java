package cm.deepdream.vehicleseller.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notation {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id ;
	
	@ManyToOne
	@JoinColumn (name = "id_race")
	private Race race ;
	
	@Max(value = 10)
	@Min(value = 0)
	@Column(name = "vehicle_notation")
	private Integer vehicleNotation ;
	
	@Max(value = 10)
	@Min(value = 0)
	@Column(name = "driver_notation")
	private Integer driverNotation ;
	
	@Column(name = "observations")
	private String observations ;
}

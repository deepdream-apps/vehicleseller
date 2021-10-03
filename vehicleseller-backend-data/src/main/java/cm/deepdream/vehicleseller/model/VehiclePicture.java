package cm.deepdream.vehicleseller.model;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiclePicture {
	@Id
	@Column(name = "id")
	private Long id ;
	
	@ManyToOne
	@JoinColumn (name = "id_vehicle")
	private Vehicle vehicle ;
	
	@Embedded
	private Picture picture ;
	
	@Column(name = "label")
	private String label ;
	
	
}

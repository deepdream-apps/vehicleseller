package cm.deepdream.vehicleseller.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id ;
	
	@NotNull
	@Column(name = "registration_number")
	private String registrationNumber ;
	
	@NotNull
	@Column(name = "chassis_number")
	private String chassisNumber ;
	
	@NotNull
	@ManyToOne
	@JoinColumn (name = "id_model")
	private Model model ;
	
	@Column(name = "category")
	private String category ;
	
	@Column(name = "color")
	private String color ;
	
	@Column(name = "year")
	private Integer year ;
	
	@Column(name = "mileage")
	private Integer mileage ;
	
	@Column(name = "status")
	private String status ;

	@Column(name = "doors")
	private Integer doors ;
	
	@Column(name = "seats")
	private Integer seats ;

	@Column(name = "description")
	private String description ;
	
	@Column(name = "image_name")
	private String imageName ;
}

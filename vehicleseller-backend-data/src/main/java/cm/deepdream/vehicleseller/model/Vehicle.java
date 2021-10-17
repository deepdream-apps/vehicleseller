package cm.deepdream.vehicleseller.model;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
public class Vehicle {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id ;
	
	@Column(name = "registration_number")
	private String registrationNumber ;
	
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

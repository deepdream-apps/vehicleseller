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
public class Vehicle {
	@Id
	@Column(name = "id")
	private Long id ;
	
	@Column(name = "registration_number")
	private String registrationNumber ;
	
	@ManyToOne
	@JoinColumn (name = "id_model")
	private Model model ;
	
	@Column(name = "color")
	private String color ;
	
	@Column(name = "year")
	private Integer year ;
	
	@Column(name = "status")
	private String status ;

	@Column(name = "doors")
	private Integer doors ;
	
	@Column(name = "seats")
	private Integer seats ;
	
	@Embedded
	private Picture picture ;

	@ManyToOne
	@JoinColumn (name = "id_town")
	private Town town ;
	
	@Column(name = "description")
	private String description ;

}

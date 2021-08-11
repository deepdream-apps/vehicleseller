package cm.deepdream.vehicleseller.model;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
public class Vehicle {
	@Id
	@Column(name = "id")
	private Long id ;
	
	@Column(name = "registration_number")
	private String registrationNumber ;
	
	@Column(name = "label")
	private String label ;
	
	@ManyToOne
	@JoinColumn (name = "id_brand")
	private Brand brand ;
	
	@ManyToOne
	@JoinColumn (name = "id_model")
	private Model model ;
	
	@Column(name = "color")
	private String color ;
	
	@Column(name = "year")
	private Integer year ;
	
	@Column(name = "status")
	private String status ;
	
	@Column(name = "fuel")
	private String fuel ;
	
	@Column(name = "gearbox")
	private String gearbox ;
	
	@Column(name = "transmission")
	private String transmission ;
	
	@Column(name = "mileage")
	private Long mileage ;
	
	@Column(name = "doors")
	private Integer doors ;
	
	@Column(name = "seats")
	private Integer seats ;
	
	@Column(name = "weight")
	private Integer weight ;
	
	@Column(name = "published")
	@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime published ;
	
	@ManyToOne
	@JoinColumn (name = "id_country")
	private Country country ;
	
	@ManyToOne
	@JoinColumn (name = "id_seller")
	private Seller seller ;
	
	@Embedded
	private Price price ;
	
	@Column(name = "description")
	private String description ;
	
	@Embedded
	private Picture picture1 ;
}

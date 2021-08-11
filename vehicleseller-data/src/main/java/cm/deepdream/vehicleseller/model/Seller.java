package cm.deepdream.vehicleseller.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller {
	@Id
	@Column(name = "id")
	private Long id ;
	
	@Column(name = "first_name")
	private String firstName ;
	
	@Column(name = "last_name")
	private String lastName ;
	
	@Column(name = "email_address")
	private String emailAddress ;
	
	@Column(name = "phone_number")
	private String phoneNumber ;
	
	
}

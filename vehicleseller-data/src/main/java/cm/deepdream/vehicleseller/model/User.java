package cm.deepdream.vehicleseller.model;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@Column(name = "id")
	private Long id ;
	
	@Column(name = "email_address")
	private  String emailAddress ;
	
	@Column(name = "first_name")
	private String firstName ;
	
	@Column(name = "last_name")
	private String lastName ;
	
	/**Subscriber, Seller, Administrator**/
	@Column(name = "role_name")
	private String roleName ;
	
	@Column(name = "password")
	private String password ;
	
	@Column(name = "creation_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationDate ;
	
	@Column(name = "password_expiration_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime passwordExpirationDate ;
	
	@Column(name = "status")
	private String status ;
	
}

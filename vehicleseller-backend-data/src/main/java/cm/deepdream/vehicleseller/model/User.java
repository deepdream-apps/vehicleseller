package cm.deepdream.vehicleseller.model;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class User {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id ;
	
	@Column(name = "email_address")
	private  String emailAddress ;
	
	@Column(name = "first_name")
	private String firstName ;
	
	@Column(name = "last_name")
	private String lastName ;
	
	/**Client, Opérateur, Administrateur, Gérant, Chauffeur**/
	@Column(name = "role_name")
	private String roleName ;
	
	@Column(name = "password")
	private char[] password ;
	
	@Column(name = "creation_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationDate ;
	
	@Column(name = "status")
	private String status ;
	
}

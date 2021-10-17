package cm.deepdream.vehicleseller.model;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id ;
	
	@Column(name = "first_Name")
	private String firstName ;
	
	@NotNull
	@Column(name = "last_Name")
	private String lastName ;
	
	@Column(name = "gender")
	private String gender ;
	
	@Past
	@Column(name = "birth_day")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDay ;
	
	@Column(name = "subscription_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate subscriptionDate ;
	
	@Column(name = "town")
	private String town ;
	
	@Column(name = "country")
	private String country ;
	
	@Email
	@Column(name = "email_address")
	private String emailAddress ;
	
	@Column(name = "phone_number")
	private String phoneNumber ;
	
	@Column(name = "nationality")
	private String nationality ;
	
	@Column(name = "profession")
	private String profession ;
	
}

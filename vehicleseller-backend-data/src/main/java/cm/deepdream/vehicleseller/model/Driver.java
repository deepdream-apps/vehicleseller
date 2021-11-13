package cm.deepdream.vehicleseller.model;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;

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
public class Driver {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id ;
	
	@NotNull
	@Column(name = "registration_number")
	private String registrationNumber ;
	
	@Column(name = "first_Name")
	private String firstName ;
	
	@NotNull
	@Column(name = "last_Name")
	private String lastName ;
	
	@NotNull
	@Column(name = "status")
	private String status ;
	
	@Past
	@Column(name = "birth_day")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDay ;
	
	@PastOrPresent
	@Column(name = "recruit_day")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate recruitDay ;
	
	@Email
	@Column(name = "email_address")
	private String emailAddress ;
	
	@Column(name = "phone_number")
	private String phoneNumber ;
	
	@Embedded
	private DriverLicence driverLicence ;

}

package cm.deepdream.vehicleseller.model;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Driver {
	@Id
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
	@Column(name = "sex")
	private String sex ;
	
	@Past
	@Column(name = "birth_day")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDay ;
	
	@Past
	@Column(name = "recruit_day")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate recruitDay ;
	
	@Embedded
	private DriverLicence driverLicence ;
	
	@Embedded
	private Picture photo ;
}

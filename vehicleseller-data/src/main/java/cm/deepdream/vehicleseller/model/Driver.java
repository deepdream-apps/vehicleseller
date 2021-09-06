package cm.deepdream.vehicleseller.model;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
public class Driver {
	@Id
	@Column(name = "id")
	private Long id ;
	
	@Column(name = "first_Name")
	private String firstName ;
	
	@Column(name = "last_Name")
	private String lastName ;
	
	@Column(name = "birth_day")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDay ;
	
	@Embedded
	private DriverLicence driverLicence ;
	
	@Embedded
	private Picture photo ;
}

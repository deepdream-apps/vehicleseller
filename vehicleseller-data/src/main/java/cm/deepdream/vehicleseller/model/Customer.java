package cm.deepdream.vehicleseller.model;
import java.time.LocalDate;

import javax.persistence.Column;
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
public class Customer {
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
	
	@ManyToOne
	@JoinColumn(name = "id_town")
	private Town town ;
	
	@Column(name = "email_address")
	private String emailAddress ;
	
	@Column(name = "phone_number")
	private String phoneNumber ;
}

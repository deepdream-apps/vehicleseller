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
public class Breakdown {
	@Id
	@Column(name = "id")
	private Long id ;
	
	@Column(name = "label")
	private String label ;

	@ManyToOne
	@JoinColumn (name = "id_vehicle")
	private Vehicle vehicle ;

	@Column(name = "breakdown_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date ;
	
	@Column(name = "description")
	private String description ;
}

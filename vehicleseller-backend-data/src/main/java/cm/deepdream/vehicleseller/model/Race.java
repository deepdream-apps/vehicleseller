package cm.deepdream.vehicleseller.model;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
public class Race {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id ;
	
	@ManyToOne
	@JoinColumn(name = "id_vehicle")
	private Vehicle vehicle ;
	
	@ManyToOne
	@JoinColumn(name = "id_driver")
	private Driver driver ;
	
	@ManyToOne
	@JoinColumn(name = "id_customer")
	private Customer customer ;
	
	@Column(name = "starting_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime startingDate ;
	
	@Column(name = "ending_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime endingDate ;
	
	@NotNull
	@Column(name = "status")
	private String status ;
}

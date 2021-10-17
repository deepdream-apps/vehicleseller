package cm.deepdream.vehicleseller.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
public class Reservation {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id ;

	@ManyToOne
	@JoinColumn (name = "id_vehicle")
	private Vehicle vehicle ;
	
	@ManyToOne
	@JoinColumn (name = "id_insurance")
	private Customer customer ;
	
	@Column(name = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime date ;
	
	@Column(name = "start_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate ;
	
	@Column(name = "duration")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime duration ;
	
	@Column(name = "category")
	private String category ;
	
	@Column(name = "prestation")
	private String prestation ;
	
	@Column(name = "departure_city")
	private String departureCity ;
	
	@Column(name = "arrival_city")
	private String arrivalCity ;
	
	/**
	 * Valeurs possibles du statut d'un véhicule :
	 * Annulé, En attente, Confirmé, Terminé
	 * */
	@Column(name = "status")
	private String status ;
	
	@Column(name = "description")
	private String description ;
}

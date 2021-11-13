package cm.deepdream.vehicleseller.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
public class Reservation {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id ;

	@ManyToOne
	@JoinColumn (name = "id_vehicle")
	private Vehicle vehicle ;
	
	@NotNull
	@ManyToOne
	@JoinColumn (name = "id_customer")
	private Customer customer ;
	
	@Column(name = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime date ;
	
	@Column(name = "start_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate ;
	
	@Positive
	@Column(name = "duration")
	//@DateTimeFormat(pattern = "HH:mm:ss")
	private String duration ;
	
	@NotNull
	@Column(name = "category")
	private String category ;
	
	@Column(name = "prestation")
	private String prestation ;
	
	@Column(name = "departure_city")
	private String departureCity ;
	
	@Column(name = "arrival_city")
	private String arrivalCity ;
	
	@Column(name = "departure_neighborhood")
	private String departureNeighborhood ;
	
	@Column(name = "arrival_neighborhood")
	private String arrivalNeighborhood ;
	
	/**
	 * Valeurs possibles du statut d'un véhicule :
	 * Annulé, En attente, Confirmé, Terminé
	 * */
	@NotNull
	@Column(name = "status")
	private String status ;
	
	@Column(name = "description")
	private String description ;
}

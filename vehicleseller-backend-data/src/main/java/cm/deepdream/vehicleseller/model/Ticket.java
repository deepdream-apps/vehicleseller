package cm.deepdream.vehicleseller.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id ;
	
	@ManyToOne
	@JoinColumn (name = "id_race")
	private Race race ;
	
	@NotNull
	@Column(name = "category")
	private String category ;
	
	@NotNull
	@Column(name = "description")
	private String description ;
	
}

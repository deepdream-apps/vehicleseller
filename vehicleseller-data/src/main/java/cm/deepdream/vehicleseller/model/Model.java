package cm.deepdream.vehicleseller.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Model {
	@Id
	@Column(name = "id")
	private Long id ;

	@Column(name = "label")
	private String label ;
	
	@ManyToOne
	@JoinColumn (name = "id_brand")
	private Brand brand ;
	
	@Column(name = "description")
	private String description ;
}

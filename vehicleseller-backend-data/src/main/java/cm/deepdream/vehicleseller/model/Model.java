package cm.deepdream.vehicleseller.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
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
	
	@NotNull(message = "Label should not be null")
	@Column(name = "label")
	private String label ;
	
	@NotNull(message = "Brand label should not be null")
	@Column (name = "label_brand")
	private String labelBrand ;

}

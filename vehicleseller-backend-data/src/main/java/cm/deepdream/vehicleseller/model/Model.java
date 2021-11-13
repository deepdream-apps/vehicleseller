package cm.deepdream.vehicleseller.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
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
public class Model {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id ;
	
	@NotBlank
	@NotNull
	@Column(name = "label")
	private String label ;
	
	@NotBlank
	@NotNull
	@Column (name = "label_brand")
	private String labelBrand ;

}

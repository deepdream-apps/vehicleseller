package cm.deepdream.vehicleseller.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
	@Id
	@Column(name = "id")
	private Long id ;
	
	@Column(name = "code")
	private String code ;
	
	@Column(name = "label")
	private String label ;
	
	@Column(name = "phone_code")
	private String phoneCode ;
}

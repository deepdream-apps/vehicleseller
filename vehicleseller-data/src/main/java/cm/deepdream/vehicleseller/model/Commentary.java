package cm.deepdream.vehicleseller.model;
import java.time.LocalDateTime;
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
public class Commentary {
	@Id
	@Column(name = "id")
	private Long id ;
	
	@ManyToOne
	@JoinColumn (name = "id_vehicle")
	private Vehicle vehicle ;
	
	@ManyToOne
	@JoinColumn (name = "id_user")
	private User user ;
	
	@Column (name = "commentary_date")
	@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime commentaryDate ;
	
	@Column(name = "text")
	private String text ;
}

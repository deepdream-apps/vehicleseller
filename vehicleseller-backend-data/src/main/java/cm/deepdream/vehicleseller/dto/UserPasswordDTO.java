package cm.deepdream.vehicleseller.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPasswordDTO {
	private Long userId ;
	
	private String emailAddress ;
	
	private char[] password ;
}

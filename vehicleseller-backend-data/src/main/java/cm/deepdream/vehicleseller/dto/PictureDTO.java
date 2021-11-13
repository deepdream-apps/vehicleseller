package cm.deepdream.vehicleseller.dto;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PictureDTO {
	@Column(name = "filename")
	private String fileName ;
	
	@Column(name = "path")
	private String path ;
	
	@Column(name = "sub_path")
	private String subPath ;
	
	@Column(name = "content_type")
	private String contentType ;
	
	@Transient
	private byte[] bytesStr ;

}

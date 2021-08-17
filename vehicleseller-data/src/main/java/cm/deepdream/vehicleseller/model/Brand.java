package cm.deepdream.vehicleseller.model;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
	@Id
	@Column(name = "id")
	private Long id ;
	
	@Embedded
	@AttributeOverrides({
		 @AttributeOverride( name = "path", column = @Column(name = "picture_path")),
		 @AttributeOverride( name = "subPath", column = @Column(name = "picture_subpath")),
		 @AttributeOverride( name = "fileName", column = @Column(name = "picture_filename")),
		 @AttributeOverride( name = "contentType", column = @Column(name = "picture_content_type"))
	})
	private Picture picture ;
	
	@Column(name = "label")
	private String label ;
	
	@Column(name = "description")
	private String description ;

}

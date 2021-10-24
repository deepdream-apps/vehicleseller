package cm.deepdream.vehicleseller.model;

import java.time.LocalDate;

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
public class MaintenanceOperation {
	@Id
	@Column(name = "id")
	private Long id ;
	
	@Column(name = "company")
	private String company ;
	
	@Column(name = "maintenance_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate maintenanceDate ;
	
	@ManyToOne
	@JoinColumn(name = "id_breakdown")
	private Breakdown breakdown ;
	
	@Column(name = "status")
	private String status ;
	
	@Column(name = "observations")
	private String observations ;
}
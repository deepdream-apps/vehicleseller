package cm.deepdream.vehicleseller.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor ;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id ;
	
	@Column(name = "mode")
	private String mode ;
	
	@Column(name = "reference")
	private String reference ;
	
	@Column(name = "phone_number")
	private String phoneNumber ;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_bill")
	private Bill bill ;
	
	@Column(name = "payment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate paymentDate ;
	
	@Column(name = "amount_with_taxes")
	private BigDecimal amountWithTaxes ;
	
}

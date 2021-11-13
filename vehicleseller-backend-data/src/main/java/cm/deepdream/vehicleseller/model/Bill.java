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
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id ;
	
	@NotNull
	@Column(name = "reference", unique = true)
	private String reference ;
	
	@NotNull
	@Column(name = "label", unique = true)
	private String label ;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_reservation")
	private Reservation reservation ;
	
	@NotNull
	@Column(name = "bill_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate billDate ;
	
	@Positive
	@Column(name = "amount_without_taxes")
	private BigDecimal amountWithoutTaxes ;
	
	@PositiveOrZero
	@Column(name = "taxes_amount")
	private BigDecimal taxesAmount ;
	
	@Positive
	@Column(name = "amount_with_taxes")
	private BigDecimal amountWithTaxes ;
	
	@NotNull
	@Column(name = "status")
	private String status ;
}

package cm.deepdream.vehicleseller.dto;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillDTO {
	private Long id ;

	private String reference ;

	private String label ;

	private ReservationDTO reservation ;

	private LocalDate billDate ;

	private BigDecimal amountWithoutTaxes ;

	private BigDecimal taxesAmount ;

	private BigDecimal amountWithTaxes ;

	private String status ;
}

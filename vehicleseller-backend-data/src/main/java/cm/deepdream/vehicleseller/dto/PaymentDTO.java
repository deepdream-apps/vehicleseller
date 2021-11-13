package cm.deepdream.vehicleseller.dto;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor ;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
	private Long id ;

	private String mode ;

	private String reference ;

	private String phoneNumber ;

	private BillDTO bill ;

	private LocalDate paymentDate ;

	private BigDecimal amountWithTaxes ;
	
}

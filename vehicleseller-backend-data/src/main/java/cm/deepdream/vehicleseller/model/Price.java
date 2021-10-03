package cm.deepdream.vehicleseller.model;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Price {
	@Column(name = "amount")
	private BigDecimal amount ;
	
	@Column (name = "currency")
	private String currency ;
	
	@Column (name = "currency_symbol")
	private String currencySymbol ;
}

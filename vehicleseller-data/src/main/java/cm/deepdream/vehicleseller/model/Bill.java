package cm.deepdream.vehicleseller.model;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
	@Id
	@Column(name = "id")
	private Long id ;
	
	@Column(name = "label")
	private String label ;
	
	@Column(name = "bill_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate billDate ;
	
	@Column(name = "amount_without_taxes")
	private BigDecimal amountWithoutTaxes ;
	
	@Column(name = "taxes_amount")
	private BigDecimal taxesAmount ;
	
	@Column(name = "amount_with_taxes")
	private BigDecimal amountWithTaxes ;
}

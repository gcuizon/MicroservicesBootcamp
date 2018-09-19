package microservices.training.camp.currencyexchangeservice;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="exchange_value")
@NamedQueries({
			   @NamedQuery(name="getExchangeValByFromTo", query="FROM ExchangeValue WHERE currency_from=:currency_from AND currency_to=:currency_to")
})
public class ExchangeValue {
	
	@Id 
	/* 'strategy=GenerationType.IDENTITY' is needed to auto generate the ID for you
	 * no need to include ID in insert statements	 */
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String currency_from;
	private String currency_to;
	private BigDecimal exchangeValue;
	private Integer port;
	
	public ExchangeValue(){
		
	}
	
	public ExchangeValue(Long id, String from, String to,
			BigDecimal exchangeValue) {
		super();
		this.id = id;
		this.currency_from = from;
		this.currency_to = to;
		this.exchangeValue = exchangeValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return currency_from;
	}

	public void setFrom(String from) {
		this.currency_from = from;
	}

	public String getTo() {
		return currency_to;
	}

	public void setTo(String to) {
		this.currency_to = to;
	}

	public BigDecimal getExchangeValue() {
		return exchangeValue;
	}

	public void setExchangeValue(BigDecimal exchangeValue) {
		this.exchangeValue = exchangeValue;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
}

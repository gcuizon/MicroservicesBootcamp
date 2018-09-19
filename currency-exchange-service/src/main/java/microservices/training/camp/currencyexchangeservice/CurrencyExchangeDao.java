package microservices.training.camp.currencyexchangeservice;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@EntityScan("microservices.training.camp.currencyexchangeservice")
public class CurrencyExchangeDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public ExchangeValue getExchangeValue(String from, String to){
		
		Query query = entityManager.createNamedQuery("getExchangeValByFromTo");
		query.setParameter("currency_from", from);
		query.setParameter("currency_to", to);
		
		return (ExchangeValue)query.getSingleResult();
	}

}

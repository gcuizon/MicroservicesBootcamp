package microservices.training.camp.limitsservice;

import microservices.training.camp.configuration.LimitsConfiguration;
import microservices.training.camp.limitsservice.bean.LimitConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	LimitsConfiguration configuration;
	
	@GetMapping(path="/limits")
	public LimitConfiguration getLimitsConfiguration(){
		return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
	}
}

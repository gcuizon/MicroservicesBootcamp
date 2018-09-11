package microservices.training.camp.rest.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EntityScan("microservices.training.camp.rest.bean")//important to expose the named queries
@EnableSwagger2
public class ApplicationConfigClass {
	public Docket getDockit(){
		return new Docket(DocumentationType.SWAGGER_2);
	}
}

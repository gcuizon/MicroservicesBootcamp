package microservices.training.camp.rest.userappl;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
@ComponentScan("microservices.training.camp.rest")
public class UserApplApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplApplication.class, args);
	}
		
	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		
		return localeResolver;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		
		return messageSource;
	}
	

}

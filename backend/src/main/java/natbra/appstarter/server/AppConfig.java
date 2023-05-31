package natbra.appstarter.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public Utils UtilsBeans() {
		return new Utils();
	}
}
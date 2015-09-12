package example;

import orest.config.EnableOpenRest;
import orest.projection.authorization.SecureAnnProjectionAuthorizationStrategy;
import orest.security.ExpressionEvaluator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import example.model.Client;

@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class })
@EnableOpenRest
public class Application {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private Environment env;

	@Bean
	public javax.validation.Validator localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}

	@Autowired
	public void configureRest(RepositoryRestConfiguration config) {
		config.setBaseUri("/api");
		config.exposeIdsFor(Client.class);
	}
	

}

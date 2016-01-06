package example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import pl.openrest.core.config.EnableOpenRest;
import pl.openrest.dto.config.EnableOpenRestDto;
import pl.openrest.dto.security.authorization.AuthorizeDtoAnnotationAuthorizationStrategy;
import pl.openrest.dto.security.config.EnableOpenRestDtoSecurity;
import pl.openrest.dto.validation.DtoFieldExpressionValidator;
import pl.openrest.dto.validation.config.EnableOpenRestDtoValidation;
import pl.openrest.filters.querydsl.config.EnableOpenRestQueryDslFilters;
import example.dto.security.BeanDtoAuthorizationStrategyFactory;
import example.model.Client;

@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class })
// @EnableOpenRest
@EnableOpenRest
@EnableOpenRestQueryDslFilters
@EnableOpenRestDto
@EnableOpenRestDtoSecurity
@EnableOpenRestDtoValidation
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private Environment env;

    // @Bean
    // public javax.validation.Validator localValidatorFactoryBean() {
    // return new LocalValidatorFactoryBean();
    // }

    @Autowired
    public void configureRest(RepositoryRestConfiguration config) {
        config.setBaseUri("/api");
        config.exposeIdsFor(Client.class);
    }

    @Bean
    public DtoFieldExpressionValidator dtoFieldExpressionValidator() {
        return new DtoFieldExpressionValidator();
    }

    @Bean
    public BeanDtoAuthorizationStrategyFactory strategyFactory() {
        return new BeanDtoAuthorizationStrategyFactory();
    }

    @Bean
    @Autowired
    public AuthorizeDtoAnnotationAuthorizationStrategy authorizeDtoAnnotationAuthorizationStrategy() {
        return new AuthorizeDtoAnnotationAuthorizationStrategy(strategyFactory());
    }

}

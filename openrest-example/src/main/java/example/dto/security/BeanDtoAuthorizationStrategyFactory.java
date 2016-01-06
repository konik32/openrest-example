package example.dto.security;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import pl.openrest.dto.security.authorization.DtoAuthorizationStrategy;
import pl.openrest.dto.security.authorization.DtoAuthorizationStrategyFactory;

public class BeanDtoAuthorizationStrategyFactory implements DtoAuthorizationStrategyFactory, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

    @Override
    public DtoAuthorizationStrategy getAuthorizationStrategy(Class<? extends DtoAuthorizationStrategy> type) {
        return applicationContext.getBean(type);
    }

}

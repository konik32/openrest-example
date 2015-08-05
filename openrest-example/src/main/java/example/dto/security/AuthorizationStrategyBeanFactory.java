package example.dto.security;

import orest.dto.authorization.DtoAuthorizationStrategy;
import orest.dto.authorization.DtoAuthorizationStratetyFactory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

//@Component
public class AuthorizationStrategyBeanFactory implements DtoAuthorizationStratetyFactory, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public DtoAuthorizationStrategy getAuthorizationStrategy(Class<? extends DtoAuthorizationStrategy> type) {
		return applicationContext.getBean(type);
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.applicationContext = context;

	}

}

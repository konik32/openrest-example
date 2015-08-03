package example.dto.security;

import orest.dto.authorization.AuthorizationStrategy;
import orest.dto.authorization.AuthorizationStratetyFactory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

//@Component
public class AuthorizationStrategyBeanFactory implements AuthorizationStratetyFactory, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public AuthorizationStrategy getAuthorizationStrategy(Class<? extends AuthorizationStrategy> type) {
		return applicationContext.getBean(type);
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.applicationContext = context;

	}

}

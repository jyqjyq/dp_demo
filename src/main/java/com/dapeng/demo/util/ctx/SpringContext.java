package com.dapeng.demo.util.ctx;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringContext implements ApplicationContextAware {

	private static ApplicationContext ctx; // Spring应用上下文环境

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContext.ctx = applicationContext;
	}

	public static ApplicationContext getSpringContext() {
		return ctx;
	}

	@SuppressWarnings("unchecked")
	public static <T> Optional<T> getBean(String name)  {
		try {
			return Optional.of((T)SpringContext.ctx.getBean(name));
		}catch(Exception e) {
			return Optional.empty();
		} 
	} 
	public static <T> Optional<T> getBean(Class<T> clz)  {
		try {
			return Optional.of((T)SpringContext.ctx.getBean(clz));
		}catch(Exception e) {
			return Optional.empty();
		}  
	}

}

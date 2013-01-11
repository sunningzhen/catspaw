package org.catspaw.cherubim.security.ss3;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.security.access.AccessDeniedException;

/**
 * 控制权限的AOP拦截器抛出AccessDeniedException后，进行相应后续处理
 * @author 孙宁振
 */
public class AccessDeniedInterceptor implements ThrowsAdvice {

	private static final Logger	logger	= LoggerFactory
												.getLogger(AccessDeniedInterceptor.class);

	public void afterThrowing(Method method, Object[] args, Object target,
			AccessDeniedException exception) {
		logger.debug("access denied");
		//TODO 通过DatabaseMethodDefinitionSource拒绝访问后的处理
	}
}

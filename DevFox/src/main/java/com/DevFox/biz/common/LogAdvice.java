package com.DevFox.biz.common;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;


@Service
@Aspect	
public class LogAdvice {
	
	@Pointcut("execution(* com.DevFox.biz..*Impl.*(..))")
	public void allPointcut() {
		
	}
	
	@Pointcut("execution(* com.DevFox.biz..*Impl.get*(..))")
	public void getPointcut() {
		
	}
	
	@Before("allPointcut()")
	public void printLog(JoinPoint jp) {
	    System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작하는 메소드...");
	    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	    HttpSession session = request.getSession();
	    Enumeration<String> attributeNames = session.getAttributeNames();
	    while (attributeNames.hasMoreElements()) {
	        String attributeName = attributeNames.nextElement();
	        Object attributeValue = session.getAttribute(attributeName);
	        System.out.println("Session attribute: " + attributeName + " = " + attributeValue);
	    }
	}
}

package com.globalin.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdvice {
	
	/*
	@Pointcut("execution(* com.globalin.biz..*Impl.*(..))")
	public void allPointcut() {
		
	}
	
	@Before("allPointcut()")
	*/
	
	@Before("PointcutCommon.allPointcut()")
	public void beforeLog(JoinPoint jp) {
		
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();	// 인자의 목록을 가져옴
		
	
		
	//	System.out.println("[사전 처리] "+method+ "() 메소드 ARGS정보 : "+ args[0].toString());
		
	}
	
}

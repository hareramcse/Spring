package com.hs6;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SecruityService {

	@Pointcut(value = "execution(* com.hs6.AccountService.my*(..))")
	public void test1() {

	}

	@Pointcut(value = "execution(* com.hs6.CustomerService.up*(..))")
	public void test2() {

	}

	@Around("test1() or test2()")
	public void verifyUser(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("verifyUser..begin");
		pjp.proceed();
		System.out.println("verifyUser End...");
	}
}

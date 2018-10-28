package com.hs6;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogService {

	@Pointcut(value = "execution(* com.hs6.AccountService.my*(..))")
	public void test1() {

	}

	@Pointcut(value = "execution(* com.hs6.CustomerService.up*(..))")
	public void test2() {

	}

	public void log(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("LogService...Log()..begin");
		logBefore();
		pjp.proceed();
		logReturning();
		System.out.println("LogService..log()..end");
	}

	public void logBefore() {
		System.out.println("LS-logBefore");
	}

	public void logReturning() {
		System.out.println("LS-logReturning");
	}

	@AfterThrowing("test1() or test2()")
	public void logThrowing(Exception e) {
		System.out.println("LS-logThrowing");
		System.out.println(e);
	}

	@After("test1() or test2()")
	public void logOk() {
		System.out.println("LS...logOk()..");
	}
}
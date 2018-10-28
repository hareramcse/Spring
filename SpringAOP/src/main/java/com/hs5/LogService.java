package com.hs5;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogService {

	@Pointcut(value = "execution(* com.hs5.AccountService.my*(..))")
	public void test1() {

	}

	@Pointcut(value = "execution(* com.hs5.CustomerService.up*(..))")
	public void test2() {

	}

	@Before("test1() or test2()")
	public void logBefore() {
		System.out.println("Log Service-logBefore");
	}

	@AfterReturning("test1() or test2()")
	public void logReturning() {
		System.out.println("Log Service-logReturning");
	}

	@AfterThrowing("test1() or test2()")
	public void logThrowing(Exception e) {
		System.out.println("Log Service-logThrowing");
		System.out.println(e);
	}

	@After("test1() or test2()")
	public void logOk() {
		System.out.println("Log Service...logOk()..");
	}
}
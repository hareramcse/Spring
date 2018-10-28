package com.hs6;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TxService {

	@Pointcut(value = "execution(* com.hs6.AccountService.my*(..))")
	public void test1() {

	}

	@Pointcut(value = "execution(* com.hs6.CustomerService.up*(..))")
	public void test2() {

	}

	@Around("test1() or test2()")
	public void runTx(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("txService..runTx()..begin");
		begin();
		pjp.proceed();
		commit();
		System.out.println("txService...runTx()..end");
	}

	public void begin() {
		System.out.println("TS-begin");
	}

	public void commit() {
		System.out.println("TS-commit");
	}

	@AfterThrowing("test1() or test2()")
	public void rollback() {
		System.out.println("TS-rollabck");
	}
}
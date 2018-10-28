package com.hs5;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TxService {

	@Pointcut(value = "execution(* com.hs5.AccountService.my*(..))")
	public void test1() {

	}

	@Pointcut(value = "execution(* com.hs5.CustomerService.up*(..))")
	public void test2() {

	}

	@Before("test1() or test2()")
	public void begin() {
		System.out.println("Transaction Service-begin");
	}

	@AfterReturning("test1() or test2()")
	public void commit() {
		System.out.println("Transaction Service-commit");
	}

	@AfterThrowing("test1() or test2()")
	public void rollback() {
		System.out.println("Transaction Service-rollabck");
	}
}

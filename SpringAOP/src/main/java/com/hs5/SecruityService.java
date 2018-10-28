package com.hs5;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SecruityService {

	@Pointcut(value = "execution(* com.hs5.AccountService.my*(..))")
	public void test1() {

	}

	@Pointcut(value = "execution(* com.hs5.CustomerService.up*(..))")
	public void test2() {

	}

	@Before("test1() or test2()")
	public void verifyUser() {
		System.out.println("Security Service-verifyUser");
	}
}

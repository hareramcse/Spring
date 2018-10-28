package com.hs8;

import org.aspectj.lang.ProceedingJoinPoint;

public class SecruityService {

	public void verifyUser(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("verifyUser begin...");
		pjp.proceed();
		System.out.println("verifyUser End...");
	}
}

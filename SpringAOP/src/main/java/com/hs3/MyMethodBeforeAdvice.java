package com.hs3;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;

public class MyMethodBeforeAdvice implements MethodBeforeAdvice {
	@Autowired
	LogService ls = null;

	@Autowired
	SecruityService ss = null;

	@Autowired
	TxService ts = null;

	public void before(Method method, Object[] args, Object target) throws Throwable {
		ss.veryUser();
		ls.logBefore();
		ts.begin();
	}

}

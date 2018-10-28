package com.hs2;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;

public class MyMethodInterceptorAdvice implements MethodInterceptor {
	@Autowired
	LogService ls = null;

	@Autowired
	SecruityService ss = null;

	@Autowired
	TxService ts = null;

	public Object invoke(MethodInvocation mi) throws Throwable {
		ss.veryUser();
		ls.logBefore();
		ts.begin();
		Object obj = mi.proceed();
		ts.commit();
		ls.logReturning();
		return obj;
	}

}

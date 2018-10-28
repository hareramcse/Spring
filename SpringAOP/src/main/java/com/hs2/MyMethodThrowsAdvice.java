package com.hs2;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;

public class MyMethodThrowsAdvice implements ThrowsAdvice {

	@Autowired
	LogService ls = null;

	@Autowired
	TxService ts = null;

	public void afterThrowing(Exception e) {
		ts.rollback();
		ls.logThrowing(e);
	}
}

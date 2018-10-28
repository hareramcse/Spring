package com.hs3;

public class LogService {
	public void logBefore() {
		System.out.println("Log Service-logBefore");
	}

	public void logReturning() {
		System.out.println("Log Service-logReturning");
	}

	public void logThrowing(Exception e) {
		System.out.println("Log Service-logThrowing");
		System.out.println(e);
	}
}

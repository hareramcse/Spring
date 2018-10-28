package com.hs1;

public class TxService {
	public void begin() {
		System.out.println("Transaction Service-begin");
	}

	public void commit() {
		System.out.println("Transaction Service-commit");
	}

	public void rollback() {
		System.out.println("Transaction Service-rollabck");
	}
}

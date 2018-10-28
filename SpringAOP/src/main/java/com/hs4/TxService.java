package com.hs4;

public class TxService {
	public void begin(){
		System.out.println("TS-begin");
	}
	public void commit(){
		System.out.println("TS-commit");
	}
	public void rollback(){
		System.out.println("TS-rollabck");
	}
}

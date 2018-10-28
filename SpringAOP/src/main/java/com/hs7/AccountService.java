package com.hs7;

public class AccountService {
	public void getBal() {
		System.out.println("getBal-called");
	}

	public void myDeposit() {
		System.out.println("deposit-called");
	}

	public void myWithDraw() throws Exception {
		System.out.println("withdraw-called");
	}
}

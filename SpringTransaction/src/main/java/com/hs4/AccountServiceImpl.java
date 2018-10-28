package com.hs4;

import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements AccountService {
	@Autowired
	AccoutnDAO accountdao = null;

	public void deposit(int accno, double amt) {
		accountdao.deposit(accno, amt);
	}

	public void withdraw(int accno, double amt) {
		accountdao.withdraw(accno, amt);
	}

	public void fundsTransfer(int saccno, int daccno, double amt) {
		accountdao.fundsTransfer(saccno, daccno, amt);
	}

	public double getBalance(int accno) {
		return accountdao.getBalance(accno);
	}

}

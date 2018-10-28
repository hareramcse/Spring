package com.hs1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Lab31 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext1.xml");
		AccountService as = (AccountService) ctx.getBean("as");
		System.out.println(as.getBalance(10001));
		System.out.println(as.getBalance(10002));

		// deposit
		as.deposit(10001, 2000.00);

		// 2. withdraw
		as.withdraw(10001, 3000.00);

		// 3. getbalance

		System.out.println(as.getBalance(10001));
		System.out.println(as.getBalance(10002));

		// 4. fundstransfer System.out.println(as.getBalance(103));
		System.out.println(as.getBalance(10001));
		as.fundsTransfer(10002, 10001, 3000.00);
		System.out.println(as.getBalance(10001));
		System.out.println(as.getBalance(10002));

	}
}

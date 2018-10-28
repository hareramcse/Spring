package com.hs5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Lab19 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext5.xml");
		System.out.println("..Using target Object..");

		CustomerService cs1 = ctx.getBean("cs", CustomerService.class);
		cs1.addCustomer();
		System.out.println("............");
		cs1.updateCustomer();
		System.out.println("............");

		AccountServiceImpl as1 = ctx.getBean("as", AccountServiceImpl.class);
		as1.myDeposit();
		System.out.println("............");
		as1.getBal();
		System.out.println("............");
		try {
			as1.myWithDraw();
		} catch (Exception e) {
			System.out.println("sorry....");
		}
	}
}

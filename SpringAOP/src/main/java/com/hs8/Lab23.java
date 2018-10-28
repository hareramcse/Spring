package com.hs8;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Lab23 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext8.xml");
		System.out.println("..Using target Object..");

		CustomerService cs = ctx.getBean("cs", CustomerService.class);
		cs.addCustomer();
		System.out.println("............");
		cs.updateCustomer();
		System.out.println("............");

		AccountService as = ctx.getBean("as", AccountService.class);
		as.myDeposit();
		System.out.println("............");
		as.getBal();
		System.out.println("............");
		try {
			as.myWithDraw();
		} catch (Exception e) {
			System.out.println("sorry....");
		}

	}
}

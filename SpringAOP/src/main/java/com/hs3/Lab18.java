package com.hs3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Lab18 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext3.xml");
		System.out.println("..Using target Object..");

		CustomerService cs = ctx.getBean("csTarget", CustomerService.class);
		cs.addCustomer();
		cs.updateCustomer();

		AccountService as = ctx.getBean("asTarget", AccountService.class);
		as.myDeposit();
		try {
			as.myWithDraw();
		} catch (Exception e) {
			System.out.println("sorry....");
		}
		System.out.println(".............");

		System.out.println("..using proxy object...");

		CustomerService cs1 = ctx.getBean("csProxy", CustomerService.class);
		cs1.addCustomer();
		cs1.updateCustomer();

		AccountService as1 = ctx.getBean("asProxy", AccountService.class);
		as1.myDeposit();
		as1.getBal();
		try {
			as1.myWithDraw();
		} catch (Exception e) {
			System.out.println("sorry......");
		}
	}
}

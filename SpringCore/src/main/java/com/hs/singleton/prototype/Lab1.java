package com.hs.singleton.prototype;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Lab1 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		RequestProcessor rp1 = ctx.getBean("requestProcessor", RequestProcessor.class);
		RequestValidator rv1= rp1.getValidator();
		System.out.println(rv1);
		RequestProcessor rp2 = ctx.getBean("requestProcessor", RequestProcessor.class);
		RequestValidator rv2= rp2.getValidator();
		System.out.println(rv2);
	}
}

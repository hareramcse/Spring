package com.hs.lookup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LookupExample {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("lookup.xml");
		PizzaShop pizzaShop = (PizzaShop) context.getBean("pizzaShop");
		Pizza firstPizza = pizzaShop.makePizza();
		System.out.println("First Pizza: " + firstPizza);

		Pizza secondPizza = pizzaShop.makePizza();
		System.out.println("Second Pizza: " + secondPizza);
		
		Pizza thirdPizza = pizzaShop.makePizza();
		System.out.println("Second Pizza: " + thirdPizza);
	}
}
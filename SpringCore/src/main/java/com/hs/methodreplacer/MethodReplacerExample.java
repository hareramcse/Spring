package com.hs.methodreplacer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

public class MethodReplacerExample {

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("replacement.xml");

		ReplacementTarget standardTarget = (ReplacementTarget) factory.getBean("standardTarget");
		ReplacementTarget replacementTarget = (ReplacementTarget) factory.getBean("replacementTarget");

		displayInfo(standardTarget);
		displayInfo(replacementTarget);

	}

	private static void displayInfo(ReplacementTarget target) {
		System.out.println(target.formatMessage("Hello world"));

		StopWatch stopWatch = new StopWatch();
		stopWatch.start("perfTest");

		for (int x = 0; x < 1000000; x++) {
			String out = target.formatMessage("foo");
		}

		stopWatch.stop();

		System.out.println("1000000 invocations took: " + stopWatch.getTotalTimeMillis() + " ms");
	}
}
package com.hs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootApp {
	private static Logger logger = LoggerFactory.getLogger(SpringBootApp.class);

	public static void main(String[] args) {
		logger.info("starting main method");
		SpringApplication.run(SpringBootApp.class, args);
		logger.info("execution completed");
	}
}

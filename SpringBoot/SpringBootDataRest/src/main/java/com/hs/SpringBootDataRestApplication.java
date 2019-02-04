package com.hs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hs.dao.StudentDataRestRepository;

@SpringBootApplication
public class SpringBootDataRestApplication {

	@Autowired
	StudentDataRestRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataRestApplication.class, args);
	}
}

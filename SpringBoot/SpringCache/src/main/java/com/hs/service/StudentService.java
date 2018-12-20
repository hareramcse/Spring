package com.hs.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hs.domain.Student;

@Service
public class StudentService {
	
	@Cacheable("student")
	public Student getStudentByID(String id) {
		try {
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Student(id, "Sajal", "V");

	}
}

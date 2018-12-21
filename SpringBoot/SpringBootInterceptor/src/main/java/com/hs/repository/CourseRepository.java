package com.hs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hs.model.Course;

public interface CourseRepository extends CrudRepository<Course, String> {

	public List<Course> findByTopicId(String id);
}

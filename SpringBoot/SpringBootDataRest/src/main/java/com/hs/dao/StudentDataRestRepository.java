package com.hs.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hs.model.Student;

@RepositoryRestResource(path = "students", collectionResourceRel = "students")
public interface StudentDataRestRepository extends PagingAndSortingRepository<Student, Long>{

}

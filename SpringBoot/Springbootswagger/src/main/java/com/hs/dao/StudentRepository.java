package com.hs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hs.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}

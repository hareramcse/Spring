package com.hs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hs.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
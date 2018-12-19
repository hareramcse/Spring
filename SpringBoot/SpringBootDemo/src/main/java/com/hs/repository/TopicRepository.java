package com.hs.repository;

import org.springframework.data.repository.CrudRepository;

import com.hs.model.Topic;

public interface TopicRepository extends CrudRepository<Topic, String>{

}

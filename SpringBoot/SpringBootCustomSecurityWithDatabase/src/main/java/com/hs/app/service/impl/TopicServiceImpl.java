package com.hs.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.app.dao.TopicDAO;
import com.hs.app.entities.Topic;
import com.hs.app.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicDAO topicDAO;

	@Override
	public Topic getTopicById(int topicId) {
		return topicDAO.getTopicById(topicId);
	}

	@Override
	public List<Topic> getAllTopics() {
		return topicDAO.getAllTopics();
	}

	@Override
	public boolean addTopic(Topic topic) {
		if (topicDAO.topicExists(topic.getTitle(), topic.getCategory())) {
			return false;
		} else {
			topicDAO.addTopic(topic);
			return true;
		}
	}

	@Override
	public void updateTopic(Topic topic) {
		topicDAO.updateTopic(topic);
	}

	@Override
	public void deleteTopic(int topicId) {
		topicDAO.deleteTopic(topicId);
	}
}

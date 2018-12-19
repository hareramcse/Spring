package com.hs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hs.model.Topic;
import com.hs.service.TopicService;

@RestController
public class TopicController {

	@Autowired
	TopicService topicService;

	@RequestMapping("/topic")
	public List<Topic> getAllTopic() {
		return topicService.getAllTopic();
	}

	@RequestMapping("/topic/{id}")
	public Optional<Topic> getTopicById(@PathVariable String id) {
		return topicService.getTopicById(id);
	}

	@RequestMapping(value = "/topic", method = RequestMethod.POST)
	public void addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
	}

	@RequestMapping(value = "/topic/{id}", method = RequestMethod.PUT)
	public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		topicService.updateTopic(topic, id);
	}
	
	@RequestMapping(value = "/topic/{id}", method = RequestMethod.DELETE)
	public void deleteTopic(@PathVariable String id) {
		topicService.deleteTopic(id);
	}
}
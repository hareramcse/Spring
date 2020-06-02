package com.hs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hs.model.User;
import com.hs.service.UserService;

@RestController
@RequestMapping(value = "/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String getWelcomeMessage() {
		return "Welcome to Spring Boot Mongo DB Demo";
	}

	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) {
		return userService.getUserById(userId);
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		return userService.addNewUser(user);
	}

	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String userId) {
		return userService.getAllUserSettings(userId);
	}

	@RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
	public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
		return userService.getUserSetting(userId, key);
	}

	@RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
	public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
		return userService.addUserSetting(userId, key, value);
	}
}
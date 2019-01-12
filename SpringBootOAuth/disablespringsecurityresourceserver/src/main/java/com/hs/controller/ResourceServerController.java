package com.hs.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;

public class ResourceServerController {
	
	@RequestMapping(value = "/api")
	@PreAuthorize("hasRole('SYSTEMADMIN')")
	public String success() {
		return "SUCCESS";
	}

	@RequestMapping(value = "/customers/api")
	public String manageAPI1() {
		return "SUCCESS";
	}

	@RequestMapping(value = "/customers/version")
	public String manageAPI2() {
		return "SUCCESS";
	}

	@RequestMapping(value = "/users/manage")
	public String usersmanage() {
		return "SUCCESS";
	}
}

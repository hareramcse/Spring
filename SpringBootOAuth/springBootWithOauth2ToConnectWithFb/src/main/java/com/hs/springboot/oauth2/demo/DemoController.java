package com.hs.springboot.oauth2.demo;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}

}

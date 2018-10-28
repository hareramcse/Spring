package com.ratelimiter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratelimiter.annotation.RateLimit;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author vthati
 *
 */
@RestController
public class HelloController {

	@RateLimit(limit = 2,duration=1,unit=TimeUnit.SECONDS)
	@RequestMapping("/hello")
	public String hello(HttpServletRequest request) {
		return "hello: " + request.getRemoteAddr() + " | " + request.getRemoteHost();
	}

	// @RateLimit(limit = 3)
	@RequestMapping("/hello2")
	public String hello2() {
		return "hello2";
	}
}

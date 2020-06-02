package com.ratelimiter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratelimiter.annotation.RateLimit;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
public class HelloController {
	
	private Cache<String, Integer> cache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build();

	@RateLimit(limit = 2, duration = 1, unit = TimeUnit.SECONDS)
	@RequestMapping("/hello")
	public String hello(HttpServletRequest request) {
		return "hello: " + request.getRemoteAddr() + " | " + request.getRemoteHost();
	}

	// @RateLimit(limit = 3)
	@RequestMapping("/hello2")
	public String hello2() {
		return "hello2";
	}
	
	// use this method to add the ratelimiter thing in any controller
	private synchronized boolean checkReq(HttpServeltRequest req, HttpServeltResponse res, String appId, String userId){
		StringBuilder builder=new StringBuilder();
		
		if(null != req.getRemoteAddr()){
			builder.append();
		}
		if(null != appId){
			builder.append(appId);
		}
		if(null != userid){
			builder.append(userId);
		}
		
		String key = builder.toString();
		
		if(null != key){
			if(null == cache.getIfPresent(key)){
				cache.put(key, 1);
				return true;
			}
			if(null != chache.getIfPersent(key) && chache.getIfPersent(key) < rateRequests){
				cache.put(key, cache.getIfPersent(key)+1);
				return true;
			}
			if(null != chache.getIfPersent(key) && chache.getIfPersent(key) >= rateRequests){
				return false;
			}
		}
		return false;
	}
}

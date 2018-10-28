package com.ratelimiter.aspect;

import java.util.Date;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;
import com.ratelimiter.annotation.RateLimit;
import com.ratelimiter.exception.RateLimitException;

/**
 * 
 * @author vthati
 *
 */
@Component
@Aspect
public class RateLimitAspect {

	private final ConcurrentHashMap<String, RateLimiter> limiters = new ConcurrentHashMap<String, RateLimiter>();
	private final ConcurrentHashMap<String, RateLimitModel> tempLimiters = new ConcurrentHashMap<String, RateLimitModel>();

	private final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

	private static final Logger logger = LoggerFactory.getLogger(RateLimitAspect.class);

	@Pointcut("@annotation(rateLimit)")
	private void annotatedWithRateLimit(RateLimit rateLimit) {
	}

	@Pointcut("@within(org.springframework.stereotype.Controller)"
			+ " || @within(org.springframework.web.bind.annotation.RestController)")
	private void controllerMethods() {
	}

	private HttpServletRequest getRequest(Object[] args) {
		for (Object arg : args) {
			if (arg instanceof HttpServletRequest) {
				return (HttpServletRequest) arg;
			}
		}
		return null;
	}

	@Before("controllerMethods() && annotatedWithRateLimit(rateLimit)")
	public void rateLimitProcess(final JoinPoint joinPoint, RateLimit rateLimit) throws RateLimitException {
		logger.debug("start rateLimitProcess()");
		HttpServletRequest request = getRequest(joinPoint.getArgs());
		if (request == null) {
			logger.error(String.format("getting request is null", joinPoint.getSignature().toShortString()));
			return;
		}
		String ip = request.getRemoteHost();
		String url = request.getRequestURI();
		String key = String.format(ip);

		if (tempLimiters.containsKey(key) && tempLimiters.get(key).getCount() >= rateLimit.limit()) {
			System.out.println("Too Many requested");

			expire(key);
			throw new RateLimitException();
		}
		if (tempLimiters.containsKey(key)) {
			if (expire(key)) {
				System.out.println("Granted1");
				tempLimiters.put(key, new RateLimitModel(1, sdfTime.format(new Date()), caluculateLiftTime(rateLimit)));
			}

			else {
				tempLimiters.get(key).setCount(tempLimiters.get(key).getCount() + 1);
				System.out.println("Granted2");
			}
		} else {

			System.out.println("Granted4");
			tempLimiters.put(key, new RateLimitModel(1, sdfTime.format(new Date()), caluculateLiftTime(rateLimit)));
		}

	}

	private boolean expire(String key) {

		long l = 0;
		try {
			l = sdfTime.parse(sdfTime.format(new Date())).getTime()
					- sdfTime.parse(tempLimiters.get(key).getCurrentMilliSec()).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (l > tempLimiters.get(key).getLifeTime()) {
			tempLimiters.remove(key);
			return true;
		}
		return false;
	}

	private double caluculateLiftTime(RateLimit ratelimit) {

		switch (ratelimit.unit()) {
		case SECONDS:
			return ratelimit.duration() * 1000;
		case MINUTES:
			return ratelimit.duration() * 60000;
		case HOURS:
			break;
		default:
			break;
		}
		return 0;
	}
}

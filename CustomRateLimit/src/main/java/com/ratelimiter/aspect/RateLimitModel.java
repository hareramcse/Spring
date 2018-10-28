package com.ratelimiter.aspect;

import java.util.Date;

public class RateLimitModel {

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCurrentMilliSec() {
		return currentMilliSec;
	}

	public void setCurrentMilliSec(String currentMilliSec) {
		this.currentMilliSec = currentMilliSec;
	}

	public double getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(double lifeTime) {
		this.lifeTime = lifeTime;
	}

	private int count;
	private String currentMilliSec;
	private double lifeTime;

	public RateLimitModel(int count, String currentMilliSec, double lifeTime) {

		this.count = count;
		this.currentMilliSec = currentMilliSec;
		this.lifeTime = lifeTime;

	}

}

package com.hs.lookup;

import java.util.concurrent.atomic.AtomicLong;

public class Pizza {
	private static AtomicLong count = new AtomicLong(0);

	public Pizza() {
		count.incrementAndGet();
	}

	public String toString() {
		return "A new " + "Pizza, count(" + count.get() + ")";
	}
}

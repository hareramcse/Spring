package com.hs;

import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {

	private static Queue<String> queue = new LinkedList<String>();
	private String url;

	public SharedQueue(String url) {
		this.url = url;
	}

	public synchronized static void add(String url) {
		queue.add(url);
	}

	// Sync Cause it remove the item
	public synchronized static String poll() {
		return queue.poll();
	}

	public synchronized static boolean isEmpty() {
		if (queue.isEmpty()) {
			return true;
		}
		return false;
	}

	public synchronized static boolean contains(String Url) {
		if (queue.contains(Url)) {
			return true;
		}
		return false;
	}

	public synchronized static int size() {
		int size = queue.size();
		return size;
	}

	public String getUrl() {
		return url;
	}
}

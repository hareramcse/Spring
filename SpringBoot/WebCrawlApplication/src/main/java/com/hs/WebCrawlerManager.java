package com.hs;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebCrawlerManager {
	Logger logger = LoggerFactory.getLogger(WebCrawlerManager.class);

	private Set<String> visited = new HashSet<String>();
	private static final int MAX_PAGES_NUM = 50;
	private static final int TIME_OUT = 60;
	private static final int MAX_QUEUE_SIZE = 2000;
	private ExecutorService exe;
	public static final Object lock = new Object();

	public void processPage(String rootLink) {
		printMessage();
		SharedQueue.add(rootLink);

		exe = Executors.newCachedThreadPool();

		while (true) {
			if (!SharedQueue.isEmpty()) {

				if (SharedQueue.size() > MAX_QUEUE_SIZE) {
					logger.info("Reaches the Max Queue Size " + MAX_QUEUE_SIZE);
					break;
				}
				if (visited.size() > MAX_PAGES_NUM) {
					logger.info("Reaches the Max Page Number " + MAX_PAGES_NUM);
					break;
				}
				String nextUrl = SharedQueue.poll();
				WebCrawler webCrawler = new WebCrawler(nextUrl);
				visited.add(nextUrl);
				exe.execute(webCrawler);
			} else {
				waitForNextURL();
			}
		}

		exe.shutdown();
		termination();

		for (String s : visited) {
			logger.info("Visited : " + s);

		}
		logger.info("Total pending pages in the queue : " + SharedQueue.size());
	}

	public void printMessage() {
		logger.info("------------Working on Airtel Web Pages---------------------------");
		logger.info("--------------------Please Hold On---------------------------------");
		logger.info("--------------------We Are Working On -----------------------------");
		logger.info("-------------------- " + MAX_PAGES_NUM + " Pages--------------------------------------");
		logger.info("Please wait......");
	}

	public void waitForNextURL() {
		try {
			synchronized (lock) {
				lock.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("There is no more pages");
		}
	}

	public void termination() {
		try {
			if (!exe.awaitTermination(TIME_OUT, TimeUnit.SECONDS)) {
				logger.error("Threads didn't finish in " + TIME_OUT + " Seconds!");
				exe.shutdownNow();
			}
			logger.info("Shutdown time " + TIME_OUT + " Seconds");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
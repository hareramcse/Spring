package com.hs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebCrawler implements Runnable {
	Logger logger = LoggerFactory.getLogger(WebCrawler.class);

	public Elements links;
	private String rootLink;

	public WebCrawler(String url) {
		this.rootLink = url;
	}

	@Override
	public void run() {
		webCrawl(rootLink);
	}

	public synchronized void webCrawl(String rootLink) {
		// initialize the queue with all the links
		Boolean connectionStatus = intializeSharedQueue(rootLink, 0);

		// If JSOUP is false that mean Error 404
		if (connectionStatus.equals(true)) {
			synchronized (WebCrawlerManager.lock) {
				// To wake-up all Thread on waiting list ....
				WebCrawlerManager.lock.notifyAll();
			}
		} else {
			// if Internet not working or Link syntax not correct will give this msg
			logger.info(
					"Please Check Your Internet Connection or The Syntax of Your URL..." + connectionStatus.toString());
		}
	}

	// This method to get the the links from rootlink and called again if the
	// queue is empty will save the links in links Elements form JSOUP
	public synchronized Boolean intializeSharedQueue(String url, int depth) {
		try {
			if (!SharedQueue.contains(url) && depth < Integer.MAX_VALUE) {
				// Get All the page content with HTML DOM
				Document doc = Jsoup.connect(url).get();
				// Get All the links
				links = doc.select("a[href]");
				for (Element link : links) {
					// Give me the links without DOM
					String nextLink = link.attr("abs:href");

					// if link not duplicated add to queue
					if (!(SharedQueue.contains(nextLink))) {
						// if link not null or ""
						if (!(nextLink == "")) {
							// Add to main queue
							SharedQueue.add(nextLink);
						}
					}
					depth++;
					intializeSharedQueue(nextLink, depth);
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
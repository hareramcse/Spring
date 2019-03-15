package com.hs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class WebCrawlerApplication {

	/*public static void main(String[] args) {
		SpringApplication.run(WebCrawlerApplication.class, args);
		List<Pages> pages = readJsonFile();
		WebCrawlerManager webCrawlerManager = new WebCrawlerManager();
		for (Pages p : pages) {
			webCrawlerManager.processPage(p.getAddress());
		}
	}*/
	
	public static void main(String[] args) {
		SpringApplication.run(WebCrawlerApplication.class, args);
		String link = "https://www.airtel.in";
		WebCrawlerManager webCrawlerManager = new WebCrawlerManager();
		webCrawlerManager.processPage(link);
	}

	private static List<Pages> readJsonFile() {
		File file;
		PagesModel p = null;
		try {
			file = ResourceUtils.getFile("classpath:internet_2.json");
			String content = new String(Files.readAllBytes(file.toPath()));
			ObjectMapper mapper = new ObjectMapper();
			p = mapper.readValue(content, PagesModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getPages();
	}
}

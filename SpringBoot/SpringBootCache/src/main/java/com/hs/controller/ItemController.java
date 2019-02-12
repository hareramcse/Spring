package com.hs.controller;

import com.hs.model.Item;
import com.hs.service.ItemCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {

	@Autowired
	ItemCache itemCache;

	@GetMapping("/item/{itemId}")
	public ResponseEntity<Item> getItem(@PathVariable int itemId) throws Exception {
		System.out.println("RestController..");
		Item item = itemCache.getItem(itemId);
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}

	@PutMapping("/updateItem")
	public ResponseEntity<Item> updateItem(@RequestBody Item item) {
		if (item != null) {
			itemCache.updateItem(item);
		}
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable int id) {
		itemCache.deleteItem(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}

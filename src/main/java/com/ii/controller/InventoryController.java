package com.ii.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ii.entities.InventoryItem;
import com.ii.service.InventoryService;

@RestController
public class InventoryController {
	
	private InventoryService service;
	
	@Autowired
	public InventoryController(InventoryService service) {
		this.service = service;
	}
	
	@PostMapping("/AddItem")
	public ResponseEntity<InventoryItem> AddItem(@RequestBody InventoryItem invItem) {
		return new ResponseEntity<InventoryItem>(this.service.AddItem(invItem), HttpStatus.CREATED);
	}
	
	@GetMapping("/FindItem/{itemId}")
	public ResponseEntity<InventoryItem> GetItemById(@PathVariable Long itemId) {
		return new ResponseEntity<InventoryItem>(this.service.GetItemById(itemId), HttpStatus.FOUND);
	}
	
	@GetMapping("/ShowAllItems")
	public ResponseEntity<List<InventoryItem>> ShowAllItems() {
		return new ResponseEntity<List<InventoryItem>>(this.service.ShowAllItems(), HttpStatus.FOUND);
	}
	
	@DeleteMapping("/DropItem/{itemId}")
	public ResponseEntity<Boolean> DropItemById(@PathVariable Long itemId) {
		boolean hasDeleted = this.service.DropItemById(itemId);
		
		if(hasDeleted) {
			return new ResponseEntity<Boolean>(hasDeleted, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Boolean>(hasDeleted, HttpStatus.FORBIDDEN);
		}
	}
	
	@DeleteMapping("DropAllItems")
	public ResponseEntity<Boolean> DropAllItems() {
		boolean hasDeleted = this.service.DropAllItems();
		
		if(hasDeleted) {
			return new ResponseEntity<Boolean>(hasDeleted, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Boolean>(hasDeleted, HttpStatus.FORBIDDEN);
		}
	}
	
	@PutMapping("/UpdateItem/{itemId}")
	public ResponseEntity<InventoryItem> UpdateItem(@PathVariable Long itemId, @RequestBody InventoryItem invItem) {
		return new ResponseEntity<InventoryItem>(this.service.UpdateItem(itemId, invItem), HttpStatus.ACCEPTED);
	}
}

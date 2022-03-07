package com.ii.controller;

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
		return new ResponseEntity<InventoryItem>(this.service.AddItem(invItem), HttpStatus.ACCEPTED);
	}
	
}

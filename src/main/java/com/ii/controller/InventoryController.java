package com.ii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.ii.service.InventoryService;

@RestController
public class InventoryController {
	
	private InventoryService service;
	
	@Autowired
	public InventoryController(InventoryService service) {
		this.service = service;
	}
	
}

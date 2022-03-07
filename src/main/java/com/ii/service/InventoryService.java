package com.ii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ii.repository.InventoryRepository;

@Service
public class InventoryService {

	private InventoryRepository repo;
	
	@Autowired
	public InventoryService(InventoryRepository repo) {
		this.repo = repo;
	}
	
}

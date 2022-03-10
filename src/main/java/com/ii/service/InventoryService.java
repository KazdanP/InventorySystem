package com.ii.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ii.entities.InventoryItem;
import com.ii.exceptions.ItemNotFoundException;
import com.ii.repository.InventoryRepository;

@Service
public class InventoryService {

	private InventoryRepository repo;
	
	@Autowired
	public InventoryService(InventoryRepository repo) {
		this.repo = repo;
	}
	
	public InventoryItem AddItem(InventoryItem invItem) {
		return this.repo.save(invItem);
	}
	
	public InventoryItem GetItemById(Long itemId) {
		return this.repo.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Can't find that Item"));
	}
	
	public List<InventoryItem> ShowAllItems() {
		return this.repo.findAll();
	}

	public boolean DropItemById(Long itemId) {
		this.repo.deleteById(itemId);
		return !this.repo.existsById(itemId);
	}
	
	public boolean DropAllItems() {
		this.repo.deleteAll();
		return (this.repo.count() == 0);
	}
	
	public boolean DropByName(String name) {
		this.repo.DeleteItemByName(name);
		return !this.repo.ItemExistsByName(name);
	}
}

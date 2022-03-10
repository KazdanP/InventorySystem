package com.ii.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.ii.entities.InventoryItem;
import com.ii.repository.InventoryRepository;

@SpringBootTest
public class InventoryServiceTest {

	@Autowired
	private InventoryService service;
	
	@MockBean
	private InventoryRepository repo;
	
	@Test
	void AddItemTest() {
		InventoryItem itemToAdd = new InventoryItem("Legendary Sword", "A sword", "Weapon", 2, true);
		InventoryItem addedItem = new InventoryItem(1L, "Legendary Sword", "A sword", "Weapon", 2, true);
		
		Mockito.when(this.repo.save(itemToAdd)).thenReturn(addedItem);
		
		assertThat(this.service.AddItem(itemToAdd)).isEqualTo(addedItem);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(Mockito.any(InventoryItem.class));
	}
	
}

package com.ii.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
	
	@Test
	void GetItemByIdTest() {
		Long id = 1L;
		InventoryItem itemFound = new InventoryItem(1L, "Legendary Sword", "A sword", "Weapon", 2, true);
		
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(itemFound));

		assertThat(this.service.GetItemById(id)).isEqualTo(itemFound);

		Mockito.verify(this.repo, Mockito.times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	void ShowAllItemsTest() {
		List<InventoryItem> addedItems = new ArrayList<>();
		addedItems.add(new InventoryItem(1L, "Legendary Sword", "A sword", "Weapon", 2, true));
		addedItems.add(new InventoryItem(2L, "Legendary Bow", "A bow", "Weapon", 1, true));
		addedItems.add(new InventoryItem(3L, "Wooden Sword", "A sword", "Weapon", 1, false));
		
		Mockito.when(this.repo.findAll()).thenReturn(addedItems);
		
		assertThat(this.service.ShowAllItems()).isEqualTo(addedItems);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void DeleteByIdTest() {
		Long id = 1L;
		
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		
		assertThat(this.service.DropItemById(id)).isTrue();
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(Mockito.anyLong());
	}
	
	@Test
	void DeleteAllTest() {
		Mockito.when((this.repo.count())).thenReturn(0L);
		
		assertThat(this.service.DropAllItems()).isTrue();
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteAll();
	}
}

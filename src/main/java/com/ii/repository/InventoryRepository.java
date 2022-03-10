package com.ii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ii.entities.InventoryItem;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long>{

	@Query(value="SELECT * FROM InventoryItem i WHERE i.itemName = ?1", nativeQuery = true)
	InventoryItem FindItemByName(String name);
	
	@Query(value="DELETE * FROM InventoryItem i WHERE i.itemName = ?1", nativeQuery = true)
	void DeleteItemByName(String name);
	
	@Query(value="SELECT * FROM InventoryItem i WHERE EXISTS AND i.itemName = ?1", nativeQuery = true)
	Boolean ItemExistsByName(String name);
}

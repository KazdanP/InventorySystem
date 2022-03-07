package com.ii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ii.entities.InventoryItem;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long>{

}

package com.ii.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InventoryItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long itemId;
	
	private String itemName;
	private String itemDesc;
	private String itemType;
	private int invSlots;
	private Boolean upgradable;
	
	public InventoryItem() {}
	
	public InventoryItem(Long itemId, String itemName, String itemDesc, String itemType, int invSlots, Boolean upgradable) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.itemType = itemType;
		this.invSlots = invSlots;
		this.upgradable = upgradable;
	}
	
	public InventoryItem(String itemName, String itemDesc, String itemType, int invSlots, Boolean upgradable) {
		super();
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.itemType = itemType;
		this.invSlots = invSlots;
		this.upgradable = upgradable;
	}

	public Long getItemId() { return itemId; }
	public void setItemId(Long itemId) { this.itemId = itemId; }

	public String getItemName() { return itemName; }
	public void setItemName(String itemName) { 	this.itemName = itemName; }

	public String getItemDesc() { return itemDesc; 	}
	public void setItemDesc(String itemDesc) { this.itemDesc = itemDesc; }

	public String getItemType() { return itemType; 	}
	public void setItemType(String itemType) { this.itemType = itemType; 	}

	public int getInvSlots() { return invSlots; }
	public void setInvSlots(int invSlots) { this.invSlots = invSlots; }

	public Boolean getUpgradable() { return upgradable; }
	public void setUpgradable(Boolean upgradable) { this.upgradable = upgradable; }

	@Override
	public String toString() {
		return String.format("%s: %s\n%s\nSlots: %s\nCan be upgraded: %s", itemType, itemName, itemDesc, invSlots, upgradable);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryItem other = (InventoryItem) obj;
		return invSlots == other.invSlots && Objects.equals(itemDesc, other.itemDesc)
				&& Objects.equals(itemId, other.itemId) && Objects.equals(itemName, other.itemName)
				&& Objects.equals(itemType, other.itemType) && Objects.equals(upgradable, other.upgradable);
	}
}

package com.ii.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ii.entities.InventoryItem;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:inventory-schema.sql", "classpath:inventory-data.sql"}, 
	 executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class InventoryControllerTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void AddNewItemTest() throws Exception {
		InventoryItem addedItem = new InventoryItem("Legendary Sword", "A sword", "Weapon", 2, true);
		String addedItemJSON = this.mapper.writeValueAsString(addedItem);
		
		InventoryItem expectedItem = new InventoryItem(4L, "Legendary Sword", "A sword", "Weapon", 2, true);
		String expectedItemJSON = this.mapper.writeValueAsString(expectedItem);
		
		RequestBuilder request = post("/AddItem").contentType(MediaType.APPLICATION_JSON).content(addedItemJSON);
		
		ResultMatcher responseStatus = status().isCreated();
		ResultMatcher responseContent = content().json(expectedItemJSON);

		this.mvc.perform(request).andExpectAll(responseStatus, responseContent);
	}
	
	@Test
	void FindByIdTest() throws Exception {
		InventoryItem addedItem = new InventoryItem(1L, "Legendary Sword", "A sword", "Weapon", 2, true);
		String addedItemJSON = this.mapper.writeValueAsString(addedItem);
		
		RequestBuilder req = get("/FindItem/1");
		
		ResultMatcher responseStatus = status().isFound();
		ResultMatcher responseContent = content().json(addedItemJSON);

		this.mvc.perform(req).andExpectAll(responseStatus, responseContent);
	}
	
	@Test
	void FindAllItemsTest() throws Exception {
		List<InventoryItem> addedItems = new ArrayList<>();
		addedItems.add(new InventoryItem(1L, "Legendary Sword", "A sword", "Weapon", 2, true));
		addedItems.add(new InventoryItem(2L, "Legendary Bow", "A bow", "Weapon", 1, true));
		addedItems.add(new InventoryItem(3L, "Wooden Sword", "A sword", "Weapon", 1, false));
		
		String addedItemsJSON = this.mapper.writeValueAsString(addedItems);
		
		RequestBuilder req = get("/ShowAllItems");
		
		ResultMatcher responseStatus = status().isFound();
		ResultMatcher responseContent = content().json(addedItemsJSON);

		this.mvc.perform(req).andExpectAll(responseStatus, responseContent);
	}
	
	@Test
	void DeleteByIdTest() throws Exception {
		this.mvc.perform(delete("/DropItem/1"))
		.andExpect(status().isAccepted());
	}
	
	@Test
	void DeleteAllTest() throws Exception {
		this.mvc.perform(delete("/DropAllItems"))
		.andExpect(status().isAccepted());
	}
}

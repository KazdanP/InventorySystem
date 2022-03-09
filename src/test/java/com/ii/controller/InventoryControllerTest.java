package com.ii.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ii.entities.InventoryItem;

@SpringBootTest
@ActiveProfiles("test")
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
		
		InventoryItem expectedItem = new InventoryItem(1L, "Legendary Sword", "A sword", "Weapon", 2, true);
		String expectedItemJSON = this.mapper.writeValueAsString(expectedItem);
		
		RequestBuilder request = post("/AddItem").contentType(MediaType.APPLICATION_JSON).content(addedItemJSON);
		
		ResultMatcher responseStatus = status().isCreated();
		ResultMatcher responseContent = content().json(expectedItemJSON);

		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}
	
	@Test
	void FindByIdTest() {
		
	}
	
	@Test
	void FindAllItemsTest() {
		
	}
}

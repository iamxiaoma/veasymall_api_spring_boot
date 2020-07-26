package com.veasymall.api.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGetUserList() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/user/list")).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	void testGetUser() {
		fail("Not yet implemented");
	}

	@Test
	void testGetQiniuResource() {
		fail("Not yet implemented");
	}

	@Test
	@Transactional
	void testSaveUser() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/user/saveUser")).andExpect(MockMvcResultMatchers.status().isOk());
	}

}

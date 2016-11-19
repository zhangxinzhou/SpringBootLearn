package com.example;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBoot01FirstprojectApplicationTests {

	private MockMvc mvc;
	
	@Test
	public void contextLoads() {
	}

	@Before
	public void setUp(){
		mvc=MockMvcBuilders.standaloneSetup(new MyBoot01FirstprojectApplication()).build();
	}
	
	@After
	public void getHello() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
		   .andExpect(status().isOk())
		   .andExpect(content().string(equalTo("hello")));
	}
	
}

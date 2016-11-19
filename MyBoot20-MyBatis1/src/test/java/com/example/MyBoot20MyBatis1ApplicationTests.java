package com.example;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.UserMapper;
import com.example.domain.Users;
import com.example.domain.XmlMapper;


//需要自己手动建表,注意
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBoot20MyBatis1ApplicationTests {

	private Logger log=Logger.getLogger(getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private XmlMapper xmlMapper;
	
	@Test
	public void contextLoads() {
		//userMapper.insert(1,"aaa",20);
		Users u=userMapper.findByName("aaa1");
		List<Users> ulist=userMapper.findAllByName("aaa");
		//参数写死
		Map<String, Object> params=new HashMap<>();
		params.put("name", "aaa");
		params.put("age", 20);
		List<Map<String, Object>> map=userMapper.getMap(params);
		//动态参数
		Map<String, Object> params2=new HashMap<>();
		params2.put("name", "aaa");
		//params2.put("age", 20);
		List<Map<String, Object>> map2=userMapper.getMap2(params2);
		log.info(u);
		log.info(ulist);
		log.info(map);
		log.info(map2);
	}
	
	@Test
	public void xmlTest(){
		List<Map<String, Object>> ulist=xmlMapper.getXmlUser("aaa", null);
		log.info(ulist);
	}

}

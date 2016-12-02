package com.example;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Test
	public void test(){
		String sql="select * from users";
		//List<Users> ul=jdbc.queryForList(sql,Users.class);
		List<Map<String, Object>> ml=jdbc.queryForList(sql);
		System.out.println(ml);
		Map<String, Object> map=null;
		if(ml!=null&&ml.size()>0){
			map=ml.get(0);
		}
		Set<String> keys = map.keySet();
		for(String key :keys){
		     System.out.println(key+" "+map.get(key));
		}
		System.out.println(map.keySet());
		System.out.println(map.values());
	}
}

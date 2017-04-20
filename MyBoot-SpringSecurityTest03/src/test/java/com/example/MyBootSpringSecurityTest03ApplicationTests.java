package com.example;

import java.util.List;
import java.util.Map;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.web.DevpController;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBootSpringSecurityTest03ApplicationTests {
	
	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private DevpController devpController;


	@Test
	public void contextLoads(){
		/*查询数据库中所有表名(只针对mysql)
		select table_name from information_schema.tables where table_schema='csdb' and table_type='base table';
		查询指定数据库中指定表的所有字段名column_name
		select column_name from information_schema.columns where table_schema='csdb' and table_name='users'
		当前数据库名称
		select database();*/
		
		String sql="select table_name from information_schema.tables where table_schema=database()";
		List<Map<String, Object>> ml=jdbc.queryForList(sql);
		ml.forEach(m->{
			m.forEach((k,v)->{
				String tableSql="select * from "+v;
				System.out.println(tableSql);
				System.out.println(jdbc.queryForList(tableSql));
			});
		});
	}

	@Test
	public void getPackageName(){
		String packagename=this.getClass().getPackage().getName();
		String classname=this.getClass().getSimpleName();
		String methodname=this.getClass().getDeclaredMethods()[0].getName();
		System.out.println(packagename);
		System.out.println(classname);
		System.out.println(methodname);
	}

	
	@Test
	@WithMockUser(username="test",roles="ADMIN")
	//@WithMockUser(username="test",roles="USER") //roles="USER"将会捕捉到AccessDeniedException异常
	public void test(){
		try {
			System.out.println(devpController.packagesJson());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

} 

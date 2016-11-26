package com.example;



import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dao.UserRepository;
import com.example.domain.Users;
import com.example.service.HighChartsService;
import com.example.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBoot22HighchartsApplicationTests {

	private Logger log=Logger.getLogger(getClass());
	
	@Autowired
	private HighChartsService hcService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${abc.abc.abc}")
	private String test;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserService userService;
	
	//@Test
	public void contextLoads() {
		log.info(hcService.getEntityMemoryData());
		log.info("目前使用的端口是:"+test);
	}

	//@Test
	public void testJpa(){
		Users u=new Users("zxz", "man", 100, "程序员");
		userRepository.save(u);
		List<?> l=userRepository.findAll();
		log.info(l);
	}
	
	//@Test
	public void testJdbc(){
		String sql="select * from users";
		String sql2="select count(*) from users";
		String sql3="select count(*) data,sex from users group by sex";
		List<Map<String, Object>> l=jdbcTemplate.queryForList(sql);
		
		log.info(l);
		log.info(jdbcTemplate.queryForList(sql2,Integer.class));
		log.info(jdbcTemplate.queryForList(sql3));
	}
	
	//复杂条件分页查询(这里只考虑单表查询),jpa真的很方便
	//@Test
	public void testComplexFind(){
		Users u=new Users();
		u.setSex("男");
		Example<Users> e=Example.of(u);
		Page<Users> pageU= userRepository.findAll(e,new PageRequest(0, 10,Direction.DESC,"id","name"));//第一页,每页10条,id降序
		log.info("******************************************************");
		log.info(pageU.getContent());
		log.info("******************************************************");
	}
	
	//测试上述封装的方法
	@Test
	public void testComplexFind2(){
		Users u=new Users();
		u.setSex("男");
		log.info(userService.getConditionPage(u, 0, 10, "age","name"));
	}
}

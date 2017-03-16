package com.example;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Student;
import com.example.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TxAopTest {

	@Autowired
	StudentService ss;
	
	@Before
	public void init(){
		ss.getStudentRepository().deleteAll();
	}
	
	@Test
	public void test(){
		try {
			List<Student> slist=ss.AopTest(false);
			slist.forEach(System.out::println);
		} catch (Exception e) {
			System.out.println("发生异常,如果事务回滚,那么aop事务配置成功");
		}		
	}
}

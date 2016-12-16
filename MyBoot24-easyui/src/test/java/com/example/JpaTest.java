package com.example;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

	private Logger log=Logger.getLogger(getClass());
	
	@Autowired
	private EntityManagerFactory emf;
	
	@Autowired
	private EntityManager em;
		
	//@Test
	public void JapTest1(){
		log.info(emf==null);//emf不为null说明注入成功
	}
	
	//@Test
	public void JpaTest2(){
		log.info(em==null);//em不为null说明注入成功
	}
	
	//@Test
	public void JpaTest3(){
		EntityManager temp=emf.createEntityManager();
		log.info(em==temp);//不相等,但用emf创建的em和注入的em功能应该是一样的
	}
	
	@Test
	public void JpaProcedureTest01(){//不带参数的存错过程
		String sql="{call pro_test4}";
		@SuppressWarnings("unchecked")		
		List<Student> stulist=em.createNativeQuery(sql,Student.class).getResultList();
		for (Student stu : stulist) {
			log.info(stu);
		}
		em.close();
	}
	
	@Test
	public void JpaProcedureTest02(){//带参数的存错过程
		String sql="{call pro_test5(?,?)}";	
		Query query=em.createNativeQuery(sql,Student.class);
		query.setParameter(1, 2);
		query.setParameter(2, "男");
		@SuppressWarnings("unchecked")
		List<Student> stulist=query.getResultList();
		for (Student stu : stulist) {
			log.info(stu);
		}
		em.close();
	}
	
	@Test
	public void JpaProcedureTest03(){//带参数的存错过程,和test02差不多
		String sql="{call pro_test5(?,?)}";	
		Query query=em.createNativeQuery(sql,Student.class);
		query.setParameter(1, 2);
		query.setParameter(2, "男");
		@SuppressWarnings("unchecked")
		List<Map<String, Object>>  map=query.getResultList();//差不多
		log.info(map);
		em.close();
	}
	
	@Test
	public void JpaProcedureTest04(){//a+b=c
		String sql="{call pro_test6(?,?)}";	
		Query query=em.createNativeQuery(sql);
		query.setParameter(1, 2);
		query.setParameter(2, 5);
		int result=(int)query.getSingleResult();
		log.info(result);
		em.close();
	}
	
/**
 * 暂时没有找到jap调用call pro_abc(@a,@b,@c output),和在存储过程中通过return 返回返回值的方法,
 * 目前发现能用的只有在存储过程中select * from table来返回结果的方法
 */
	
}

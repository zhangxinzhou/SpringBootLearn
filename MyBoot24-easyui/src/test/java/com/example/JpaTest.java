package com.example;

import java.util.List;

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

/**
 * jap能调用的存储过程有两种:
 *    1.调用无返回值的存储过程
 *    2.返回值为ResultSet（以select 形式返回的值）的存储过程(注意：EJB3不能调用以OUT参数返回值的存储过程,请使用jdbc的方式)。
 * @author user
 *
 */
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
		log.info(em==temp);//虽然不相等,但用emf创建的em和注入的em功能应该是一样的
	}
	
	@Test
	public void JpaProcedureTest01(){
		String sql="{call pro_selectfromtable(?)}";
		Query query=em.createNativeQuery(sql,Student.class);
		query.setParameter(1, "男");
		@SuppressWarnings("unchecked")		
		List<Student> stulist=query.getResultList();
		for (Student stu : stulist) {
			log.info("JpaProcedureTest01:"+stu);
		}
		em.close();
	}
	
	@Test  //我想返回一个list<map<String,object>>的泛用型存储过程,但这个似乎行不通
	public void JpaProcedureTest02(){
		String sql="{call pro_selectfromtable(?)}";	
		Query query=em.createNativeQuery(sql);
		query.setParameter(1, "男");		
		List<?> list=query.getResultList();
		log.info("JpaProcedureTest02:"+list);
		em.close();
	}
	
	
	@Test
	public void JpaProcedureTest03(){
		String sql="{call pro_selectsingle(?)}";	
		Query query=em.createNativeQuery(sql);
		query.setParameter(1, "jpa");
		String result=query.getSingleResult().toString();
		log.info("JpaProcedureTest03:"+result);
		em.close();
	}
	
	
}

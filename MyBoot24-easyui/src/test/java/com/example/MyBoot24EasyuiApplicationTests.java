package com.example;


import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dao.ClassRepository;
import com.example.dao.StudentRepository;
import com.example.dao.SysRoleRepository;
import com.example.dao.SysUserRepository;
import com.example.dao.TeacherRepository;
import com.example.domain.Class;
import com.example.domain.Student;
import com.example.domain.SysRole;
import com.example.domain.SysUser;
import com.example.domain.Teacher;
import com.example.util.EasyUIpageUtil;




@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBoot24EasyuiApplicationTests {
	
	private Logger log=Logger.getLogger(getClass());

	@Autowired
	private ClassRepository classDao;
	@Autowired
	private StudentRepository studentDao;
	@Autowired
	private SysRoleRepository roleDao;
	@Autowired
	private SysUserRepository userDao;
	@Autowired
	private TeacherRepository teacherDao;
	
	
	//单例测试所用	
	@Autowired
	private ClassRepository classDaoTemp;
	
	@Test
	public void contextLoads() {
		//测试单例模式
		System.out.println(classDao==classDaoTemp);
	}
	
	//测试班级
	@Test
	public void classTest(){
		Class cla=new Class(null,"一班");
		classDao.save(cla);
		log.info(classDao.findAll());
	}
	
	//测试学生
	@Test
	public void studentTest(){
		Student stu=new Student(null, "张三", "男", new Date());
		studentDao.save(stu);
		log.info(studentDao.findAll());
	}
	
	//测试角色 
	@Test
	public void roleTest(){
		SysRole role=new SysRole(new Long(1), "教师");
		roleDao.save(role);
		log.info(roleDao.findAll());
	}

	//测试用户
	@Test
	public void userTest(){
		SysUser user=new SysUser(new Long(1),"teacher","teacher",true);
		userDao.save(user);
		log.info(userDao.findAll());
	}
	
	//测试教师
	@Test
	public void teacherTest(){
		Teacher teacher=new Teacher(null,"李四","女",new Date());
		teacherDao.save(teacher);
		log.info(teacherDao.findAll());
	}
	
	//分页查询方法测试
	@Test
	public void easyuiPage(){
		Teacher teacher=new Teacher(null,"李四","女",new Date());
		teacherDao.save(teacher);
		Teacher temp=new Teacher();
		//temp.setSex("女");
		log.info("分页查询:"+EasyUIpageUtil.getPage(temp, 0, 10, null, null, teacherDao));
			
	}
}

package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentRepository;
import com.example.domain.Student;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	public StudentRepository getStudentRepository(){
		return studentRepository;
	}
	
	/*
	 * 测试aop是否配置成功的方法,flag=true将会抛出异常(一点要抛出异常,否则aop捕获不到异常,会认为这个方法正常执行而不会回滚事务)
	 * 抛出异常后,如果第一个学生和第二个学生都没有插入,说明配置成功
	 */
	public List<Student> AopTest(boolean flag) throws Exception{
		Student s1=new Student();
		Student s2=new Student();
		s1.setName("第一个学生");
		s2.setName("第二个学生");
		studentRepository.save(s1);
		if(flag){
			throw new Exception("exception!,check the rollback is working or not");
		}
		studentRepository.save(s2);
		List<Student> result=studentRepository.findAll();
		return result;
	}
}

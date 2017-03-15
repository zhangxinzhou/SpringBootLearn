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
	
	public List<Student> AopTest(boolean flag) throws Exception{
		Student s1=new Student();
		Student s2=new Student();
		s1.setName("第一个学生");
		s2.setName("第而个学生");
		studentRepository.save(s1);
		if(flag){
			throw new Exception("fuck you");
		}
		studentRepository.save(s2);
		List<Student> result=studentRepository.findAll();
		return result;
	}
}

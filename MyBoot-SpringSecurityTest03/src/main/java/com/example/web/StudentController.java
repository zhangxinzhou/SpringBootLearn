package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Student;
import com.example.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@RequestMapping("/allstu")
	@ResponseBody
	public List<Student> allStudent(){
		return studentService.getStudentRepository().findAll();
	}
	
	
	@RequestMapping("/aoptest")
	@ResponseBody
	public List<Student> AopTest(boolean flag) throws Exception{
		return studentService.AopTest(flag);
	}
}

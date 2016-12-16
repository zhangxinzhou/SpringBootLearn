package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.StudentRepository;
import com.example.domain.Student;
import com.example.util.EasyUIpage;
import com.example.util.EasyUIpageUtil;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepository studentDao;
	
	@RequestMapping("/toStudent")
	public String toStudent(){
		return "student";
	}
	
	@RequestMapping("/saveStudent.do")
	@ResponseBody
	public boolean saveStudent(Student stu){
		return studentDao.save(stu)!=null;
	}
	
	@RequestMapping("/delStudent.do")
	@ResponseBody
	public boolean delStudent(Long id){
		try {
			studentDao.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@RequestMapping("/getAllStudent.do")
	@ResponseBody
	public List<Student> getAllStudent(){
		return studentDao.findAll();
	}
	
	
	public EasyUIpage<Student> getPageStudent(Student stu,
			Integer page,Integer rows, String sort, String order){
		if(stu==null){stu=new Student();}
		return EasyUIpageUtil.getPage(stu, page, rows, sort, order, studentDao);				
	}
}

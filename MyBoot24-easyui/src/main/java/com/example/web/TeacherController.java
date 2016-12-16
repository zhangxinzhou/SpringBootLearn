package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.TeacherRepository;
import com.example.domain.Teacher;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherRepository teacherDao;
	
	@RequestMapping("/toTeacher")
	public String toTeacher(){
		return "teacher";
	}
	
	@RequestMapping("/saveTeacher")
	@ResponseBody
	public boolean saveTeacher(Teacher teacher){
		return teacherDao.save(teacher)!=null;
	}
	
	@RequestMapping("/delTeacher")
	@ResponseBody
	public boolean delTeacher(Long id){
		try {
			teacherDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

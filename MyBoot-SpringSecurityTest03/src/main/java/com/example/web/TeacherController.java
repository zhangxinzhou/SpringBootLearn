package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.service.TeacherService;

@Controller
public class TeacherController {

	@Autowired
	TeacherService teacherService;
}

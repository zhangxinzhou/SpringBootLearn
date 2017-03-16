package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.service.ClazzService;

@Controller
public class ClazzController {

	@Autowired
	ClazzService clazzService;
	
	
}

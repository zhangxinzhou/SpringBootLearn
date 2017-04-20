package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	RoleService roleService;
}

package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ClazzRepository;

@Service
public class ClazzService {

	@Autowired
	ClazzRepository clazzRepository;
	

	public ClazzRepository getClazzRepository(){
		return clazzRepository;
	}
}

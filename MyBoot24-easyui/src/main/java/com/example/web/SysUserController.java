package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.SysUserRepository;
import com.example.domain.SysUser;

@Controller
@RequestMapping("/user")
public class SysUserController {

	@Autowired
	private SysUserRepository userDao;
	
	@RequestMapping("/toUser")
	public String toUser(){
		return "user";
	}
	
	@RequestMapping("/saveUser.do")
	@ResponseBody
	public boolean saveUser(SysUser user){
		return userDao.save(user)!=null;
	}
	
	@RequestMapping("/delUser.do")
	@ResponseBody
	public boolean delUser(Long id){
		try {
			userDao.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

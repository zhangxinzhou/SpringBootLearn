package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.SysRoleRepository;
import com.example.domain.SysRole;

@Controller
@RequestMapping("/role")
public class SysRoleController {

	@Autowired
	private SysRoleRepository roleDao;
	
	@RequestMapping("/toRole")
	public String toRole(){
		return "role";
	}
	
	@RequestMapping("/saveRole.do")
	@ResponseBody
	public boolean saveRole(SysRole role){
		return roleDao.save(role)!=null;
	}
	
	@RequestMapping("/delRole.do")
	public boolean delRole(Long id){
		try {
			roleDao.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}

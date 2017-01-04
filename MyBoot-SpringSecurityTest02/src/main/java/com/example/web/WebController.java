package com.example.web;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {
	
	private Logger log=LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	@RequestMapping("/admin/admin1")
	public String admin1(){
		return "/admin/admin1";
	}
	
	@RequestMapping("/admin/admin2")
	public String admin2(ModelMap map){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();		
		log.info("方法admin2,判断是否取到Authentication:"+(auth!=null));  //成功取到,可以使用
		map.put("auth", auth);
		map.put("username", auth.getName());
		return "/admin/admin2";
	}
	
	@RequestMapping("/user/user1")
	public String user1(){
		return "/user/user1";
	}
	
	@RequestMapping("/user/user2")
	public String user2(){
		return "/user/user2";
	}
	
	@RequestMapping("/visitor/visitor1")
	public String visitor1(){
		return "/visitor/visitor1";
	}
	
	@RequestMapping("/visitor/visitor2")
	public String visitor2(){
		return "/visitor/visitor2";
	}
	
	@RequestMapping("/others/other1")
	public String other1(){
		return "/others/other1";
	}
	
	@RequestMapping("/others/other2")
	public String other2(){
		return "/others/other2";
	}
	
	//以下几个方法没起作用
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping("/foradmin")
	@ResponseBody
	public String foradmin(){//admin才能访问
		return "you are <span style='color:red;'>admin</span>";
	}
	
	@RolesAllowed("ROLE_USER")
	@RequestMapping("/foruser")
	@ResponseBody
	public String foruser(){//user才能访问
		return "you are <span style='color:red;'>user</span>";
	}
	
	@PermitAll
	@RequestMapping("/forall")
	@ResponseBody
	public Authentication forall(){
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	@PermitAll
	@RequestMapping("/fornoall")
	@ResponseBody
	public String fornoall(){
		return "任何人都不能访问";
	}

}

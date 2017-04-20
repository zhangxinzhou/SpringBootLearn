package com.example.web;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.anno.Notes;
import com.example.service.DevpService; 

@Notes("反射获取类的方法,和方法的注解")
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")//管理员才能访问,需要配置@EnableGlobalMethodSecurity(prePostEnabled=true)
public class DevpController {
	
	@Autowired
	private DevpService devpService;

	//返回数据,并且页面跳转	
	@RequestMapping("packages")
	public String packages(ModelMap mm){
		mm.put("data",devpService.getPackages());
		return "/devp/packages";
	}
	
	@RequestMapping("/classes")
	public String classes(ModelMap mm,String packagename){
		mm.put("data", devpService.getClasses(packagename));
		return "/devp/classes";
	}
	
	@RequestMapping("methods")
	public String methods(ModelMap mm,String classname){
		mm.put("data",devpService.getMethods(classname));
		return "/devp/methods";
	}
	
	@RequestMapping("tables")
	public String tables(ModelMap mm){
		mm.put("data",devpService.getAllTables());
		return "/devp/tables";
	}
	
	@RequestMapping("table")
	public String table(ModelMap mm,String tablename){
		mm.put("data",devpService.getTable(tablename));
		return "/devp/table";
	}
	
	//返回json数据,主要测试用	
	@RequestMapping("/packagesJson")
	@ResponseBody
	public Set<String> packagesJson(){
		return devpService.getPackages();
	}
	
	@RequestMapping("/classesJson")
	@ResponseBody
	public Set<String> classesJson(String packagename){
		return devpService.getClasses(packagename);
	}
	
	@RequestMapping("/methodsJson")
	@ResponseBody
	public List<Map<String, Object>> methodsJson(String classname){
		return devpService.getMethods(classname);
	}
	
	@RequestMapping("/tablesJson")
	@ResponseBody
	public List<Map<String, Object>> tablesJson(){
		return devpService.getAllTables();
	}
	
	@RequestMapping("/tableJson")
	@ResponseBody
	public List<Map<String, Object>> tableJson(String tablename){
		return devpService.getTable(tablename);
	}
}

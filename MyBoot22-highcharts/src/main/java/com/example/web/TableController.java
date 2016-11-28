package com.example.web;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.UserRepository;
import com.example.domain.Users;
import com.example.service.UserService;
import com.example.util.MyPage;

@Controller
public class TableController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/table1")
	public String table1(){
		return "table/table1";
	}
	
	@RequestMapping("/table2")
	public String table2(){
		return "table/table2";
	}
	
	@RequestMapping("/table/getAllUser.do")
	@ResponseBody
	public List<Users> getAllUser(){
		return userRepository.findAll();
	} 
	
	@RequestMapping("/table/getPageUser.do")
	@ResponseBody
	public Page<Users> getPageUser(String order,Integer offset,Integer limit){
		return userRepository.findAll(new PageRequest(offset, limit, Direction.ASC, "id"));
	}
	
	@RequestMapping("/table/getPageTest.do")
	@ResponseBody
	public MyPage<Users> getPageTest(String order,Integer offset,Integer limit){	
		Page<Users> p=userRepository.findAll(new PageRequest(offset/limit, limit, Direction.ASC,"id"));
		return new MyPage<>(p);
	}
	
	@RequestMapping("/table/getConditionPage.do")
	@ResponseBody
	public MyPage<Users> getConditionPage(Users u,Integer offset,Integer limit,String... order){
		return userService.getConditionPage(u, offset, limit, order);
	}
	
	@RequestMapping("/table/saveUser.do")
	@ResponseBody
	public Users saveUser(Users u){
		//添加和更新都是这个方法,id如果已经存在则是更新,否则添加
		return userRepository.save(u);
	}
	
	@RequestMapping("/table/delUser.do")
	@ResponseBody
	public boolean delUser(Long id){
		try {
			userRepository.delete(id);
			return true;//删除成功
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;//删除失败,不存在该用户或者其他的异常
	}
	
	
}

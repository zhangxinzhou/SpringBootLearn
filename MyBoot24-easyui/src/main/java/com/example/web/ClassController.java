package com.example.web;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.ClassRepository;
import com.example.domain.Class;
import com.example.util.EasyUIpage;
import com.example.util.EasyUIpageUtil;

@Controller
@RequestMapping("/class")
public class ClassController {

	@Autowired
	private ClassRepository classDao;
	
	/**
	 * 跳转到班级class.html
	 * @return
	 */
	@RequestMapping("/toClass")
	public String toClass(){
		return "class";
	}
	
	
	/**
	 * 保存班级
	 * @param cla 班级实体
	 * @return
	 */
	@RequestMapping("/saveClass.do")
	@ResponseBody
	public boolean saveClass(Class cla){
		return classDao.save(cla)!=null;
	}
	
	/**
	 * 删除班级
	 * @param id 待删除的id
	 * @return
	 */
	@RequestMapping("/delClass.do")
	@ResponseBody
	public boolean delClass(Long id){
		try {
			classDao.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 获取所有的班级
	 * @return
	 */
	@RequestMapping("/getAllClass.do")
	@ResponseBody
	public List<Class> getAllClass(){
		return classDao.findAll();
	}
	
	/**
	 *                分页排序条件查询方法 
	 * @param cla     条件实体类
	 * @param rows    每页大小
	 * @param page    第几页
	 * @param sort    待排序的字段
	 * @param order   asc|desc
	 * @return
	 */
	@RequestMapping("/getPageClass.do")
	@ResponseBody
	public EasyUIpage<Class> getPageClass(Class cla,
			Integer page,Integer rows, String sort, String order){
		if(cla==null){cla=new Class();}
		return EasyUIpageUtil.getPage(cla, page, rows, sort, order, classDao);
	}
}

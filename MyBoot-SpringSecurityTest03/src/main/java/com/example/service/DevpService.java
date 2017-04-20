package com.example.service;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.example.anno.Notes;
import com.example.util.PackageUtil;

@Service
public class DevpService {
	
	static LocalVariableTableParameterNameDiscoverer pnd=new LocalVariableTableParameterNameDiscoverer();
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private RequestMappingHandlerMapping rmhm;	

	//获取项目全部包名
	public Set<String> getPackages(){
		return PackageUtil.getPackages();
	}
	
	//根据包名获取该包下的所有类(不包含子包)
	public Set<String> getClasses(String packagename){
		if(packagename==null){
			packagename=this.getClass().getPackage().getName();
     	}
		return PackageUtil.getClassName(packagename, false);
	}
	
	//根据类名获取所有的方法和某些注解
	public List<Map<String, Object>> getMethods(String classname){
		Class<?> cla = null;
		try {
			if(classname==null){
				cla=getClass();
			}else{
				cla=Class.forName(classname);
			}	
			return getMethods(cla);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 获取方法的信息,如果是controller则还要获取它的注解信息
	 */
	public List<Map<String, Object>> getMethods(Class<?> cla){
		List<Map<String, Object>> ml=new ArrayList<>();
		Method[] methods=cla.getDeclaredMethods();
		Arrays.asList(methods).forEach(method->{
			Map<String, Object> map=new HashMap<>();
			map.put("package", cla.getPackage().getName());
			map.put("class", cla.getSimpleName());
			map.put("isController", cla.getDeclaredAnnotation(Controller.class)!=null);
			map.put("method", method.getName());
			map.put("params", getParams(method));
			map.put("return", method.getReturnType().getSimpleName());
			getAnnotations(method, map);
			ml.add(map);
		});
		return ml;
	}
	
	/*
	 * 获取方法的参数类型和参数名称
	 */
	public List<String> getParams(Method method){
		List<String> list=new ArrayList<>();
		if(method!=null){
			Class<?>[] clas=method.getParameterTypes();
			Parameter[] params=method.getParameters();
			String[] names=pnd.getParameterNames(method);//无法获取接口定义的方法的参数
			for (int i = 0; i < clas.length; i++) {
				String paramname=names!=null?names[i]:params[i].getName();
				String param=clas[i].getSimpleName()+" "+paramname;
				list.add(param);
			}
		}
		return list;
	}
	/*
	 * 获取方法上的注解
	 */
	public void getAnnotations(Method method,Map<String, Object> map){
		Map<RequestMappingInfo, HandlerMethod> hm=rmhm.getHandlerMethods();		
		hm.forEach((k,v)->{
			if(v.getMethod().getName().equals(method.getName())){ 
				map.put("urls", k.getPatternsCondition().getPatterns());
			}
		});		
		Notes note=method.getDeclaredAnnotation(Notes.class);
		map.put("note", note!=null?note.value():null);
		map.put("isResponseBody", method.getDeclaredAnnotation(ResponseBody.class)!=null);
		map.put("urls", map.get("urls"));
	}
	
	//获取当前数据库下所有的表名
	public List<Map<String, Object>> getAllTables(){
		String sql="select TABLE_NAME,TABLE_SCHEMA,CREATE_TIME,UPDATE_TIME from information_schema.tables where TABLE_SCHEMA=database()";
		return jdbc.queryForList(sql);
	}
	
	//根据表的名称获取该表的数据(暂时不考虑分页)
	public List<Map<String, Object>> getTable(String tablename){
		if(tablename==null){
			String sql="select TABLE_NAME from information_schema.tables where TABLE_SCHEMA=database()";
			List<String> tablenames=jdbc.queryForList(sql,String.class);
			if(tablenames==null||tablenames.size()<1){
				return null;
			}
			tablename=tablenames.get(0);
		}
		String sql ="select * from "+tablename;
		return jdbc.queryForList(sql);
	}
}

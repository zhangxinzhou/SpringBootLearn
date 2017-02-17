package com.example.service;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.util.ClassUtils;
import com.example.util.Description;


//反射获得controller的类名,方法和requestmapping等的值
@Service
public class TestService {


	LocalVariableTableParameterNameDiscoverer pnd=new LocalVariableTableParameterNameDiscoverer();
	
	//注意,这里没有考虑Controller类上可能带有RequestMapping的情况
	public List<Map<String, Object>> getRequestMapping(Class<?> cla){
		List<Map<String, Object>> ml=new ArrayList<>();
		//getDeclaredMethods的关键词是：自身，所有方法，不继承而getMethods的关键词是public 继承
		Method[] methods=cla.getDeclaredMethods();		
		for (int i = 0; i < methods.length; i++) {
			Map<String, Object> map=new HashMap<>();		
			map.put("cla", cla.getSimpleName());
			map.put("method", methods[i].getName());
			Description desc=methods[i].getAnnotation(Description.class);
			map.put("desc", desc==null?null:desc.value());
			boolean present=methods[i].isAnnotationPresent(RequestMapping.class);
			if(present){
				//得到requestMapping注释
				RequestMapping rm=methods[i].getAnnotation(RequestMapping.class);			
				//输出 annotation RequestMapping包含的信息(headers=[], name=, path=[], value=[value], produces=[], method=[], params=[], consumes=[])
				//System.err.println(annotation);
				String[] values=rm.value();	
				map.put("rm", values!=null?values[0]:null);				
			}else{
				map.put("rm", null);
			}			
			ml.add(map);
		}		
		return ml;
	}
	
	
	//需要展示的东西
	//类名,包名,类名上的关于requestMapping注解
	//方法名,方法上参数,方法的返回值,方法的注解
	public List<Map<String, Object>> getMoreRequestMapping(Class<?> cla){
		List<Map<String, Object>> ml=new ArrayList<>();
		String simpleName=cla.getSimpleName();
		String packageName=cla.getPackage().toString();
		RequestMapping claRM=cla.getDeclaredAnnotation(RequestMapping.class);
		boolean claRB=cla.isAnnotationPresent(ResponseBody.class);
		List<RequestMapping> claRMlist=claRM!=null?Arrays.asList(claRM):null;
		String cRM=claRM!=null?claRM.value()[0]:null;
		Method[] methods=cla.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Map<String, Object> map=new HashMap<>();
			map.put("cla", simpleName);
			map.put("package", packageName);
			map.put("claRM", claRMlist);
			map.put("claRB", claRB);
			map.put("method", methods[i].getName());		
			map.put("params", getParams(methods[i]));
			map.put("return", methods[i].getReturnType());
			RequestMapping rm=methods[i].getAnnotation(RequestMapping.class);
			boolean rb=methods[i].isAnnotationPresent(ResponseBody.class);
			map.put("rm", rm!=null?Arrays.asList(rm):null);
			map.put("rb", rb);
			String mRM=rm!=null?rm.value()[0]:null;
			map.put("href", getURI(cRM,mRM));
			ml.add(map);
		}
		return ml;
	}
	
	//上面的方法的改进
	public List<Map<String, Object>> getMoreRequestMapping2(Class<?> cla){
		List<Map<String, Object>> ml=new ArrayList<>();
		String simpleName=cla.getSimpleName();
		String packageName=cla.getPackage().toString();
		RequestMapping claRM=cla.getDeclaredAnnotation(RequestMapping.class);
		boolean claRB=cla.isAnnotationPresent(ResponseBody.class);
		Method[] methods=cla.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Map<String, Object> map=new HashMap<>();
			RequestMapping methodRM= methods[i].getAnnotation(RequestMapping.class);
			map.put("cla", simpleName);
			map.put("package", packageName);
			map.put("claRM", getRM(claRM));
			map.put("claRB", claRB);
			map.put("method", methods[i].getName());		
			map.put("params", getParams(methods[i]));
			map.put("return", methods[i].getReturnType());
			map.put("rm", methodRM);
			map.put("rb", methods[i].isAnnotationPresent(ResponseBody.class));
			map.put("href", getURI(claRM, methodRM));
			ml.add(map);
		}
		return ml;
	}
	
	//根据包名获取包下所有的类名
	public List<Map<String, Object>> getClassNamebyPackageName(String packageName){
		List<Map<String, Object>> ml=new ArrayList<>();
		Set<String> claNames=ClassUtils.getClassName(packageName, false);
		if(claNames!=null){
			for (String str : claNames) {
				Map<String, Object> map=new HashMap<>();
				map.put("packageName", packageName);
				map.put("className", str);
				ml.add(map);
			}
		}
		return ml;
	}
	
	//**********************************************************************************************************
	//以下是工具方法

	//获取方法参数的类型和参数名称
	private List<String> getParams(Method method){
		List<String> result=new ArrayList<>();
		Parameter[] params=method.getParameters();
		String[] names=pnd.getParameterNames(method);
		for (int j = 0; j < params.length; j++) {
			String str=params[j].toString();
			result.add(str.replace(params[j].getName(), names[j]));
		}
		return result;
	}
	
	//拼接URI(如果类前面有requestMapping注解,那么需要拼接,如果没有不需要拼接)
	private String getURI(String claRM,String methodRM){
		String cla=dealValue(claRM);
		String method=dealValue(methodRM); 
		if(method!=null){
			return cla!=null?cla+method:method;
		}
		return null;
	}
	
	//如果能看源码的话,这个很简单吧...spring一定有这个功能,RequestMappingHandlerMapping可以满足需求
	private List<String> getURI(RequestMapping claRM,RequestMapping methodRM){	
		List<String> cla=getRM(claRM);
		List<String> method=getRM(methodRM);
		List<String> result=new ArrayList<>();
		if(method!=null){
			if(cla==null){
				for (String me : method) {
					result.add(dealValue(me));
				}
				return result;
			}else{
				for (String c : cla) {
					for (String me : method) {
						result.add(getURI(c, me));
					}
				}
				return result;
			}
		}
		return null;
	}
	
	//将数据处理成为符合拼接要求的字符串,比如(/path),以/开头,结尾不能有/,字符串长度要大于1
	private String dealValue(String value){
		if(value!=null){
			if(!value.startsWith("/")){//如果不是以/开头,处理成以/开头
				value="/"+value;
			}
			if(value.endsWith("/")&&value.length()>1){//如果是以/结尾,去掉/
				value=value.substring(0, value.length()-1);
			}
			return value;
		}
		return null;
	}
	
	private List<String> getRM(RequestMapping rm){
		if(rm!=null){
			return Arrays.asList(rm.value());
		}
		return null;
	}

}

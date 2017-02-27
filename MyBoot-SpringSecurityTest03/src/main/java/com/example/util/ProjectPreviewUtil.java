package com.example.util;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.example.anno.Notes;

@Notes(author="zxz",lastmodifier="zxz",date="2017-2-16",value="获取controller注解的信息")
public class ProjectPreviewUtil { 
	//获取参数名称(反射只能获取到参数的类型,参数的名称用arg0..来表示,无法获取参数的名称,所以要借助此类)
	@Notes(author="zxz",lastmodifier="zxz",date="2017-2-16",value="作用:借助此类获取方法参数的名称")
	LocalVariableTableParameterNameDiscoverer pnd=new LocalVariableTableParameterNameDiscoverer();

	@Notes(value="作用:借助handlerMapping获取全部的RequestMapping")
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	
	/*
	 * 获取类上面的注解
	 */
	public static Map<String, Object> getClassAnnotationInfo(Class<?> cla){
		Map<String, Object> can=new HashMap<>();//class annotation name 类的注解
		Annotation[] classAnnos=cla.getDeclaredAnnotations();		
		Arrays.asList(classAnnos).forEach(classAnno->{
			Map<String, Object> camn=new HashMap<>();//class annotation method name 类的注解的方法的名称和值
			Arrays.asList(classAnno.annotationType().getDeclaredMethods()).forEach(classAnnoMethod->{
				String name=classAnnoMethod.getName();
				Object value=null;
				try {
					value=classAnnoMethod.invoke(classAnno);
					if(value instanceof String[]){
						String[] str=(String[])value;
						value=Arrays.asList(str);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				camn.put(name, value);
			});	
			can.put(classAnno.annotationType().getSimpleName(), camn);
		});
		return can;
	}
	
	/*
	 * 获取域上面的注解
	 */
	public static Map<String, Object> getFieldsAnnotationInfo(Class<?> cla){
		Map<String, Object> cfn=new HashMap<>();//class field name 类的域
		Field[] classFields=cla.getDeclaredFields();
		Arrays.asList(classFields).forEach(classField->{
			Map<String, Object> cfan=new HashMap<>();//class field annotation name 类的域的注解
			Annotation[] fieldAnnos=classField.getDeclaredAnnotations();
			Arrays.asList(fieldAnnos).forEach(fieldAnno->{
				Map<String, Object> cfamn=new HashMap<>();//class field annotation method name 类的域的注解
				Arrays.asList(fieldAnno.annotationType().getDeclaredMethods()).forEach(fieldAnnoMethod->{
					String name=fieldAnnoMethod.getName();
					Object value=null;
					try {
						value=fieldAnnoMethod.invoke(fieldAnno);
						if(value instanceof String[]){
							String[] str=(String[])value;
							value=Arrays.asList(str);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					cfamn.put(name, value);
				});
				cfan.put(fieldAnno.annotationType().getSimpleName(), cfamn);
			});
			cfn.put(classField.getName(), cfan);
		});
		return cfn;
	}
	
	/* 
	 * 获取方法上面的注解
	 */
	public static Map<String, Object> getMethodsAnnotationInfo(Class<?> cla){
		Map<String, Object> cmn=new HashMap<>();//class method name 类的方法
		Method[] classMethods= cla.getDeclaredMethods();
		Arrays.asList(classMethods).forEach(classMethod->{
			Map<String, Object> cman=new HashMap<>();//class method annotation name 类的方法的注解
			Annotation[] classMethodAnnos=classMethod.getDeclaredAnnotations();
			Arrays.asList(classMethodAnnos).forEach(classMethodAnno->{
				Map<String, Object> cmamn=new HashMap<>();//class method annotation method name 类的方法的注解的方法
				Arrays.asList(classMethodAnno.annotationType().getDeclaredMethods()).forEach(classMethodAnnoMethod->{
					String name=classMethodAnnoMethod.getName();
					Object value=null;
					try {
						value=classMethodAnnoMethod.invoke(classMethodAnno);
						if(value instanceof String[]){
							String[] str=(String[])value;
							value=Arrays.asList(str);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					cmamn.put(name, value);
				});
				cman.put(classMethodAnno.annotationType().getSimpleName(), cmamn);
			});
			cmn.put(classMethod.getName(), cman);
		});
		return cmn;
	}
	

	
	/*
	 * 获取controller的类和方法上面的注解
	 */
	public static void getControllerInfo(Class<?> cla){
		
	}
	
	
}

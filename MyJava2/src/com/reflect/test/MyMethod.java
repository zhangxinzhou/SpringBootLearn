package com.reflect.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MyMethod {

	@annoForMethod(value="no1")
	public static void method1(Map<String, Object> params) {
		System.out.println(params);
		System.out.println("method1");
	}
	
	@annoForMethod(value="no2")
	public static void method2(Map<String, Object> params) {
		System.out.println(params);
		System.out.println("method2");
	}
	
	@annoForMethod(value="no3")
	public static void method3(Map<String, Object> params) {
		System.out.println(params);
		System.out.println("method3");
	}
	
	public static List<String> getMethods() {
		List<String> result=new ArrayList<>();
		Class<?> cla=MyMethod.class;
		Method[] methods=cla.getDeclaredMethods();
		for (Method method : methods) {
			result.add(method.getName());
		}
		return result;
	}
	
	public static Object doMethod(String methodName,Map<String, Object> params) throws Exception {
		Class<?> cla=MyMethod.class;
		Method method=cla.getDeclaredMethod(methodName, Map.class);
		return method.invoke(cla, params);
	}
	
	public static void doMethodTest() throws Exception {
		Map<String, Object> map=new HashMap<>();
		map.put("xxx", "xxx");
		doMethod("method1", map);
	}
	
	public static List<String> getAnnotations(){
		List<String> result=new ArrayList<>();
		Class<?> cla=MyMethod.class;
		Method[] methods=cla.getDeclaredMethods();
		for (Method method : methods) {
			annoForMethod[] annotation = method.getDeclaredAnnotationsByType(annoForMethod.class);
			for (annoForMethod anno : annotation) {
				result.add(anno.value());
			}		 
		}
		return result;
	}
	
	public static Object doMethodByAnnotionValue(String annoValue,Map<String, Object> params) throws Exception{
		Class<?> cla=MyMethod.class;
		Method[] methods=cla.getDeclaredMethods();		
		for (Method method : methods) {
			annoForMethod[] annotation = method.getDeclaredAnnotationsByType(annoForMethod.class);
			for (annoForMethod anno : annotation) {		
				if(Objects.equals(annoValue, anno.value())) {
					return method.invoke(cla, params);
				}
			}		 
		}
		return null;
	}
	
	public static void doMethodByAnnotionValueTest() throws Exception {
		Map<String, Object> map=new HashMap<>();
		map.put("xxx", "xxx");
		doMethodByAnnotionValue("no1", map);
	}
	
	public static void main(String[] args) throws Exception {
		doMethodByAnnotionValueTest();
	}
}

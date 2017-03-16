package com.example;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.example.service.DevpService;
import com.example.web.TestController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HandlerMappingTest {

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	
	@Autowired
	private DevpService devpService;
	
	@Test
	public void test(){
		Map<RequestMappingInfo, HandlerMethod> map=handlerMapping.getHandlerMethods();
		map.forEach((info,method)->{
			//debug看清楚里面放了什么,所以这样写
			RequestMappingInfo info1=info;
			HandlerMethod method1=method;
			System.out.println("info:"+info1+",method:"+method1);
			System.out.println(info1.getPatternsCondition().getPatterns());
		});
		System.out.println(handlerMapping.getHandlerMethodsForMappingName("nameTest"));		
		
		Method method=TestController.class.getDeclaredMethods()[0];
		map.forEach((k,v)->{
			if(v.getMethod()==method){
				System.out.println("无法靠method判断,因为地址不一样:");
				System.out.println(v.getMethod()==method);
			}
			if(v.getMethod().getName().equals(method.getName())){
				System.out.println("通过判断方法名称是否相同来判断:");
				System.out.println(v.getMethod().getName().equals(method.getName()));
			}
		});
	}
	
	@Test
	public void testMethodUtil(){
		System.out.println(devpService.getMethods(TestController.class));
	}
}

package com.example;


import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.web.WebController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBoot28GetClientMacIpApplicationTests {

	LocalVariableTableParameterNameDiscoverer pnd=new LocalVariableTableParameterNameDiscoverer();
	
	@Test
	public void contextLoads() {		
		Method[] methods=WebController.class.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {			
			Parameter[] params=methods[i].getParameters();
			String[] names=pnd.getParameterNames(methods[i]);
			List<String> result=new ArrayList<>();
			//这个带interface或者class
			/*for (int j = 0; j < params.length; j++) {
				StringBuffer sb=new StringBuffer();
				sb.append(params[j].getType()+" "+names[j]);
				result.add(sb.toString());
			}*/
			
			for (int j = 0; j < params.length; j++) {
				String str=params[j].toString();
				result.add(str.replace(params[j].getName(), names[j]));
			}
			
			System.out.println(result);
		}
		
		
	}
	
	//@Test
	public void test1(){
		Method[] methods=WebController.class.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Parameter[] params=methods[i].getParameters();
			for (int j = 0; j < params.length; j++) {
				String douhao=(j==params.length-1)?"":",";
				System.out.print(params[j]);		
				System.out.print(";"+"getType():"+params[j].getType()+" ");
				System.out.print("getParameterizedType():"+params[j].getParameterizedType()+" ");
				System.out.print("getName():"+params[j].getName()+douhao);
			}
			System.out.println();
		}
		
	}
	
	//@Test
	public void test2(){
		Method[] methods=WebController.class.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			String[] names=pnd.getParameterNames(methods[i]);
			for (int j = 0; j < names.length; j++) {
				String douhao=(j==names.length-1)?"":",";
				System.out.print(names[j]+douhao);
			}
			System.out.println();
		}
		
	}
	

}

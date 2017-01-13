package com.example;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.util.ClassUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PackageTest {

	//测试根据包名获取包下所有类名的方法,测试成功 
	@Test
	public void getClaFormPackage(){
		String packageName="com.example.web";
		Set<String> ss= ClassUtils.getClassName(packageName, true);
		for (String str : ss) {
			System.out.println(getClass(str).getSimpleName());
		}
	}
	
	private Class<?> getClass(String claName){
		try {
			return Class.forName(claName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}

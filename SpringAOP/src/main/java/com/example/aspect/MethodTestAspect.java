package com.example.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodTestAspect {
	
	private Logger log=LoggerFactory.getLogger(getClass());
	private String classname=this.getClass().getSimpleName();

	/*
	 * 只处理com.example.web包下的WebController类的test方法,失去了"面向切面"的意义,这里只是一个测试
	 */
	@AfterReturning(value="execution(* com.example.web.WebController.methodtest(..))",returning="obj")
	public void doAfterReturning(Object obj){
		String methodname=Thread.currentThread().getStackTrace()[1].getMethodName();
		String result="执行完毕后返回结果:"+obj;
		log.error("类名:[{}],方法名:[{}],结果:[{}]", classname,methodname,result);
	}
	
}

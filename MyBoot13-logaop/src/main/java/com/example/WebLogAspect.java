package com.example;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Order(10)   //@Order(i)注解来标识切面的优先级。i的值越小，优先级越高,@order(5)会比@order(10)先执行完毕
             //在@Before中优先执行@Order(5)的内容，再执行@Order(10)的内容
             //在@After和@AfterReturning中优先执行@Order(10)的内容，再执行@Order(5)的内容
             //可能的顺序1 @order(5).doBefore            2 @order(10).doBefore 
             //       3 @order(10).@AfterReturning     4 @order(5).@AfterReturning (先进后出)
             
@Aspect
@Component
public class WebLogAspect {

	private Logger log=Logger.getLogger(getClass());
	
	//切入点
	//execution():     表达式主体
	//public           修饰符,所有public的方法
	//第一个*            第一个*代表返回任意类型
	//com.example      要拦截的包名
	//第一个..           当前包(com.example)和当前包的所有子包
	//第二个*            所有类
	//第三个*(..)        *所有方法,(..)括弧里面表示方法的参数，两个句点表示任何参数。
	@Pointcut("execution(public * com.example.web..*.*(..))")
	public void webLog(){		
	}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint){
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request=attributes.getRequest();
		
		//日志记录请求内容
		log.info("URL:"+request.getRequestURL().toString());
		log.info("HTTP_METHOD:"+request.getMethod());
		log.info("IP:"+request.getRemoteAddr());
		log.info("CLASS_METHOD:"+joinPoint.getSignature().getDeclaringTypeName()+"."
		                        +joinPoint.getSignature().getName());
		log.info("ARGS:"+Arrays.toString(joinPoint.getArgs()));
	}
	
	//如果不是Object ret,会报如下错错误
	//java.lang.IllegalStateException: Returning argument name 'ret' was not bound in advice arguments
	@AfterReturning(returning="ret",pointcut="webLog()")
	public void doAfterReturning(Object ret){
		log.info("RESPONSE:"+ret);
	}
}

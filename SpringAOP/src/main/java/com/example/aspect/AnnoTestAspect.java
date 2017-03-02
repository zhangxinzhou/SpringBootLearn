package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 *使用@Aspect注解将一个java类定义为切面类
 *使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
 *根据需要在切入点不同位置的切入内容
 *使用@Before在切入点开始处切入内容
 *使用@After在切入点结尾处切入内容
 *使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
 *使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
 *使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
 */
@Aspect
@Component
public class AnnoTestAspect {

	private Logger log=LoggerFactory.getLogger(getClass());
	private String classname=this.getClass().getSimpleName();
	

	@Pointcut("@annotation(com.example.annotation.AopTest)")
	public void annoPointCut(){}
	
	@Before(value="annoPointCut()")
	public void doBefore(JoinPoint joinPoint){
		String methodname=Thread.currentThread().getStackTrace()[1].getMethodName();
		String result=joinPoint.getSignature().getName()+"()执行前";
		log.error("类名:[{}],方法名:[{}],结果:[{}]", classname,methodname,result);
	}
	
	@After(value="annoPointCut()")
	public void doAfter(JoinPoint joinPoint){
		String methodname=Thread.currentThread().getStackTrace()[1].getMethodName();
		String result=joinPoint.getSignature().getName()+"()执行后";
		log.error("类名:[{}],方法名:[{}],结果:[{}]", classname,methodname,result);
	}
	
	@AfterReturning(pointcut="annoPointCut()",returning="obj")
	public void doAfterReturning(Object obj){
		String methodname=Thread.currentThread().getStackTrace()[1].getMethodName();
		String result="执行完毕后返回结果:"+obj;
		log.error("类名:[{}],方法名:[{}],结果:[{}]", classname,methodname,result);
	}
	
	@AfterThrowing(pointcut="annoPointCut()",throwing="exception")
	public void doAfterThrowing(Throwable exception){
		String methodname=Thread.currentThread().getStackTrace()[1].getMethodName();
		String result="异常信息(若出现异常):"+exception.getMessage();
		log.error("类名:[{}],方法名:[{}],结果:[{}]", classname,methodname,result);
	}
	
	/*
	 * 环绕需要一个返回值proceedingJoinPoint.proceed(),否则被切的方法没有办法继续
	 */
	@Around(value="annoPointCut()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String methodname=Thread.currentThread().getStackTrace()[1].getMethodName();
		String resultbefore=proceedingJoinPoint.getSignature().getName()+"()环绕前";
		log.error("类名:[{}],方法名:[{}],结果:[{}]", classname,methodname,resultbefore);
		Object around= proceedingJoinPoint.proceed();
		String resultafter=proceedingJoinPoint.getSignature().getName()+"()环绕后";
		log.error("类名:[{}],方法名:[{}],结果:[{}]", classname,methodname,resultafter);
		return around;
	}
	
	
}

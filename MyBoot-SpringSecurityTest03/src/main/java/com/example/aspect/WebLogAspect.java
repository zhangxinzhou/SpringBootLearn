package com.example.aspect;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * aspect注明这是一个aspect程序,@component注解把他变为spring管理的bean,否则不生效
 * @author user
 *
 */
@Aspect
@Component
public class WebLogAspect {
	
	private Logger log=LoggerFactory.getLogger(getClass());
	private static final String line="********************************************************";
	
	ThreadLocal<Long> startTime=new ThreadLocal<>();

	/*
	 * 定义切入点
	 */
	@Pointcut(value="execution(* com.example.web.*.*(..))")
	public void WebLogPointCut(){}
	
	/*
	 * 切点之前执行的方法
	 */
	@Before(value="WebLogPointCut()")
	public void doBefore(JoinPoint jp){
		startTime.set(System.currentTimeMillis());
	}
	
	/*
	 * 切点之后执行的方法(即使抛出异常也会执行)
	 */
	@After(value="WebLogPointCut()") 
	public void doAfter(JoinPoint jp){

	}
	
	/*
	 * 返回结果执行的方法(抛出异常不会执行)
	 */
	@AfterReturning(value="WebLogPointCut()",returning="result")
	public void doAfterReturning(JoinPoint jp,Object result){
		String url = "";
		String httpMethod = "";
		String ip = "";
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();		
		if(requestAttributes!=null){
			ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
			HttpServletRequest request=attributes.getRequest();
			url=request.getRequestURL().toString();
			httpMethod=request.getMethod();
			ip=request.getRemoteAddr();
		}
		String spend="SPEND TIME : "+(System.currentTimeMillis()-startTime.get())+"ms";
		
		log.info(getLineBreak("log begin"));
		log.info("TIME : " + LocalDateTime.now());
		log.info("URL : " + url);
        log.info("HTTP_METHOD : " + httpMethod);
        log.info("IP : " + ip);
        log.info("CLASS_METHOD : " + jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(jp.getArgs()));
        log.info("RETURN : " + result);       
		log.info(spend);
		log.info(getLineBreak("log end"));
	}
	
	/*
	 * 发生异常时执行的方法
	 */
	@AfterThrowing(value="WebLogPointCut()",throwing="ex")
	public void doAfterThrowing(JoinPoint jp,Throwable ex){
		log.error(getLineBreak("exception begin"));
		log.error(jp.toLongString());
		log.error(ex.toString());
		log.error(getLineBreak("exception end"));
	}
	
	/*
	 * 环绕
	 */
	//@Around(value="WebLogPointCut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
		Object obj=null;
		//在切点之前做
		obj=pjp.proceed();//被切的方法继续执行
		//在切点之后做
		return obj;
	}
	
	/**
	 * 分行符
	 * @param message 分行符中间的文字信息
	 * @return
	 */
	private String getLineBreak(String message){
		StringBuffer sb=new StringBuffer(line);
		sb.insert(line.length()/2, message);
		return sb.toString();
	}
}

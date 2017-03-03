package com.example.aspect;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class WebLogAspect {
	
	private Logger log=LoggerFactory.getLogger(getClass());
	private String line="********************************************************";
	
	ThreadLocal<Long> startTime=new ThreadLocal<>();

	@Pointcut(value="execution(* com.example.web.*.*(..))")
	public void WebLogPointCut(){}
	
	@Before(value="WebLogPointCut()")
	public void doBefore(JoinPoint jp){
		startTime.set(System.currentTimeMillis());
	}
	
	@After(value="WebLogPointCut()") 
	public void doAfter(JoinPoint jp){

	}
	
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
		
		log.info(getLineBreak("访问记录开始"));
		log.info("TIME : " + LocalDateTime.now());
		log.info("URL : " + url);
        log.info("HTTP_METHOD : " + httpMethod);
        log.info("IP : " + ip);
        log.info("CLASS_METHOD : " + jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(jp.getArgs()));
        log.info("RETURN : " + result);       
		log.info(spend);
		log.info(getLineBreak("访问记录结束"));
	}
	
	@AfterThrowing(value="WebLogPointCut()",throwing="ex")
	public void doAfterThrowing(JoinPoint jp,Throwable ex){
		log.error(getLineBreak("异常记录开始"));
		log.error(jp.toLongString());
		log.error(ex.toString());
		log.error(getLineBreak("异常记录结束"));
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

package com.example;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mongodb.BasicDBObject;

@Aspect
@Order(1)
@Component
public class WebLogAspect {

	private Logger log=Logger.getLogger(getClass());
	
	// 切入点
	// execution(): 表达式主体
	// public 修饰符,所有public的方法
	// 第一个* 第一个*代表返回任意类型
	// com.example 要拦截的包名
	// 第一个.. 当前包(com.example)和当前包的所有子包
	// 第二个* 所有类
	// 第三个*(..) *所有方法,(..)括弧里面表示方法的参数，两个句点表示任何参数。
	@Pointcut("execution(public * com.example.web..*.*(..))")
	public void webLog() {}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint){
		ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request=attributes.getRequest();
		// 获取要记录的日志内容
        BasicDBObject logInfo = getBasicDBObject(request, joinPoint);
        log.info(logInfo);
		
	}
	
	private BasicDBObject getBasicDBObject(HttpServletRequest request, JoinPoint joinPoint){
		//基本信息
		BasicDBObject r=new BasicDBObject();
		r.append("requestURL", request.getRequestURL().toString());
        r.append("requestURI", request.getRequestURI());
        r.append("queryString", request.getQueryString());
        r.append("remoteAddr", request.getRemoteAddr());
        r.append("remoteHost", request.getRemoteHost());
        r.append("remotePort", request.getRemotePort());
        r.append("localAddr", request.getLocalAddr());
        r.append("localName", request.getLocalName());
        r.append("method", request.getMethod());
        r.append("headers", getHeadersInfo(request));
        r.append("parameters", request.getParameterMap());
        r.append("classMethod", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        r.append("args", Arrays.toString(joinPoint.getArgs()));
        return r;
	}
	
	private Map<String, String> getHeadersInfo(HttpServletRequest request){
		Map<String, String> map=new HashMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String key=(String)headerNames.nextElement();
			String value=request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}
}

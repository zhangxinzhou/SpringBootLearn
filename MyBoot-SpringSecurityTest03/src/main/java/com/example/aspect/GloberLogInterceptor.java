package com.example.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//import com.yudian.mall.common.Environment;

@Aspect
@Component
public class GloberLogInterceptor {

	@Value("${environment}")
	private String environment;

	@Resource
	private HttpServletRequest request;

	public Log log = LogFactory.getLog(GloberLogInterceptor.class);

	@Pointcut("execution(* com.yudian.mall.controller*..*(..))")
	public void log() {
		log.info(request.getParameterMap());
	}

	@Before("log()")
	public void before(JoinPoint joinPoint) {
		//if (StringUtils.equals(environment, Environment.DEV)) {
		boolean falg=true;
	    if (falg) {
			StringBuilder sb = new StringBuilder("\nSpringMVC action report -------- ")
					.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
					.append(" ------------------------------\n");

			sb.append("Controller  : ").append(joinPoint.getTarget().getClass().getName()).append(".(")
					.append(joinPoint.getTarget().getClass().getSimpleName()).append(".java:1)");
			sb.append("\nMethod      : ").append(joinPoint.getSignature().getName()).append("\n");

			String uri = request.getRequestURI();
			if (uri != null) {
				sb.append("url         : ").append(uri).append("\n");
			}

			Enumeration<String> e = request.getParameterNames();
			if (e.hasMoreElements()) {
				sb.append("Parameter   : ");
				while (e.hasMoreElements()) {
					String name = e.nextElement();
					String[] values = request.getParameterValues(name);
					if (values.length == 1) {
						sb.append(name).append("=").append(values[0]);
					} else {
						sb.append(name).append("[]={");
						for (int i = 0; i < values.length; i++) {
							if (i > 0)
								sb.append(",");
							sb.append(values[i]);
						}
						sb.append("}");
					}
					sb.append("  ");
				}
				sb.append("\n");
			}
			sb.append("--------------------------------------------------------------------------------\n");
			System.out.print(sb.toString());
		} else {
			StringBuilder sb = new StringBuilder();

			sb.append(request.getRequestURI()).append(", ");
			sb.append("IP: " + request.getRemoteAddr()).append(", [");

			Map<String, String[]> parameters = request.getParameterMap();

			for (Map.Entry<String, String[]> entity : parameters.entrySet()) {

				//sb.append(String.format("%s = %s, ", entity.getKey(), StringUtils.join(entity.getValue(), ',')));
				System.out.println(entity);
			}

			sb.delete(sb.length() - 2, sb.length()).append("]");

			log.info(sb.toString());
		}
	}

	@AfterReturning(pointcut = "log()", returning = "returnValue")
	public void afterReturning(JoinPoint joinPoint, Object returnValue) {
		log.info("return value is "+returnValue);
	}

}

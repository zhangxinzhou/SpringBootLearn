package com.example.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.TestService;
import com.example.service.WebService;
import com.example.util.Description;
import com.example.util.UdpGetClientMacAddr;

@Controller
public class WebController {
	
	private final String backIndex="<a href=\"/index\">返回首页</a><br/>";

	@Autowired
	private WebService webService;
	
	@Autowired
	private TestService testService;
	
	@Description("主页")
	@RequestMapping({"/","/index"})
	public String index(ModelMap mm){
		mm.put("result", testService.getRequestMapping(getClass()));
		return "index";
	}
	
	@Description("无内容跳转")
	@RequestMapping("/test")
	public String test(){
		return "test";
	}
	
	@Description("getMoreRequestMapping方法测试")
	@RequestMapping("/toTest")
	public String toTest(ModelMap mm){
		mm.put("result", testService.getMoreRequestMapping(WebController.class));
		return "test";
	}
	
	@Description("getMoreRequestMapping的方法改进,RequestMapping多值处理")
	@RequestMapping("/toTest2")
	public String toTest2(ModelMap mm){
		mm.put("result", testService.getMoreRequestMapping2(WebController.class));
		return "test";
	}
	
	@Description("RequestMapping多个值测试,换一个controller测试")
	@RequestMapping("/RMtest")
	public String RMtest(ModelMap mm){
		mm.put("result", testService.getMoreRequestMapping2(RMtestController.class));
		return "test";
	}
	
	@Description("跳转到cla")
	@RequestMapping("/toCla")
	public String toCla(ModelMap mm){
		String packageName="com.example.web";
		mm.put("result", testService.getClassNamebyPackageName(packageName));
		return "cla";
	}
	
	@Description("根据controller的名称获取该controller")
	@RequestMapping("/getControllerByName")
	public String getControllerByName(ModelMap mm,String claName){
		Class<?> cla;
		try {
			cla = Class.forName(claName);
			mm.put("result", testService.getMoreRequestMapping2(cla));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return "test";
	}
	
	@Description("获取ip")
	@RequestMapping("/getLocalIP")
	@ResponseBody
	public String getLocalIP(){	
		return backIndex+webService.getLocalIP();
	}
	
	@Description("获取本机(服务器的mac)")
	@RequestMapping("/getLocalMac")
	@ResponseBody
	public String getLocalMac1(){
		return webService.getLocalMac1();
	}
	
	@RequestMapping("/getLocalMac2")
	@ResponseBody
	public String getLocalMac2(){
		return backIndex+webService.getLocalMac2();
	}
	

	@RequestMapping("/getRequest")
	@ResponseBody
	public String getRequest(HttpServletRequest request){
		return backIndex+request.toString();
	}
	
	@RequestMapping("/getClientIP1")
	@ResponseBody
	public String getClientIP1(HttpServletRequest request){
		return backIndex+webService.getClientIP1(request);
	}
	
	@RequestMapping("/getClientIP2")
	@ResponseBody
	public String getClientIP2(HttpServletRequest request){
		return backIndex+webService.getClientIP2(request);
	}
	
	@RequestMapping("/getClientIP3")
	@ResponseBody
	public String getClientIP3(HttpServletRequest request){
		return backIndex+webService.getClientIP3(request);
	}
	
	@RequestMapping("/getClientMac1")
	@ResponseBody
	public String getClientMac1(){
		return backIndex+webService.getClientMac1();
	}
	
	@Description("获取浏览器的信息,通过User-Agent")
	@RequestMapping("/getRequestBrowserInfo")
	@ResponseBody
	public String getRequestBrowserInfo(HttpServletRequest request){
		return backIndex+webService.getRequestBrowserInfo(request);
	}
	
	@Description("获取os的信息,通过User-Agent")
	@RequestMapping("/getRequestSystemInfo")
	@ResponseBody
	public String getRequestSystemInfo(HttpServletRequest request){
		return backIndex+webService.getRequestSystemInfo(request);
	}
	
	@Description("没有注解@RequestMapping的方法,测试是否有空指针异常")
	public void testNoAnnotation(){
		System.out.println("没有注解的方法");
	}
	
	@Description("获取Mac的方法无非两种,调用windows的cmd使用命令:arp -a 或者nbtstat -A +ip的方式,无法稳定的获取,一个能打的都没有")
	@RequestMapping("/getMacByIp1")
	@ResponseBody
	public String getMacByIp1(HttpServletRequest request,String ip){
		StringBuffer sb=new StringBuffer();
		ip=ip==null?request.getRemoteAddr():ip;
		sb.append("ip:"+ip);
		sb.append(",mac:"+webService.getMacByIp1(ip));
		return backIndex+sb.toString();
	}
	
	@RequestMapping("/getMacByIp2")
	@ResponseBody
	public String getMacByIp2(HttpServletRequest request,String ip){
		StringBuffer sb=new StringBuffer();
		ip=ip==null?request.getRemoteAddr():ip;
		sb.append("ip:"+ip);
		sb.append(",mac:"+webService.getMacByIp2(ip));
		return backIndex+sb.toString();
	}
	
	@RequestMapping("/getMacByIp3")
	@ResponseBody
	public String getMacByIp3(HttpServletRequest request,String ip){
		StringBuffer sb=new StringBuffer();
		ip=ip==null?request.getRemoteAddr():ip;
		sb.append("ip:"+ip);
		sb.append(",mac:"+webService.getMacByIp3(ip));
		return backIndex+sb.toString();
	}
	
	@RequestMapping("/getMacByIp4")
	@ResponseBody
	public String getMacByIp4(HttpServletRequest request,String ip){
		StringBuffer sb=new StringBuffer();
		ip=ip==null?request.getRemoteAddr():ip;
		sb.append("ip:"+ip);
		sb.append(",mac:"+webService.getMacByIp4(ip));
		return backIndex+sb.toString();
	}
	
	@RequestMapping("/getHost")
	@ResponseBody
	public String getHost(HttpServletRequest request){
		Map<String, Object> map=new HashMap<>();
		map.put("Addr", request.getRemoteAddr());
		map.put("Host", request.getRemoteHost());
		map.put("Port", request.getRemotePort());
		map.put("User", request.getRemoteUser());
		map.put("URI", request.getRequestURI());
		map.put("URL", request.getRequestURL());
		map.put("SessionId", request.getRequestedSessionId());
		return backIndex+map.toString();
	}
	
	@RequestMapping("/getMacByRequest")
	@ResponseBody
	public String getMacByRequest(HttpServletRequest request) throws Exception{
		String sip = request.getHeader("x-forwarded-for");   
		if(sip == null || sip.length() == 0 || "unknown".equalsIgnoreCase(sip)) {   
		    sip = request.getHeader("Proxy-Client-IP");   
		}   
		if(sip == null || sip.length() == 0 || "unknown".equalsIgnoreCase(sip)) {   
		    sip = request.getHeader("WL-Proxy-Client-IP");   
		}   
		if(sip == null || sip.length() == 0 || "unknown".equalsIgnoreCase(sip)) {   
		    sip = request.getRemoteAddr();   
		}   
		UdpGetClientMacAddr umac = new UdpGetClientMacAddr(sip);
		return backIndex+umac.GetRemoteMacAddr();
	}
	
	

}

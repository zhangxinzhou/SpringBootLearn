package com.example.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	@ResponseBody
	public Map<String, Object> Hello(String name){
		Map<String, Object> map=new HashMap<>();
		map.put("name", name!=null?name:"匿名用户");
		map.put("world", "hello");
		map.put("time", new Date());
		return map;
	}
	
	@RequestMapping("/")
	public String test1(){ 
		return "NewFile";
	}

	/*
	 * http://www.jb51.net/article/46462.htm
	 * 服务器端代码，是重点，开始以为，只要客户端通过jsonp就可以直接跨域访问，其实不然，需要服务器端的支持才行
	 */
	@RequestMapping("/jsonpTest")
	public void jsonpTest(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//事先不知道传过来的参数是什么,打印出来看一下 
		Map<String, String[]> map=request.getParameterMap();
		Set<Entry<String, String[]>> keSet=map.entrySet(); 
		for (Entry<String, String[]> entry : keSet) {
			System.out.println("key:"+entry.getKey()+",value:"+Arrays.asList(entry.getValue()));
		}
		String callbackName=request.getParameter("callback");//如果callback为空,那么返回的是:null({"name":"张三","age":28});
		String jsonStr= "{\"name\":\"张三\",\"age\":28}";
		String renderStr=callbackName+"("+jsonStr+");";
		response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().write(renderStr); 
	}
	
	
	//由服务器来做转发,这样就解决跨域的问题
	@RequestMapping("/crossDomain")
	@ResponseBody  //spring boot自动将String转成json,真tm方便
	public String crossDomain(String target){		
        try {
        	URL url=new URL(target);
    		HttpURLConnection huc=(HttpURLConnection)url.openConnection();		
    		huc.connect();
    		BufferedReader br=new BufferedReader(new InputStreamReader(huc.getInputStream()));
    		String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }      
    		br.close();//关闭流
            huc.disconnect();// 断开连接  
            return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "{\"msg\":\"您输入的地址"+target+"不正确\"}";
	}
}

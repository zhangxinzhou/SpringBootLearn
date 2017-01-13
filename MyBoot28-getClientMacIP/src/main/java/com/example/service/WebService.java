package com.example.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class WebService {

	//获取服务器IP地址
	public String getLocalIP(){
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//获取本地(服务器)mac地址1
	public String getLocalMac1(){
		try {
			byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
			StringBuffer sb = new StringBuffer("");
			for(int i=0; i<mac.length; i++) {
				if(i!=0) {
					sb.append("-");
				}
				//字节转换为整数
				int temp = mac[i]&0xff;
				String str = Integer.toHexString(temp);
				if(str.length()==1) {
					sb.append("0"+str);
				}else {
					sb.append(str);
				}
			}
			return sb.toString().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//获取本地(服务器)mac地址2
	public String getLocalMac2(){
		String mac=null;
		try {
			Process pro = Runtime.getRuntime().exec("cmd.exe /c ipconfig/all");
			InputStream is = pro.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String message = br.readLine();
			int index = -1;
			while (message != null) {
				if ((index = message.indexOf("Physical Address")) > 0) {
					mac = message.substring(index + 36).trim();
					break;
				}
				message = br.readLine();
			}
			System.out.println(mac);
			br.close();
			pro.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return mac;
	}
	
	//获取客户端的request
	public Map<String, Object> getRequest(HttpServletRequest request){
		Map<String, Object> result=new HashMap<>();
		result.put("result", request.toString());
		return result;
	}
	
	//获取客户端的IP1
	public String getClientIP1(HttpServletRequest request){
		return request.getRemoteAddr();
	}
	//获取客户端的IP2
	public String getClientIP2(HttpServletRequest request){
		if (request.getHeader("x-forwarded-for") == null) { 
	        return request.getRemoteAddr(); 
	    } 
	    return request.getHeader("x-forwarded-for"); 
	}
	//获取客户端的IP1=3
	public String getClientIP3(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for"); 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	}
	//获取客户端的Mac地址
	public String getClientMac1(){
	    try {
	        Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
	        byte[] mac = null;
	        while (allNetInterfaces.hasMoreElements()) {
	          NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
	          if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
	            continue;
	          } else {
	            mac = netInterface.getHardwareAddress();
	            if (mac != null) {
	              StringBuilder sb = new StringBuilder();
	              for (int i = 0; i < mac.length; i++) {
	                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
	              }
	              if (sb.length() > 0) {
	                return sb.toString();
	              }
	            }
	          }
	        }
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	      return "";	
	}
	
	// 获取来访者的浏览器版本
	public String getRequestBrowserInfo(HttpServletRequest request) {
		String browserVersion = null;
		String header = request.getHeader("user-agent");
		if (header == null || header.equals("")) {
			return "";
		}
		if (header.indexOf("MSIE") > 0) {
			browserVersion = "IE";
		} else if (header.indexOf("Firefox") > 0) {
			browserVersion = "Firefox";
		} else if (header.indexOf("Chrome") > 0) {
			browserVersion = "Chrome";
		} else if (header.indexOf("Safari") > 0) {
			browserVersion = "Safari";
		} else if (header.indexOf("Camino") > 0) {
			browserVersion = "Camino";
		} else if (header.indexOf("Konqueror") > 0) {
			browserVersion = "Konqueror";
		}
		return browserVersion;
	}
	
	//获取系统版本的信息
	public String getRequestSystemInfo(HttpServletRequest request) {
		String systenInfo = null;
		String header = request.getHeader("user-agent");
		if (header == null || header.equals("")) {
			return "";
		}
		// 得到用户的操作系统
		if (header.indexOf("NT 6.0") > 0) {
			systenInfo = "Windows Vista/Server 2008";
		} else if (header.indexOf("NT 5.2") > 0) {
			systenInfo = "Windows Server 2003";
		} else if (header.indexOf("NT 5.1") > 0) {
			systenInfo = "Windows XP";
		} else if (header.indexOf("NT 6.0") > 0) {
			systenInfo = "Windows Vista";
		} else if (header.indexOf("NT 6.1") > 0) {
			systenInfo = "Windows 7";
		} else if (header.indexOf("NT 6.2") > 0) {
			systenInfo = "Windows Slate";
		} else if (header.indexOf("NT 6.3") > 0) {
			systenInfo = "Windows 9";
		} else if (header.indexOf("NT 5") > 0) {
			systenInfo = "Windows 2000";
		} else if (header.indexOf("NT 4") > 0) {
			systenInfo = "Windows NT4";
		} else if (header.indexOf("Me") > 0) {
			systenInfo = "Windows Me";
		} else if (header.indexOf("98") > 0) {
			systenInfo = "Windows 98";
		} else if (header.indexOf("95") > 0) {
			systenInfo = "Windows 95";
		} else if (header.indexOf("Mac") > 0) {
			systenInfo = "Mac";
		} else if (header.indexOf("Unix") > 0) {
			systenInfo = "UNIX";
		} else if (header.indexOf("Linux") > 0) {
			systenInfo = "Linux";
		} else if (header.indexOf("SunOS") > 0) {
			systenInfo = "SunOS";
		}
		return systenInfo;
	}
	
	
	
	//获取mac地址1
	public String getMacByIp1(String ip){
		try {
			String str="";
			String macAddress="";
			final String LOOPBACK_ADDRESS="127.0.0.1";
			if(LOOPBACK_ADDRESS.equals(ip)){
				return getLocalMac1();
			}else{
				Process p=Runtime.getRuntime().exec("nbtstat -A " + ip);
				InputStreamReader ir = new InputStreamReader(p.getInputStream());
				BufferedReader br = new BufferedReader(ir);
		        while ((str = br.readLine()) != null) {
		            if(str.indexOf("MAC")>1){
		              macAddress = str.substring(str.indexOf("MAC")+9, str.length());
		              macAddress = macAddress.trim();
		              break;
		            }
		         }
		        p.destroy();
		        br.close();
		        ir.close();
		        return macAddress.toUpperCase();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//获取mac地址2,与1一毛一样
	public String getMacByIp2(String ip){
		String str=null;
		String macAddress="";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (; true;) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC") > 1) {
						macAddress = str.substring(str.indexOf("MAC")+9, str.length()).trim();
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return macAddress.toUpperCase();
	}

	//获取mac地址3
	public String getMacByIp3(String ip){
		try {
			Process p = Runtime.getRuntime().exec("arp -a");
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			p.waitFor();
			boolean flag = true;
			while (flag) {
				String str = input.readLine();
				if (str != null) {
					if (str.indexOf(ip) > 1&&str.length()>=41) {
						return str.substring(24, 41).toUpperCase();
					}
				} else
					flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "can not find";
	}
	
	//获取mac地址4
	public String getMacByIp4(String ip) {
		String macAddress = "";
		macAddress = getMacInWindows(ip).trim();
		if (macAddress == null || "".equals(macAddress)) {
			macAddress = getMacInLinux(ip).trim();
		}
		return macAddress.toUpperCase();
	}

	/**
	 * 
	 * @param cmd   
	 * @return
	 */
	private String callCmd(String[] cmd) {
		String result = "";
		String line = "";
		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStreamReader is = new InputStreamReader(proc.getInputStream());
			BufferedReader br = new BufferedReader(is);
			while ((line = br.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 *        
	 * @param cmd       第一个命令
	 * @param another   第二个命令
	 * @return          第二个命令的执行结果
	 */
	private String callCmd(String[] cmd, String[] another) {
		String result = "";
		String line = "";
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd);
			proc.waitFor(); // 已经执行完第一个命令，准备执行第二个命令
			proc = rt.exec(another);
			InputStreamReader is = new InputStreamReader(proc.getInputStream());
			BufferedReader br = new BufferedReader(is);
			while ((line = br.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 *            
	 * @param ip              目标ip,一般在局域网内
	 * @param sourceString    命令处理的结果字符串
	 * @param macSeparator    mac分隔符号
	 * @return                mac地址,用上面的分隔符号表示
	 */
	private String filterMacAddress(final String ip, final String sourceString, final String macSeparator) {
		String result = "";
		String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(sourceString);
		while (matcher.find()) {
			result = matcher.group(1);
			if (sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) {
				break; // 如果有多个IP,只匹配本IP对应的Mac.
			}
		}
		return result;
	}
	
	/**
	 *            
	 * @param ip    目标ip
	 * @return      mac地址
	 */
	private String getMacInWindows(final String ip) {
		String result = "";
		String[] cmd = { "cmd", "/c", "ping " + ip };
		String[] another = { "cmd", "/c", "arp -a" };
		String cmdResult = callCmd(cmd, another);
		result = filterMacAddress(ip, cmdResult, "-");
		return result;
	}
	
	/**
	 * 
	 * @param ip   目标ip
	 * @return     mac地址
	 */
	private String getMacInLinux(final String ip) {
		String result = "";
		String[] cmd = { "/bin/sh", "-c", "ping " + ip + " -c 2 && arp -a" };
		String cmdResult = callCmd(cmd);
		result = filterMacAddress(ip, cmdResult, ":");

		return result;
	}
	
	
	
}

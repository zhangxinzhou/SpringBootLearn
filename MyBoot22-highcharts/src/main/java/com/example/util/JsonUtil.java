package com.example.util;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtil {

	/**
	 *                  将json字符串转化为实体类
	 * @param jsonStr   json字符串
	 * @param obj       实体类型
	 * @return          返回实体类
	 */
	public static <T> T jsonStrToObj(String jsonStr,Class<T> obj){
		if(jsonStr==null){return null;}
		T t=null;
		try {
			ObjectMapper om=new ObjectMapper();
			t=om.readValue(jsonStr, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	/**
	 *                  将实体类转换成json(其实用@responseBody即可)
	 * @param obj       待转换的obj
	 * @return          返回转换到的json对象
	 */
	public static <T> JSONObject objToJsonObj(T obj){
		ObjectMapper om=new ObjectMapper();
		String jsonStr="";
		try {
			jsonStr=om.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONObject(jsonStr);
	} 
}

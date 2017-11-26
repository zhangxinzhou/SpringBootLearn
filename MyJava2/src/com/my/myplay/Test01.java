package com.my.myplay;

import java.util.HashMap;
import java.util.Map;

public class Test01 {

	
	public static void main(String[] args) {
		
		
		String str="vdhejdjdbdbjd";
		String[] arr=str.split("");
		Map<String, Integer> map=new HashMap<>();
		for (String string : arr) {
			Integer i= map.get(string);
			if(i==null) {
				i=0;
			}
			i++;
			map.put(string, i);
		}
		System.out.println(map);
	}
}

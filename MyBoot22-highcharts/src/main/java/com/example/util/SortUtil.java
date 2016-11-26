package com.example.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class SortUtil {
	
	public static Sort getPage(String ... str){
		Direction d=null;
		List<String> l=new ArrayList<>();
		if(str!=null&&str.length>0){
			for (String s : str) {
				if(s.equalsIgnoreCase("asc")||s.equalsIgnoreCase("desc")){
					d=Direction.valueOf(s.toUpperCase());
				}else{
					l.add(s);
				}
			}
		}
		d=d==null?Direction.ASC:d;
		Sort p=new Sort(d, l);
		System.out.println(d);
		System.out.println(l);
		return p;
	}
}

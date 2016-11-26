package com.example.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class SortUtil {
	
	public static Sort getPage(String ... str){
		Direction d=null;
		List<String> l=Arrays.asList(str);
		if(str!=null&&str.length>0){
			for (String s : l) {
				if(s.equalsIgnoreCase("asc")){
					d=Direction.ASC;
					l.remove(s);
					break;
				}else if(s.equalsIgnoreCase("desc")){
					d=Direction.DESC;
					l.remove(s);
					break;
				}
			}
		}
		Sort p=new Sort(d!=null?d:Direction.ASC, l);
		return p;
	}
}

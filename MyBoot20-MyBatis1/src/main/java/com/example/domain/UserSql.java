package com.example.domain;

import java.util.Map;

public class UserSql {

	
	//拼接sql,fucking麻烦,还不如xml
	public String getSql(Map<String, Object> params){	
		StringBuffer sql=new StringBuffer("select * from users where 1=1 ");
		if(params.get("name")!=null){
			sql.append(" and name='"+params.get("name")+"'");
		}
		if(params.get("age")!=null){
			sql.append(" and age="+params.get("age"));
		}
		System.out.println(sql.toString());
		return sql.toString();
	}
}

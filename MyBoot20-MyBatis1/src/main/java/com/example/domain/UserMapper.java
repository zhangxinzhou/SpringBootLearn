package com.example.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface UserMapper {
	


	//单个
	@Select("select * from users where name=#{name}")
	Users findByName(@Param("name") String name);
	
	//多个
	@Select("select * from users where name=#{name}")
	List<Users> findAllByName(@Param("name") String name);
	
	
	@Insert("insert into users(id,name,age) values(#{id},#{name},#{age})")
	int insert(@Param("id") Integer id,@Param("name") String name,@Param("age") Integer age);
	
	//map做参数,返回List<Map<String, Object>>
	@Select("select * from users where name=#{name,jdbcType=VARCHAR} and age=#{age,jdbcType=INTEGER}")
	List<Map<String, Object>> getMap(Map<String, Object> params);
	
	@SelectProvider(method="getSql",type=UserSql.class)//那个类的那个方法
	@ResultType(value=HashMap.class)                   //返回什么类型
	List<Map<String, Object>> getMap2(Map<String, Object> params);
	
}

package com.example.domain;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;




@Mapper   //要在application.properties中设置mybatis.mapper-locations,否则会报错Invalid bound statement (not found)
public interface XmlMapper {

	List<Map<String, Object>> getXmlUser(@Param("name") String name,@Param("age") Integer age);
}

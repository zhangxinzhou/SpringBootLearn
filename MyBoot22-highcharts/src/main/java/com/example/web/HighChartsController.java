package com.example.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.HighChartsService;

@Controller
public class HighChartsController {

	@Autowired
	HighChartsService hcService;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/demo1")
	public String demo1(){
		return "highcharts/hc1";
	}
	
	@RequestMapping("/demo2")
	public String demo2(){
		return "highcharts/hc2";
	}
	
	@RequestMapping("/demo3")
	public String demo3(ModelMap map){
		map.put("hc", hcService.getMemoryData());
		return "highcharts/hc3";
	}
	
	@RequestMapping("/demo4")
	public String demo4(ModelMap map){
		map.put("hc", hcService.getEntityMemoryData());
		return "highcharts/hc4";
	}
	
	@RequestMapping("/demo5")
	public String demo5(ModelMap map){
		//饼图需要的数据,第一列是name第二列是y,不能变,数组来源数据库
		String sql="select occupation name,COUNT(*) y from users group by occupation";
		map.put("hc", jdbcTemplate.queryForList(sql));
		return "highcharts/hc5";
	}
	
	@RequestMapping("/demo2/getAjaxData.do")
	@ResponseBody
	public Map<String, Object> getAjaxData(){
		return hcService.getMemoryData();
	}
}

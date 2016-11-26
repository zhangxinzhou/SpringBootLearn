package com.example.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.domain.HC;
import com.example.service.HighChartsService;

@Service
public class HighChartsServiceImpl implements HighChartsService {

	@Override
	public Map<String, Object> getMemoryData() {
		Map<String, Object> md=new HashMap<>();
		
		Map<String, Object> title=new HashMap<>();
		Map<String, Object> xAxis=new HashMap<>();
		List<Map<String, Object>> series=new ArrayList<>();
		
		//标题
		String text="水果(内存数据)";
		title.put("text", text);
		
		//x轴
		List<String> categories=Arrays.asList(new String[]{"Jan","Feb","Mar"});		
		xAxis.put("categories", categories);
		
		//数据
		Map<String, Object> dataLabels=new HashMap<>();
		boolean enabled=true;
		dataLabels.put("enabled", enabled);
		Map<String, Object> map1=new HashMap<>();
		Map<String, Object> map2=new HashMap<>();
		Map<String, Object> map3=new HashMap<>();	
		map1.put("name", "苹果");
		map1.put("data", Arrays.asList(new Integer[]{1,2,3}));
		map1.put("dataLabels", dataLabels);
		map2.put("name", "橘子");
		map2.put("data", Arrays.asList(new Integer[]{3,2,1}));
		map2.put("dataLabels", dataLabels);
		map3.put("name", "香蕉");
		map3.put("data", Arrays.asList(new Integer[]{2,5,5}));
		map3.put("dataLabels", dataLabels);
		series.add(map1);
		series.add(map2);
		series.add(map3);
		
		md.put("title", title);
		md.put("xAxis", xAxis);
		md.put("series", series);
		return md;
	}

	@Override
	public HC getEntityMemoryData() {
			
		Map<String, Object> chart=new HashMap<>();
		Map<String, Object> title=new HashMap<>();
		Map<String, Object> yAxis=new HashMap<>();
		List<Map<String, Object>> series=new ArrayList<>();
		
		chart.put("type", "column");
		title.put("text", "水果(实体类)");
		yAxis.put("categories", Arrays.asList(new String[]{"Jan","Feb","Mar"}));
		
		Map<String, Object> dataLabels=new HashMap<>();
		boolean enabled=true;
		dataLabels.put("enabled", enabled);
		Map<String, Object> map1=new HashMap<>();
		Map<String, Object> map2=new HashMap<>();
		Map<String, Object> map3=new HashMap<>();	
		map1.put("name", "苹果");
		map1.put("data", Arrays.asList(new Integer[]{1,2,3}));
		map1.put("dataLabels", dataLabels);
		map2.put("name", "橘子");
		map2.put("data", Arrays.asList(new Integer[]{3,2,1}));
		map2.put("dataLabels", dataLabels);
		map3.put("name", "香蕉");
		map3.put("data", Arrays.asList(new Integer[]{2,5,5}));
		map3.put("dataLabels", dataLabels);
		series.add(map1);
		series.add(map2);
		series.add(map3);
		

		
		HC hc=new HC(chart, title, yAxis, series);
		return hc;
	}

}

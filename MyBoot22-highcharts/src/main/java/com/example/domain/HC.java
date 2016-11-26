package com.example.domain;

import java.util.List;
import java.util.Map;



public class HC {

	private Map<String, Object> chart;
	private Map<String, Object> title;
	private Map<String, Object> yAxis;
	private List<Map<String, Object>>  series;
	
	
	
	public HC() {
		super();
	}
	
	public HC(Map<String, Object> chart, Map<String, Object> title, Map<String, Object> yAxis,
			List<Map<String, Object>> series) {
		super();
		this.chart = chart;
		this.title = title;
		this.yAxis = yAxis;
		this.series = series;
	}

	public Map<String, Object> getChart() {
		return chart;
	}
	public void setChart(Map<String, Object> chart) {
		this.chart = chart;
	}
	public Map<String, Object> getTitle() {
		return title;
	}
	public void setTitle(Map<String, Object> title) {
		this.title = title;
	}
	public Map<String, Object> getyAxis() {
		return yAxis;
	}
	public void setyAxis(Map<String, Object> yAxis) {
		this.yAxis = yAxis;
	}
	public List<Map<String, Object>> getSeries() {
		return series;
	}
	public void setSeries(List<Map<String, Object>> series) {
		this.series = series;
	}
	@Override
	public String toString() {
		return "HC [chart=" + chart + ", title=" + title + ", yAxis=" + yAxis + ", series=" + series + "]";
	}
	
	
}

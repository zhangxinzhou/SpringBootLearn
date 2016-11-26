package com.example.service;

import java.util.Map;

import com.example.domain.HC;

public interface HighChartsService {

	Map<String, Object> getMemoryData();
	
	HC getEntityMemoryData();
}

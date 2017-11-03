package com.my.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class CallableTest1 implements Callable<List<Map<String, Object>>>{
	
	private Map<String, Object> params;
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public List<Map<String, Object>> call() throws Exception {
		List<Map<String, Object>> result=new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map=new HashMap<>();
			map.put("name", this.getClass().getSimpleName());
			map.put("k"+i, "v"+i);
			result.add(map);
		}
		Thread.sleep(2000);
		return result;
	}


}

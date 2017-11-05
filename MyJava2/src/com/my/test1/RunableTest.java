package com.my.test1;

import java.util.Map;

public class RunableTest implements Runnable{
	
	private Map<String, Object> params;
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}


	@Override
	public void run() {
		try {
			System.out.println(getParams());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}

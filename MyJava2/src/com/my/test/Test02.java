package com.my.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test02 {

	private final static int THREAD_POOL_SIZE=10;
	private final static int index=0;
	
	public static void main(String[] args) {
		List<Map<String, Object>> list=getList();
		ExecutorService  service=Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		while(list!=null&&!list.isEmpty()){
			Map<String, Object> params=list.get(index);
			list.remove(0);
			RunableTest task=new RunableTest();
			task.setParams(params);
			service.execute(task);
		}
		service.shutdown();//释放资源
	}
	
	public static List<Map<String, Object>> getList(){
		List<Map<String, Object>> result=new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			Map<String, Object> map=new HashMap<>();
			map.put("k"+i, "v"+i);
			result.add(map);
		}
		return result;
	}
}

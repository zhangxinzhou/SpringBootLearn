package com.my.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test03 {

	
	private final static int THREAD_POOL_SIZE=10;
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Long start=System.currentTimeMillis();
		tttt().forEach((k,v)->{
			System.out.println(k+":"+v);
		});
		System.out.println("spend:"+(System.currentTimeMillis()-start)+"ms");
	}
	
	private static Map<String, Object> tttt() throws InterruptedException, ExecutionException{
		Map<String, Object> result=new HashMap<>();
		ExecutorService  service=Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		CallableTest1 task1=new CallableTest1();
		CallableTest2 task2=new CallableTest2();
		
		Future<List<Map<String, Object>>> future1 = service.submit(task1);
		Future<List<Map<String, Object>>> future2 = service.submit(task2);
		result.put("r1", future1.get());
		result.put("r2", future2.get());
		service.shutdown();   
		return result;
	}
	

}

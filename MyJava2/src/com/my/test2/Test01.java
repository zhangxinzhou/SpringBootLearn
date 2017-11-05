package com.my.test2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.my.test1.CallableTest1;
import com.my.test1.CallableTest2;

public class Test01 {

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Long start=System.currentTimeMillis();
		tttt().forEach((k,v)->{
			System.out.println(k+":"+v);
		});
		System.out.println("spend:"+(System.currentTimeMillis()-start)+"ms");
	}
	
	private static Map<String, Object> tttt() throws InterruptedException, ExecutionException{
		Map<String, Object> result=new HashMap<>();
		CallableTest1 task1=new CallableTest1();
		CallableTest2 task2=new CallableTest2();
		
		Future<List<Map<String, Object>>> future1 = StaticThreadPool.service.submit(task1);
		Future<List<Map<String, Object>>> future2 = StaticThreadPool.service.submit(task2);
		result.put("r1", future1.get());
		result.put("r2", future2.get());
		//StaticThreadPool.service.shutdown();  //释放资源 
		return result;
	}
}

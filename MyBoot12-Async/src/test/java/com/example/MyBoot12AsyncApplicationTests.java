package com.example;

import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBoot12AsyncApplicationTests {

	@Autowired
	private Task task;
	
	@Autowired
	private AsyncTask asyncTask;
	
	@Autowired
	private AsyncTask2 asyncTask2;
	//@Test
	public void contextLoads() throws Exception {
		task.doTaskOne();
		task.doTaskTwo();
		task.doTaskThree();
	}
	
	/* 可能出现:
    1.没有任何任务相关的输出
    2.有部分任务相关的输出
    3.乱序的任务相关的输出
            原因是目前doTaskOne、doTaskTwo、doTaskThree三个函数的时候已经是异步执行了。
            主程序在异步调用之后，主程序并不会理会这三个函数是否执行完成了，由于没有其他需要执行的内容，
            所以程序就自动结束了，导致了不完整或是没有输出任务相关内容的情况。*/
	//@Test
	public void AsyncTaskTest() throws Exception{
		asyncTask.doTaskOne();
		asyncTask.doTaskTwo();
		asyncTask.doTaskThree();
	}
	
	@Test
	public void AsyncTaskTest2() throws Exception{
		long start=System.currentTimeMillis();
		
		Future<String> async2task1 = asyncTask2.doTaskOne();
		Future<String> async2task2 = asyncTask2.doTaskOne();
		Future<String> async2task3 = asyncTask2.doTaskOne();
		
		while(true){
			if(async2task1.isDone()&&async2task2.isDone()&&async2task3.isDone()){
				//三个任务都调用完成,退出循环等待
				break;
			}
			Thread.sleep(1000);
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}

}

package com.my.test2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StaticThreadPool {

	private final static int THREAD_POOL_SIZE=10;
	//是应该初始化一个线程池,挂在哪里呢,还是每次用的时候new一个,用完释放资源呢
	public final static ExecutorService  service=Executors.newFixedThreadPool(THREAD_POOL_SIZE);
}

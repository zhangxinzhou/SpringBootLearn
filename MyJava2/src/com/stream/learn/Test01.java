package com.stream.learn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class Test01 {

	public static void main(String[] args) {		
		List<Map<String, Object>> lists=new ArrayList<>();
		Map<String, Object> map1=new HashMap<>();
		Map<String, Object> map2=new HashMap<>();
		lists.add(map1);
		lists.add(map2);
		map1.put("k1", 1);
		map1.put("k2", 1.0D);
		map2.put("k1", 2);
		map2.put("k2", 2.0D);
		//第一个函数生成一个新的ArrayList实例；
		//第二个函数接受两个参数，第一个是前面生成的ArrayList对象，二个是stream中包含的元素，函数体就是把stream中的元素加入ArrayList对象中。第二个函数被反复调用直到原stream的元素被消费完毕；
		//第三个函数也是接受两个参数，这两个都是ArrayList类型的，函数体就是把第二个ArrayList全部加入到第一个中；
		List<Integer> l1=lists.stream().collect(
						() -> new ArrayList<Integer>(),
						(list, item) -> list.add((Integer)item.get("k1")),
						(list1, list2) -> list1.addAll(list2)
				);
		
		//创建一个新的容器，制定要存放的类型
		Supplier<ArrayList<Integer>> supplier=new Supplier<ArrayList<Integer>>() {
			@Override
			public ArrayList<Integer> get() {
				return new ArrayList<Integer>();
			}
		};
		BiConsumer<ArrayList<Integer>,Map<String,Object>> accumulator=new BiConsumer<ArrayList<Integer>, Map<String,Object>>() {
			@Override
			public void accept(ArrayList<Integer> list, Map<String, Object> item) {
				list.add((Integer)item.get("k1"));						
			}			
		};
		BiConsumer<ArrayList<Integer>,ArrayList<Integer>> combiner=new BiConsumer<ArrayList<Integer>, ArrayList<Integer>>() {			
			@Override
			public void accept(ArrayList<Integer> list1, ArrayList<Integer> list2) {
				list1.addAll(list2);			
			}
		};
		List<Integer> l2=lists.stream().collect(supplier, accumulator, combiner);
		System.out.println(lists);
		System.out.println(l1);
		System.out.println(l2);
	}
}

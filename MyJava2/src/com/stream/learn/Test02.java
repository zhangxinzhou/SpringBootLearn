package com.stream.learn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;


/**
 * https://segmentfault.com/a/1190000010166897
 * Collector<T, A, R>接口的方法，一共5个
 * Supplier<A> supplier()
 * BiConsumer<A, T> accumulator()
 * BinaryOperator<A> combiner()
 * Function<A, R> finisher()
 * Set<Characteristics> characteristics()
 * 方法中有泛型，所以要先要介绍哈Collector中的三个泛型T, A, R
 * T：stream在调用collect方法收集前的数据类型
 * A：A是T的累加器，遍历T的时候，会把T按照一定的方式添加到A中，换句话说就是把一些T通过一种方式变成A
 * R：R可以看成是A的累加器，是最终的结果，是把A汇聚之后的数据类型，换句话说就是把一些A通过一种方式变成R
 * @author zxz
 *
 */
public class Test02 {

	
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
		
		//Collector<T, A, R>
		//T：stream在调用collect方法收集前的数据类型
		//A：A是T的累加器，遍历T的时候，会把T按照一定的方式添加到A中，换句话说就是把一些T通过一种方式变成A
		//R：R可以看成是A的累加器，是最终的结果，是把A汇聚之后的数据类型，换句话说就是把一些A通过一种方式变成R
		//Collector<T, ?, List<T>> toList() {
		//		 return new CollectorImpl<>((Supplier<List<T>>) ArrayList::new, List::add,
		//		                            (left, right) -> { left.addAll(right); return left; },
		//		                             CH_ID);
		//(Supplier<List<T>>) ArrayList::new 对应Supplier<A> supplier()第一个方法
		//List::add 对应BiConsumer<A, T> accumulator()第二个方法
		//(left, right) -> { left.addAll(right); return left; }对应BinaryOperator<A> combiner()第三个方法
		
		Collector<Map<String, Object>, List<Map<String, Object>> , List<Integer>> collector=new Collector<Map<String,Object>, List<Map<String,Object>>, List<Integer>>() {

			@Override
			public Supplier<List<Map<String, Object>>> supplier() {
				return ArrayList::new;
			}

			@Override
			public BiConsumer<List<Map<String, Object>>, Map<String, Object>> accumulator() {
				return (left, right) -> {
					System.out.println("left:"+left);
					System.out.println("left:"+right);
				};
			}

			@Override
			public BinaryOperator<List<Map<String, Object>>> combiner() {
				return (left, right) -> { 
					//left.addAll(right); 
					return left; 
					};
			}

			@Override
			public Function<List<Map<String, Object>>, List<Integer>> finisher() {
				return null;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return null;
			}
		};
		
		List<Integer> l=lists.stream().collect(collector);
		System.out.println(l);
	}
}

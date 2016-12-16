package com.example.util;


import java.io.Serializable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;




public class EasyUIpageUtil {
	
	
	/**
	 *                 getSort
	 * @param sort     排序的列
	 * @param order    升序,降序
	 * @return         返回sort
	 */
	private static Sort getSort(String sort,String order){
		if(sort!=null&&order!=null){
			Direction d=Direction.valueOf(order.toUpperCase());
			return new Sort(d, sort);
		}
		return null;
	}
	
	/**
	 *               获取pageRequest
	 * @param page   页码(第n页)
	 * @param size   没页记录数
	 * @return
	 */
	private static PageRequest getPageRequest(Integer page,Integer size){
		if(page!=null&&size!=null){
			return new PageRequest(page, size);
			
		}
		return null;
	}
	
	/**
	 *                 获取排序的pageRequest
	 * @param page     页码(第n页)
	 * @param size     没页记录数
	 * @param sort     排序规则
	 * @return
	 */
	private static PageRequest getPageRequest(Integer page,Integer size,Sort sort){
		if(page!=null&&size!=null&&sort!=null){
			return new PageRequest(page, size, sort);
		}
		return null;
	}
	
	/**
	 *                获取排序的pageRequest
	 * @param page    页码(第n页)
	 * @param size    没页记录数
	 * @param sort    排序的字段
	 * @param order   asc|desc
	 * @return
	 */
	private static PageRequest getPageRequest(Integer page,Integer size,String sort,String order){
		Sort s=getSort(sort, order);
		if(s!=null){			
			return getPageRequest(page, size,s);
		}else{
			return getPageRequest(page, size);
		}
	}
	
	/**
	 * @param t            实体类查询条件
	 * @param page         页码(第n页)
	 * @param rows         每页大小
	 * @param sort         排序的字段
	 * @param order        asc|desc
	 * @param jpa          需要注入的jpa
	 * @return
	 * 
	 * 应对情况     1,不分页,不排序.       jpa.findAll(example)   
	 *        2,不分页,但排序.       jpa.findAll(sort)
	 *        3,仅分页,不排序.       jpa.findAll(example, new PageRequest(page, size))
	 *        4,且分页,且排序.       jpa.findAll(example, new PageRequest(page, size, sort);
	 *        
	 * 解决思路:先判断是否分页,再判断是否排序.
	 * 其他:
	 * T         实体类查询条件
	 * rows      easyui参数:每页大小,Size
	 * page      easyui参数:页码(第n页),page
	 * sort      easyui参数:待排序字段
	 * order     easyui参数:asc|desc
	 */
	public static <T> EasyUIpage<T> getPage(T t,Integer page,Integer rows, String sort, String order,
		JpaRepository<T,? extends Serializable> jpa){
		Example<T> e=Example.of(t);//注意,t不能为空,否则报错,所以调用这个方法前要判断if(t==null)t=new T();(泛型无法实例化,在外层做)
		PageRequest pr=getPageRequest(page, rows);
		Sort s=getSort(sort, order);
		if(pr!=null){
			//分页+排序+条件查询
			return new EasyUIpage<>(jpa.findAll(e, getPageRequest(page, rows, sort, order)));
		}else{
			if(s!=null){
				//不分页+排序+条件查询
				return new EasyUIpage<>(jpa.findAll(e, s));				
			}else{
				//不分页+不排序+条件查询
				return new EasyUIpage<>(jpa.findAll(e));
			} 
		}
	}
	
}

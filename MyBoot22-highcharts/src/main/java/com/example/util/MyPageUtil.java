package com.example.util;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.example.dao.UserRepository;
import com.example.domain.Users;

@Component
public class MyPageUtil<T> {
		

	@Autowired
	private UserRepository ur;
	
	
	//局限性:每个要分页的controller都要注入这个mypatautil,限定了id字段必须为long类型
	@Autowired
	private JpaRepository<T, Long> jpa;
	
	/**
	 *                 getSort
	 * @param sort     排序的列
	 * @param order    升序,降序
	 * @return         返回sort
	 */
	private Sort getSort(String sort,String order){
		if(sort!=null&&order!=null){
			Direction d=Direction.valueOf(order.toUpperCase());
			return new Sort(d, sort);
		}
		return null;
	}
	
	/**
	 *                 getPageRequest
	 * @param offset   分页查询第一条记录数
	 * @param limit    分页查询每页大小
	 * @return         返回PageRequest
	 */
	private PageRequest getPageRequest(Integer offset,Integer limit){
		if(offset!=null&&limit!=null){
			return new PageRequest(offset/limit, limit);
		}
		return null;
	}
	
	/** 
	 *                  getPageRequest
	 * @param offset    分页查询第一条记录数
	 * @param limit     分页查询每页大小
	 * @param sort      排序方案
	 * @return          返回PageRequest
	 */
	private PageRequest getPageRequest(Integer offset,Integer limit,Sort sort){
		if(offset!=null&&limit!=null&&sort!=null){
			return new PageRequest(offset/limit, limit, sort);
		}
		return null;
	}

	/**
	 *                  getPageRequest
	 * @param sort      排序的列
	 * @param order     升序,降序
	 * @param offset    分页查询第一条记录数
	 * @param limit     分页查询每页大小
	 * @return          返回PageRequest
	 */
	private PageRequest getPageRequest(String sort,String order,Integer offset,Integer limit){
		Sort s=getSort(sort, order);
		if(s!=null){			
			return getPageRequest(offset, limit,s);
		}else{
			return getPageRequest(offset, limit);
		}
	}
	
	/**
	 *                     getPage
	 * @param e            findAll所需参数,查询例子
	 * @param sort         排序的列
	 * @param order        升序,降序
	 * @param offset       分页查询第一条记录数
	 * @param limit        分页查询每页大小
	 * @return             返回Page<T>
	 * 应对情况     1,不分页,不排序.       ur.findAll(example)   
	 *        2,不分页,但排序.       ur.findAll(sort)
	 *        3,仅分页,不排序.       ur.findAll(example, new PageRequest(page, size))
	 *        4,且分页,且排序.       ur.findAll(example, new PageRequest(page, size, sort);
	 *        
	 * 解决思路:先判断是否分页,再判断是否排序.
	 */
	public MyPage<Users> getPage(String search,String sort,String order,Integer offset,Integer limit){
		Users u=new Users();
		Example<Users> e=Example.of(u);
		PageRequest pr=getPageRequest(offset, limit);
		Sort s=getSort(sort, order);
		if(pr!=null){
			return new MyPage<>(ur.findAll(e, getPageRequest(sort, order, offset, limit)));
		}else{
			if(s!=null){
				return new MyPage<>(ur.findAll(e, s));				
			}else{
				return new MyPage<>(ur.findAll(e));
			}
		}
	}
	//扩展上方法
	public MyPage<Users> getPage(String search,String sort,String order,Integer offset,Integer limit,String filter){
		Users u=JsonUtil.jsonStrToObj(filter, Users.class);
		if(u==null){
			u=new Users();
		}
		Example<Users> e=Example.of(u);
		PageRequest pr=getPageRequest(offset, limit);
		Sort s=getSort(sort, order);
		if(pr!=null){
			return new MyPage<>(ur.findAll(e, getPageRequest(sort, order, offset, limit)));
		}else{
			if(s!=null){
				return new MyPage<>(ur.findAll(e, s));				
			}else{
				return new MyPage<>(ur.findAll(e));
			}
		}
	}
	
	/**
	 * 
	 *                     getPage
	 * @param e            findAll所需参数,查询例子
	 * @param sort         排序的列
	 * @param order        升序,降序
	 * @param offset       分页查询第一条记录数
	 * @param limit        分页查询每页大小
	 * @return             返回Page<T>
	 */
	//泛型多条件分页排序查询
	public MyPage<T> getPage(T t,String sort,String order,Integer offset,Integer limit){
		Example<T> e=Example.of(t);
		PageRequest pr=getPageRequest(offset, limit);
		Sort s=getSort(sort, order);
		if(pr!=null){
			return new MyPage<>(jpa.findAll(e, getPageRequest(sort, order, offset, limit)));
		}else{
			if(s!=null){
				return new MyPage<>(jpa.findAll(e, s));				
			}else{
				return new MyPage<>(jpa.findAll(e));
			}
		}
	}
	
	public MyPage<T> getPage(T t,String sort,String order,Integer offset,Integer limit,String filter){
		Example<T> e=Example.of(t);
		PageRequest pr=getPageRequest(offset, limit);
		Sort s=getSort(sort, order);
		if(pr!=null){
			return new MyPage<>(jpa.findAll(e, getPageRequest(sort, order, offset, limit)));
		}else{
			if(s!=null){
				return new MyPage<>(jpa.findAll(e, s));				
			}else{
				return new MyPage<>(jpa.findAll(e));
			}
		}
	}
}

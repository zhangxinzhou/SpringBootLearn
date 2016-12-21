package com.example;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.test.context.junit4.SpringRunner;



/**
 *存储过程的几种情况
 *	1.exec pro_test @a,@b,@c output 其中@c为输出                                                                            
 *	2.exec pro_test 其中存储过程以return的形式返回                                                                                    
 *	3.exec pro_test 其中存储过程为begin select * from table end形式     
 *  JdbcProducureTest03-06演示了jdbc.execute(sql,new CallableStatementCallback<T>)的用法
 *  JdbcProducureTest13-16演示了jdbc.call()的用法,都能实现着几种情况
 * @author user
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTest {
	private Logger log=Logger.getLogger(getClass());
	
	@Autowired//查询用query,增删改用update
	private JdbcTemplate jdbc;
	
	
	//@Test
	public void JdbcSqlTest01(){//返回Map<String, Object>,没有数据类型转换几乎不会出错
		String sql="select * from student";
		List<Map<String, Object>> stulist=jdbc.queryForList(sql);
		for (Map<String, Object> stu : stulist) {
			log.info("JdbcSqlTest01:"+stu);
		}	
	}
	
	//@Test
	public void JdbcSqlTest02(){//统计的方法
		String sql="select count(*) from student";
		int x=jdbc.queryForObject(sql, Integer.class);
		log.info("JdbcSqlTest02:"+x);
	}

	/**
	 * jdbc.queryForList(sql, elementType)的用法,elementType指的并不是entity(实体类)而是Integer,String这样的类
	 */
	//@Test
	public void JdbcSqlTest03(){
		//org.springframework.jdbc.IncorrectResultSetColumnCountException: 
		//Incorrect column count: expected 1, actual 5
		//如下语句,会报这个错误:预期得到1但实际上查到了得到5(student这个表就是有五列)
		//推测,jdbc.queryForObject(sql, requiredType),这个requiredType只能是integer,String这些基本类型
		//也就是说,这个方法会尝试把第一列(第一个字段)转换成Student.class,这当然是不行的
		//String sql="select top 1 * from student";
		//Student stu=jdbc.queryForObject(sql, Student.class);
		String sql1="select id from student";
		String sql2="select birthday from student";
		List<Integer> il1=jdbc.queryForList(sql1,Integer.class);
		List<String> il2=jdbc.queryForList(sql2,String.class);
		
		for (Integer i : il1) {
			log.info("JdbcSqlTest03:"+i);
		}
		for (String date : il2) {
			log.info("JdbcSqlTest03:"+date);
		}
	}
	
	//@Test 和普通查询一样
	public void JdbcFunctionTest01(){//Function的用法几乎和普通的查询一毛一样
		String sql="select getdate()";
		Date date=jdbc.queryForObject(sql,Date.class);
		log.info("JdbcFunctionTest01:"+date);
	}
	
	@Test 
	public void JdbcProducureTest01(){
		String sql="exec pro_test";//就像在数据库中使用sql一样,简单粗暴,没有输入没有返回值的存储过程可以这样弄
		jdbc.execute(sql);
		log.info("JdbcProducureTest01执行完毕");
	}
	
	@Test 
	public void JdbcProducureTest02(){
		String sql="{call pro_test()}";//和上面没多大的区别,只是sql不太一样
		jdbc.execute(sql);
		log.info("JdbcProducureTest02执行完毕");
	}

	@Test
	public void JdbcProducureTest03(){//output
		int a=1;
		int b=2;
		String c="jdbc";
		String sql="{call pro_output(?,?,?,?,?)}";
		Map<String, Object> result=
		jdbc.execute(sql,new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(CallableStatement cstmt) throws SQLException, DataAccessException {
				cstmt.setInt(1, a);
				cstmt.setInt(2, b);
				cstmt.setString(3, c);
				cstmt.registerOutParameter(4, Types.INTEGER);
				cstmt.registerOutParameter(5, Types.VARCHAR);
				cstmt.execute();
				Map<String, Object> map=new HashMap<>();
				map.put("sum", cstmt.getObject(4));
				map.put("result", cstmt.getObject(5));
				return map;
			}
		});
		log.info("JdbcProducureTest03:"+result);	
	}
	
	@Test
	public void JdbcProducureTest04(){//return
		int a=1;
		int b=2;
		String sql="{?=call pro_return(?,?)}";
		Map<String, Object> result=
		jdbc.execute(sql,new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(CallableStatement cstmt) throws SQLException, DataAccessException {
				cstmt.registerOutParameter(1, Types.INTEGER);
				cstmt.setInt(2, a);
				cstmt.setInt(3, b);				
				cstmt.execute();
				Map<String, Object> map=new HashMap<>();
				map.put("sum", cstmt.getObject(1));
				return map;
			}
		});
		log.info("JdbcProducureTest04:"+result);	
	}
	
	
	@Test
	public void JdbcProducureTest05(){//存储过程返回的是select * from table这种形式的可以使用ResultSet来接受
		String sql="{call pro_selectfromtable(?)}";
		String sex="女";
		List<Map<String, Object>>  result=
		jdbc.execute(sql, new CallableStatementCallback<List<Map<String, Object>>>() {
			@Override
			public List<Map<String, Object>>  doInCallableStatement(CallableStatement cstmt) throws SQLException, DataAccessException {
				cstmt.setString(1, sex);
				ResultSet rs = cstmt.executeQuery();  
				return RStoListMap(rs);
			}
		});
		log.info("JdbcProducureTest05:"+result);
	}
	
	@Test
	public void JdbcProducureTest06(){//存储过程返回的是select 1 这种形式单个结果,其实和select * from table一样用resultset来处理
		String sql="{call pro_selectsingle(?)}";
		String name="jdbc";
		List<Map<String, Object>>  result=
		jdbc.execute(sql, new CallableStatementCallback<List<Map<String, Object>>>() {
			@Override
			public List<Map<String, Object>>  doInCallableStatement(CallableStatement cstmt) throws SQLException, DataAccessException {
				cstmt.setString(1, name);
				ResultSet rs = cstmt.executeQuery();  
				return RStoListMap(rs);
			}
		});
		log.info("JdbcProducureTest06:"+result);
	}
	
	
	
	
	@Test
	public void JdbcProducureTest13(){//output,采用jdbc.call形式
		int a=1;
		int b=2;
		String c="jdbc";
		String sql="{call pro_output(?,?,?,?,?)}";
		List<SqlParameter> sqlParams=new ArrayList<>();
		sqlParams.add(new SqlParameter(Types.INTEGER));                  //第一个?,输入参数
		sqlParams.add(new SqlParameter(Types.INTEGER));                  //第二个?,输入参数
		sqlParams.add(new SqlParameter(Types.VARCHAR));                  //第三个?,输入参数
		sqlParams.add(new SqlOutParameter("sum", Types.INTEGER));        //第四个?,输出参数
		sqlParams.add(new SqlOutParameter("result", Types.VARCHAR));     //第五个?,输出参数
		Map<String, Object> map=
		jdbc.call(new CallableStatementCreator() {			
			@Override
			public CallableStatement createCallableStatement(Connection conn) throws SQLException {
				CallableStatement cstmt=conn.prepareCall(sql);
				cstmt.setInt(1, a);
				cstmt.setInt(2, b);
				cstmt.setString(3, c);
				cstmt.registerOutParameter(4, Types.INTEGER);
				cstmt.registerOutParameter(5, Types.VARCHAR);				
				return cstmt;
			}
		}, sqlParams);
		log.info("JdbcProducureTest13:"+map);
	}
	
	@Test
	public void JdbcProducureTest14(){//存储过程以return形式返回返回值,这种反而简单
		int a=1;
		int b=2;
		String sql="{?=call pro_return(?,?)}";
		List<SqlParameter> sqlParams=new ArrayList<>();
		sqlParams.add(new SqlOutParameter("sum", Types.INTEGER));    //第一个?,输出参数
		sqlParams.add(new SqlParameter(Types.INTEGER));              //第二个?,输入参数(这个其实可以不要)
		sqlParams.add(new SqlParameter(Types.INTEGER));              //第三个?,输入参数(这个其实可以不要)
		Map<String, Object> map=
		jdbc.call(new CallableStatementCreator() {			
			@Override
			public CallableStatement createCallableStatement(Connection conn) throws SQLException {
				CallableStatement cstmt=conn.prepareCall(sql);
				cstmt.registerOutParameter(1, Types.INTEGER);
				cstmt.setInt(2, a);
				cstmt.setInt(3, b);
				return cstmt;
			}
		}, sqlParams);
		log.info("JdbcProducureTest14:"+map);
	}

	@Test
	public void JdbcProducureTest15(){
		String sql="{call pro_selectfromtable(?)}";
		String sex="女";
		List<SqlParameter> sqlParams=new ArrayList<>();
		sqlParams.add(new SqlParameter(Types.VARCHAR));
		Map<String, Object> result=
		jdbc.call(new CallableStatementCreator() {			
			@Override
			public CallableStatement createCallableStatement(Connection conn) throws SQLException {
				CallableStatement cstmt=conn.prepareCall(sql);
				cstmt.setString(1, sex);
				return cstmt;
			}
		}, sqlParams);
		log.info("JdbcProducureTest15:"+result);
		log.info("JdbcProducureTest15:"+result.get("#result-set-1").toString());
	}
	
	@Test
	public void JdbcProducureTest16(){
		String sql="{call pro_selectsingle(?)}";
		String name="jdbc";
		List<SqlParameter> sqlParams=new ArrayList<>();
		sqlParams.add(new SqlParameter(Types.VARCHAR));
		Map<String, Object> result=
		jdbc.call(new CallableStatementCreator() {			
			@Override
			public CallableStatement createCallableStatement(Connection conn) throws SQLException {
				CallableStatement cstmt=conn.prepareCall(sql);
				cstmt.setString(1, name);
				return cstmt;
			}
		}, sqlParams);
		log.info("JdbcProducureTest16:"+result);
		log.info("JdbcProducureTest16:"+result.get("#result-set-1").toString());
	}
	
	
	
	//工具方法,将ResultSet转换成List<Map<String, Object>>
	private  List<Map<String, Object>> RStoListMap(ResultSet rs) throws SQLException{//将rs转换成List<Map<String, Object>>
		List<Map<String, Object>> result=null;
		if(rs!=null){
			result=new ArrayList<>();
			ResultSetMetaData md = rs.getMetaData();  //得到结果集(rs)的结构信息，比如字段数、字段名等   
	        int columnCount = md.getColumnCount();    //返回此 ResultSet 对象中的列数
	        while(rs.next()){
	        	Map<String, Object> map=new HashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					map.put(md.getColumnName(i), rs.getObject(i));
				}
				result.add(map);
	        }
		}
		return result;
	}
	
	
	                      
}

package com.example;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
			log.info(stu);
		}	
	}
	
	//@Test
	public void JdbcSqlTest02(){//统计的方法
		String sql="select count(*) from student";
		int x=jdbc.queryForObject(sql, Integer.class);
		log.info(x);
	}

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
			log.info(i);
		}
		for (String date : il2) {
			log.info(date);
		}
	}
	
	//@Test
	public void JdbcFunctionTest01(){//Function的用法几乎和普通的查询一毛一样
		String sql="select getdate()";
		Date date=jdbc.queryForObject(sql,Date.class);
		log.info(date);
	}
	
	//@Test
	public void JdbcProducureTest01(){
		String sql="exec pro_test1";//就像在数据库中使用sql一样,简单粗暴
		jdbc.execute(sql);
	}
	
	//@Test
	public void JdbcProducureTest02(){
		int a=1;
		int b=2;
		//int c=0; 然而这种方法,遇到稍复杂的存储过程就要拼接语句,更无法取得返回值,并无卵用
		String sql="declare @c int begin exec pro_test2 "+ a + "," + b + ",@c output select @c end ";
		log.info(sql);
		jdbc.execute(sql);
	}

	//@Test
	public void JdbcProducureTest03(){//存储过程exec pro_test2 a,b,c,d output,e output 其中d,e是返回值
		int a=1;
		int b=2;
		String c="jdbc";
		String sql="{call pro_test2(?,?,?,?,?)}";
		List<SqlParameter> sqlParams=new ArrayList<>();
		sqlParams.add(new SqlOutParameter("result1", Types.INTEGER));
		sqlParams.add(new SqlOutParameter("result2", Types.VARCHAR));
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
		log.info(map);
	}
	
	//@Test
	public void JdbcProducureTest04(){//存储过程以return形式返回返回值,这种反而简单
		int a=1;
		int b=2;
		String sql="{?=call pro_test3(?,?)}";
		List<SqlParameter> sqlParams=new ArrayList<>();
		sqlParams.add(new SqlOutParameter("return", Types.INTEGER));
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
		log.info(map);
	}
	
	
	@Test
	public void JdbcProducureTest05(){//jdbc调用以output做返回值的存储过程
		int a=1;
		int b=2;
		String c="jdbc";
		String sql="{call pro_test2(?,?,?,?,?)}";
		Map<String, Object> result=
		(Map<String, Object>) jdbc.execute(sql,new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(CallableStatement cstmt) throws SQLException, DataAccessException {
				cstmt.setInt(1, a);
				cstmt.setInt(2, b);
				cstmt.setString(3, c);
				cstmt.registerOutParameter(4, Types.INTEGER);
				cstmt.registerOutParameter(5, Types.VARCHAR);
				cstmt.execute();
				Map<String, Object> map=new HashMap<>();
				map.put("r1", cstmt.getObject(4));
				map.put("r2", cstmt.getObject(5));
				return map;
			}
		});
		log.info(result);
		
	}
	
	@Test
	public void JdbcProducureTest06(){
		int a=1;
		int b=2;
		String c="jdbc";
		String sql="{call pro_test2(?,?,?,?,?)}";
		ResultSet  result=
		jdbc.execute(sql, new CallableStatementCallback<ResultSet >() {
			@Override
			public ResultSet  doInCallableStatement(CallableStatement cstmt) throws SQLException, DataAccessException {
				cstmt.setInt(1, a);
				cstmt.setInt(2, b);
				cstmt.setString(3, c);
				cstmt.registerOutParameter(4, Types.INTEGER);
				cstmt.registerOutParameter(5, Types.VARCHAR);
				ResultSet rs = cstmt.executeQuery();  
				log.info(rs==null);
				while(rs.next()){
					
					
					log.info(rs.getObject(1));
					log.info(rs.getObject(2));
				}
				return rs;
			}
		});
		log.info(result);
	}
	
}

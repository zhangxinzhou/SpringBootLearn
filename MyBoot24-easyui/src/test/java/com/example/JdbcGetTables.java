package com.example;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 注意:本例使用的是sqlserver数据库,使用其他数据库测试可能会出现错误
 * 获取表名
 * @author user
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcGetTables {
	
	private Logger log=Logger.getLogger(getClass());

	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private DataSourceProperties dsp;
	
	//@Test //dataSource的配置
	public void test0() throws SQLException{
		String driver=dsp.getDriverClassName();
		String url=dsp.getUrl();
		String username=dsp.getUsername();
		String password=dsp.getPassword();
		log.info("test0:"+driver);
		log.info("test0:"+url);
		log.info("test0:"+username);
		log.info("test0:"+password);
	}
	
	@Test //jdbc可以用?动态绑定一些参数
	public void test000(){
		String sql="select * from student where sex=?";
		List<Map<String, Object>> result=jdbc.queryForList(sql,"女");
		log.info("test000:"+result);
	}
	
	//@Test jdbc不支持动态绑定表名,所以这个方法是无效的,目前只能拼接的方式来处理
	//原因:select * from student where sex ='女'是可以的而select * from 'student'是不行的,table表名不应该加上'',推测这是原因
	public void test001(){
		String sql="select * from table";
		List<Map<String, Object>> result=jdbc.queryForList(sql,"student");
		log.info("test001:"+result);
	}
	
	@Test
	public void test01(){  //获取当前数据库下的所有表名
		String sql="select name '表名',create_date '创建日期',modify_date '最后一次修改日期',type '类型' from sys.tables";
		List<Map<String, Object>> result=jdbc.queryForList(sql);
		for (Map<String, Object> map : result) {
			log.info("test01:"+map);
		}	
	}
	
	@Test
	public void test02(){  //获取当前数据库下的所有表名,并查所有表的数据
		String sql1="select name from sys.tables";	
		List<String> tnlist=jdbc.queryForList(sql1,String.class);	
		log.info("test02:"+tnlist);
		for (String tname : tnlist) {
			String sql2="select * from "+tname;
			List<Map<String, Object>> result=jdbc.queryForList(sql2);
			for (Map<String, Object> map : result) {
				log.info("test02:"+map);
			}
		}				
	}
	
	@Test
	public void test03(){  //获取当前数据库下的所有表名,分页
		String sql="select * from(select ROW_NUMBER() over (order by object_id) RowNum,"
				+ "name '表名',create_date '创建日期',modify_date '最后一次修改日期',type '类型' from sys.tables)"
				+ "as temp where RowNum between ? and ?";
		int pageNum=1;     //第一页
		int pageSize=3;	   //每页几张
		int b1=(pageNum-1)*pageSize+1;
		int b2=pageNum*pageSize;
		List<Map<String, Object>> result=jdbc.queryForList(sql,b1,b2);
		for (Map<String, Object> map : result) {
			log.info("test03:"+map);
		}	
	}
	
	@Test
	public void test04(){  //并不知道查的是哪个表的情况,对其进行分页查询
		String sql1="select name from sys.tables";	
		List<String> tnlist=jdbc.queryForList(sql1,String.class);		
		String tableName=tnlist.get(0);
		String sql2=";with cte as(select top 1 name from syscolumns where id=object_id(?))"
				+ "select * from(select ROW_NUMBER() over (order by (select * from cte)) RowNum,* from "+tableName+")"
				+ "as temp where RowNum between ? and ?";
		int pageNum=1;     //第一页
		int pageSize=3;	   //每页几张
		int b1=(pageNum-1)*pageSize+1;
		int b2=pageNum*pageSize;
		List<Map<String, Object>> result=jdbc.queryForList(sql2,tableName,b1,b2);
		for (Map<String, Object> map : result) {
			log.info("test04:"+map);
		}
	}	
	
	
	@Test
	public void test05(){ //用存储过程来做分页动态查询表jdbc.execute(sql,CallableStatementCallback)
		String sql="{call pro_tabledynamic(?,?,?)}";
		String tName="student";
		Integer pageNum=1;
		Integer pageSize=2;
		Map<String, Object> result=
		jdbc.execute(sql,new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(CallableStatement cstmt)
					throws SQLException, DataAccessException {
				cstmt.setString(1, tName);
				cstmt.setInt(2, pageNum);
				cstmt.setInt(3, pageSize);
				boolean b=cstmt.execute();//true 返回过个结果集,false 返回返回了更新计数值，可以通过调用 getUpdateCount 方法检索此值。
				Map<String, Object> map=new HashMap<>();
				if(b){
					do{
						ResultSet rs=cstmt.getResultSet();
						map.put(rs.toString(), RStoListMap(rs));
					}while(cstmt.getMoreResults());
				}			
				return map;
			}
		});
		log.info("test05:"+result);
	}
	
	@Test
	public void test06(){ //用存储过程来做分页动态查询表jdbc.call
		String sql="{call pro_tabledynamic(?,?,?)}";
		String tName="student";
		Integer pageNum=1;
		Integer pageSize=2;
		List<SqlParameter> sqlParams=new ArrayList<>();
		sqlParams.add(new SqlParameter(Types.VARCHAR));
		sqlParams.add(new SqlParameter(Types.INTEGER));
		sqlParams.add(new SqlParameter(Types.INTEGER));
		Map<String, Object> result=
		jdbc.call(new CallableStatementCreator() {		
			@Override
			public CallableStatement createCallableStatement(Connection conn) throws SQLException {
				CallableStatement cstmt=conn.prepareCall(sql);
				cstmt.setString(1, tName);
				cstmt.setInt(2, pageNum);
				cstmt.setInt(3, pageSize);
				return cstmt;
			}
		}, sqlParams);
		log.info("test06:"+result);
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

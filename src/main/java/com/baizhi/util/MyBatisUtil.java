package com.baizhi.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class MyBatisUtil {
	
	private static SqlSessionFactory sqlSessionFactory = null;
	static{
		InputStream is = null;
		try {
		
			is = Resources.getResourceAsStream("mybatis-config.xml");
		
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
		
			e.printStackTrace();
		}finally{
			if(is!=null)try {is.close();} catch (IOException e) {}
		}
	}
	
	private static ThreadLocal<SqlSession> tl = new ThreadLocal<SqlSession>();
	
	public static SqlSession getSqlSession(){
		SqlSession sqlSession = tl.get();
	
		if(sqlSession==null){
			 sqlSession = sqlSessionFactory.openSession();
			 tl.set(sqlSession);
		}
		return sqlSession;
	}
	

	public static void close(SqlSession sqlSession){
		if(sqlSession!=null){
			sqlSession.close();tl.remove();
		}
	}
	

	public static void commit(SqlSession sqlSession){
		if(sqlSession!=null){
			sqlSession.commit();
			close(sqlSession);
		}
	}

	public static void rollback(SqlSession sqlSession){
		if(sqlSession!=null){
			sqlSession.rollback();
			close(sqlSession);
		}
	}
}

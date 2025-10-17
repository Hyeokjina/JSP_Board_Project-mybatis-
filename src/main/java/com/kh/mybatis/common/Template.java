package com.kh.mybatis.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//공통 템플릿(매번 반복적으로 작성될 코드를 메서드 정의)
public class Template {
	public static SqlSession getSqlSession() {
		String resource = "/mybatis-config.xml";
		SqlSession session = null;
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			session = sqlSessionFactory.openSession(false); // flase -> 수동커밋
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return session;
	}
}
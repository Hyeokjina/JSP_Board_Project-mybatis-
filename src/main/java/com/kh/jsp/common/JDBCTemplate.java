package com.kh.jsp.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//공통 템플릿(매번 반복적으로 작성될 코드를 메서드 정의)
public class JDBCTemplate {
	//모든 메서드를 전부 static메서드로 만듬
	
	//1. Connection객체 생성 후 해당 Connetion객체를 반환
	public static Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		
		/*
		 * 우리는 클래스 패스를 기준으로 리소스를 찾아서 가지고와야한다.
		 * JDBCTemplate.class.getResource("/경로") -> JDBCTemplate클래스를 기준으로 경로를 가져올수있다.
		 */
		String path = JDBCTemplate.class.getResource("/db/driver/driver.properties").getPath();
		
		try {
			prop.load(new FileInputStream(path));
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));			
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//2. Connection객체를 전달 받아 상태를 확인 후 commit하는 메서드
	public static void commit(Connection conn) {
		try {
			if(conn!= null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//3. Connection객체를 전달 받아 상태를 확인 후 rollback하는 메서드
		public static void rollback(Connection conn) {
			try {
				if(conn!= null && !conn.isClosed()) {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	//4. Statement관련 객체를 전달받아 반납시켜주는 메서드
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//5. Connetion관련 객체를 전달받아 반납시켜주는 메서드
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//5. ResultSet관련 객체를 전달받아 반납시켜주는 메서드
	public static void close(ResultSet rest) {
		try {
			if(rest != null && !rest.isClosed()) {
				rest.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

}

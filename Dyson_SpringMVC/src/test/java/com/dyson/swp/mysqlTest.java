package com.dyson.swp;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;


/*
	Mysql Server (Amazon AWS-RDS)와 연결 테스트
 */

public class mysqlTest {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://dyson-springboot2-webservice.cyghqc77an0t.ap-northeast-2.rds.amazonaws.com:3306";
	private static final String USER = "dyson";
	private static final String PW = "Sdy8890^^*";
	
	@Test
	public void mysqlConnectionTest() throws Exception {
		Class.forName(DRIVER);
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PW)) {
			System.out.println("mysql Connection Test Success");
			System.out.println("conn : " + conn);
		} catch (Exception e) {
			System.out.println("mysql Connection Test Fail");
			e.printStackTrace();
		}
		

	}

}

package com.dyson.swp;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class oracleTest {

	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@oracle.ctplrvn9u8xp.ap-northeast-2.rds.amazonaws.com:1521:ORACLE";
	private static final String USER = "Dyson";
	private static final String PW = "Qwer1234";
	
	@Test
	public void oracleConnectionTest() throws Exception {
		Class.forName(DRIVER);
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PW)) {
			System.out.println("oracle Connection Test Success");
			System.out.println("conn : " + conn);
		} catch (Exception e) {
			System.out.println("oracle Connection Test Fail");
			e.printStackTrace();
		}
	}
}

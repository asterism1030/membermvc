package com.kitri.util.db;

import java.sql.*;

public class DBConnection {
	//static 블록 초기화  --> 처름 load될때 한번만 호출된다.
	//어느 메소드에서 호출하든 영향을 안받으므로 static으로.
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public synchronized static Connection makeConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.159:1521:orcl", "kitri", "kitri");
	}
}

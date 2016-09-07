package com.kitri.util.db;

import java.sql.*;

public class DBConnection {
	//static ��� �ʱ�ȭ  --> ó�� load�ɶ� �ѹ��� ȣ��ȴ�.
	//��� �޼ҵ忡�� ȣ���ϵ� ������ �ȹ����Ƿ� static����.
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

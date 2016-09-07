package com.kitri.util.db;

import java.sql.*;

public class DBClose {
	//동기화 : 내가 연결하는 동안 다른것은 연결X 동시다발X 일치함이 보장되어야 하므로
	/*
	 * synchronized(this) {
	 * 		~~~~
	 * }
	 * 메소드 내 일부만 하는것을 블록 동기화
	 * 
	 */
	
	public synchronized static void close(Connection conn, PreparedStatement psmt) {
		 try {
	         if (psmt != null)
	        	 psmt.close();
	         if (conn != null)
	            conn.close();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	}
	
	
	public synchronized static void close(Connection conn, Statement stmt, ResultSet rs) {
		 try {
	         if (rs != null)
	            rs.close();
	         if (stmt != null)
	            stmt.close();
	         if (conn != null)
	            conn.close();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	}
	
	
	public synchronized static void close(Connection conn, Statement stmt) {
		 try {
	         if (stmt != null)
	            stmt.close();
	         if (conn != null)
	            conn.close();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	}
}

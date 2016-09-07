package com.kitri.util.db;

import java.sql.*;

public class DBClose {
	//����ȭ : ���� �����ϴ� ���� �ٸ����� ����X ���ôٹ�X ��ġ���� ����Ǿ�� �ϹǷ�
	/*
	 * synchronized(this) {
	 * 		~~~~
	 * }
	 * �޼ҵ� �� �Ϻθ� �ϴ°��� ��� ����ȭ
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

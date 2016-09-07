<%@ page language="java" contentType="text/html; charset=EUC-KR"
	import="java.sql.*, java.net.*"
    pageEncoding="EUC-KR"%>
    
<%!
public void init() {
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}
public Connection makeConnection(){
	Connection conn = null;
	try {
		conn = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.159:1521:orcl", "kitri", "kitri");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return conn;
}
%>

<%
String root = request.getContextPath();
String id = request.getParameter("id");
String pass = request.getParameter("pass");

Connection conn = null;
Statement st = null;
ResultSet rs = null;


String sql = "";
sql += "select name \n";
sql += "from member \n";
sql += "where id='"+id+"'and pass='"+ pass+"' \n";

String name = null;

try {
	conn = makeConnection();
	st = conn.createStatement();
	rs = st.executeQuery(sql);
	if (rs.next()) {
		name = rs.getString(1);
	}
} catch (SQLException e) {
	e.printStackTrace();
}  finally {
	try {
		if (rs != null)
			rs.close();
		if (st != null)
			st.close();
		if (conn != null)
			conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
  }

if(name != null) {
	response.sendRedirect(root + "/join/success.jsp?name="+URLEncoder.encode(name, "EUC-KR"));//name-¾ÈÈ¿ÀÎ name = %BC%C*%C*...
} else {
	response.sendRedirect(root + "/join/fail.jsp");
}

%> 

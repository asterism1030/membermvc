<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%
String root = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<center>
	<h3>MVC pattern을 이용한 회원가입 & 로그인</h3>
	<a href="<%= root %>/user?act=mvjoin">회원가입</a><br>
	<a href="<%= root %>/user?act=mvlogin">로그인</a><br>
</center>
</body>
</html>
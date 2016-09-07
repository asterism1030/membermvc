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

<font color = "red" size="15">아이디 또는 비밀번호 확인 후 다시 로그인 해주세요.<br>
</font>
<a href= "<%= root %>/user?act=mvlogin">로그인 </a>
</center>
</body>
</html>
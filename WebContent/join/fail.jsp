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

<font color = "red" size="15">서버에 문제가 있어 회원 가입이 불가능합니다ㅠㅠ <br>
다시 시도해주세요!!<br>
</font>
<a href= "<%= root %>/login/login.jsp">로그인 </a>
</center>
</body>
</html>
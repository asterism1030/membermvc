<%@ page language="java" contentType="text/html; charset=EUC-KR"
	import="com.kitri.member.model.MemberDto"
    pageEncoding="EUC-KR"%>
<%
String root = request.getContextPath();
MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");

if(memberDto != null) { //memberDto가 null X -> 로그인한 경우 이동
%>
<!-- request.setAttribute(~)의 유효범위(Controller~success)가 아니므로 다시 해줘야함 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<center>
<h3><%= memberDto.getName() %>님 메일리스트</h3>
10.ex.. <br>
9.ex.. <br>
</center>
</body>
</html>
<%
} else { //로그인 안한 경우 다시 로그인
	response.sendRedirect(root+ "/user?act=mvlogin");
}
%>
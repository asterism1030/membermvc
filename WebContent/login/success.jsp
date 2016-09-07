<%@ page language="java" contentType="text/html; charset=EUC-KR"
	import="com.kitri.member.model.MemberDto"
    pageEncoding="EUC-KR"%>
<%
String root = request.getContextPath();
MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");

if(memberDto != null) { //memberDto가 null X -> 로그인한 경우 이동
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<center>
<%= memberDto.getName() %>
(<a href="mailto:<%=memberDto.getEmail1()%>@<%=memberDto.getEmail2()%>"><%= memberDto.getId() %></a>)님 안녕하세요.<br>
<a href="<%=root%>/user?act=logout">로그아웃</a>
<a href="<%=root%>/user?act=maillist">메일보기</a>
</center>
</body>
</html>
<%
} else {
	response.sendRedirect(root+ "/user?act=mvlogin");
}
%>
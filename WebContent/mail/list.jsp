<%@ page language="java" contentType="text/html; charset=EUC-KR"
	import="com.kitri.member.model.MemberDto"
    pageEncoding="EUC-KR"%>
<%
String root = request.getContextPath();
MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");

if(memberDto != null) { //memberDto�� null X -> �α����� ��� �̵�
%>
<!-- request.setAttribute(~)�� ��ȿ����(Controller~success)�� �ƴϹǷ� �ٽ� ������� -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<center>
<h3><%= memberDto.getName() %>�� ���ϸ���Ʈ</h3>
10.ex.. <br>
9.ex.. <br>
</center>
</body>
</html>
<%
} else { //�α��� ���� ��� �ٽ� �α���
	response.sendRedirect(root+ "/user?act=mvlogin");
}
%>
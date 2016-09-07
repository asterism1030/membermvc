<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.kitri.member.model.MemberDetailDto"%>
<%
String root = request.getContextPath();
MemberDetailDto memberDetailDto = (MemberDetailDto)request.getAttribute("userInfo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<center>
<%= memberDetailDto.getName() %>님 축하합니다.<br>
가입하신 정보는 아래와 같습니다.<br>
아이디   : <%= memberDetailDto.getId() %><br>
이메일   : <%= memberDetailDto.getEmail1()%>&nbsp@&nbsp<%= memberDetailDto.getEmail2()%><br>
전화번호 : <%= memberDetailDto.getTel1()%>&nbsp-&nbsp<%= memberDetailDto.getTel2()%>&nbsp-&nbsp<%= memberDetailDto.getTel3()%><br>
주소     : <%= memberDetailDto.getZip1()%>-<%= memberDetailDto.getZip1()%>-<%=memberDetailDto.getZip1() %><br>
로그인 후 서비스를 이용하세요<br>
<a href='<%=root%>/user?act=mvlogin'>로그인</a>
</center>
</body>
</html>
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
<%= memberDetailDto.getName() %>�� �����մϴ�.<br>
�����Ͻ� ������ �Ʒ��� �����ϴ�.<br>
���̵�   : <%= memberDetailDto.getId() %><br>
�̸���   : <%= memberDetailDto.getEmail1()%>&nbsp@&nbsp<%= memberDetailDto.getEmail2()%><br>
��ȭ��ȣ : <%= memberDetailDto.getTel1()%>&nbsp-&nbsp<%= memberDetailDto.getTel2()%>&nbsp-&nbsp<%= memberDetailDto.getTel3()%><br>
�ּ�     : <%= memberDetailDto.getZip1()%>-<%= memberDetailDto.getZip1()%>-<%=memberDetailDto.getZip1() %><br>
�α��� �� ���񽺸� �̿��ϼ���<br>
<a href='<%=root%>/user?act=mvlogin'>�α���</a>
</center>
</body>
</html>
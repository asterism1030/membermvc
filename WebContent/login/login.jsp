<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
String root = request.getContextPath();

//쿠키가 클라이언트에 저장되었는데 그 저장된 클라이언트의 쿠키를 가져온다
Cookie cookie[] = request.getCookies(); //다른 브라우저를 가는등의 동작을 해도 로그인 되 있도록.

String id = "";
String ck = "";
if (cookie != null) {
	int len = cookie.length;
	for (int i = 0; i < len; i++) {
		if("loginid".equals(cookie[i].getName())) {
			id = cookie[i].getValue();
			ck = " checked='checked'";
			break;
		}
	}
}
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<script type="text/javascript">
function logincheck(){
	if(document.getElementById("id").value  == "") {
		alert("아이디 입력!");
		return;
	} else if(document.getElementById("pass").value == "") {
		alert("비밀번호 입력!");
		return;
	} else {
		document.loginform.action = "<%= root %>/user"; //Viewer에선 항상 Controller로 간다
		document.loginform.submit();
	}
}
function joinmove(){
	document.location.href ="<%= root %>/user?act=mvjoin";
}
</script>
</head>
<body>
<center>
<form name="loginform" method="post" action="">
<input type="hidden" name="act" value="login">
<table>
<tr>
   <td class="td3" colspan="2" align="right">
   <input type="checkbox" name="idsv" id="idsv" value="ok"<%=ck %>>아이디저장
   </td>
</tr>
<tr>
	<td class="td1">아이디</td>
	<td class="td3"><input type="text" name="id" id="id" value="<%= id%>"></td>
</tr>
<tr>
	<td class="td2">비밀번호</td>
	<td class="td4"><input type="password" name="pass" id="pass"></td>
</tr>
<tr>
	<td colspan="2" align="center">
	<input type="button" value="로그인" onclick="javascript:logincheck();">
	<input type="button" value="회원가입" onclick="javascript:joinmove();">
	</td>
</tr>
</table>
</form>
</center>
</body>
</html>
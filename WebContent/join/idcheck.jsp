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
<link rel="stylesheet" href="../css/style.css" type="text/css">
<script type="text/javascript">
function idcheck(){
	if(document.getElementById("id").value == "") {
		alert("검색 아이디 입력!");
		return;
	} else {
		//form에 입력하는 값은 query String을 만들수 X
		//35번째 줄 <input type="hidden" name="act" value="idsearch">
		//로 동작... id값을 가져가진 않는다
		//무조건 아래와 같이 작성한다(넘겨주는 값이 없으므로..)
		document.idform.action = "<%=root%>/user";
		document.idform.submit();
	}
}

function iduse(id){
	//idcheck.jsp를 열었던 창
	//여기선 MemberController이다
	opener.document.joinform.id.value = id; //회원가입단의 아이디에 값 넣음
	self.close();
}
</script>
</head>
<body>
<center>
<form name="idform" method="get" action="" onsubmit="return false;">
<input type="hidden" name="act" value="idsearch">
<h3>아이디 중복 검사</h3>
<table width="350">
<tr>
	<td class="td3">검색할 아이디를 입력하세요</td>
</tr>
<tr>
	<td class="td4" style="text-align: center">
	<input type="text" name="id" id="id" onkeypress="javascript:if(event.keyCode == 13){ idcheck(); }">
	<input type="button" value="검색" onclick="javascript:idcheck();">
	</td>
</tr>

<%
String id = request.getParameter("id");
if(id == null) { //검색한 적X
%>
<tr>
	<td class="td4">
	검색할 아이디를 정확히 입력한 후 검색 버튼을 클릭하세요..
	</td>
</tr>
<%
} else { //검색한 적O
	int count = Integer.parseInt(request.getParameter("count"));
System.out.println("**************"+count);
	if (count == 0) {
%>
<tr>
	<td class="td4">
	<b><%= id %></b>는 사용 가능합니다.
	<input type="button" value="사용" onclick="javascript:iduse('<%= id %>')">
	</td>
</tr>
<%
	} else {
%>
<tr>
	<td class="td4">
	<b><%= id %></b>는 사용 불가능합니다.
	</td>
</tr>
<%
	}
}
%>
</table>
</form>
</center>
</body>
</html>
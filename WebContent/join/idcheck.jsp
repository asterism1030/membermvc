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
		alert("�˻� ���̵� �Է�!");
		return;
	} else {
		//form�� �Է��ϴ� ���� query String�� ����� X
		//35��° �� <input type="hidden" name="act" value="idsearch">
		//�� ����... id���� �������� �ʴ´�
		//������ �Ʒ��� ���� �ۼ��Ѵ�(�Ѱ��ִ� ���� �����Ƿ�..)
		document.idform.action = "<%=root%>/user";
		document.idform.submit();
	}
}

function iduse(id){
	//idcheck.jsp�� ������ â
	//���⼱ MemberController�̴�
	opener.document.joinform.id.value = id; //ȸ�����Դ��� ���̵� �� ����
	self.close();
}
</script>
</head>
<body>
<center>
<form name="idform" method="get" action="" onsubmit="return false;">
<input type="hidden" name="act" value="idsearch">
<h3>���̵� �ߺ� �˻�</h3>
<table width="350">
<tr>
	<td class="td3">�˻��� ���̵� �Է��ϼ���</td>
</tr>
<tr>
	<td class="td4" style="text-align: center">
	<input type="text" name="id" id="id" onkeypress="javascript:if(event.keyCode == 13){ idcheck(); }">
	<input type="button" value="�˻�" onclick="javascript:idcheck();">
	</td>
</tr>

<%
String id = request.getParameter("id");
if(id == null) { //�˻��� ��X
%>
<tr>
	<td class="td4">
	�˻��� ���̵� ��Ȯ�� �Է��� �� �˻� ��ư�� Ŭ���ϼ���..
	</td>
</tr>
<%
} else { //�˻��� ��O
	int count = Integer.parseInt(request.getParameter("count"));
System.out.println("**************"+count);
	if (count == 0) {
%>
<tr>
	<td class="td4">
	<b><%= id %></b>�� ��� �����մϴ�.
	<input type="button" value="���" onclick="javascript:iduse('<%= id %>')">
	</td>
</tr>
<%
	} else {
%>
<tr>
	<td class="td4">
	<b><%= id %></b>�� ��� �Ұ����մϴ�.
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
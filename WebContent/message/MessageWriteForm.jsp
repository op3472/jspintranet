<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript" charset="utf-8" src="../js/message.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form name="messageform" action="../MessageWrite.ms" method="post"  onsubmit="return checkValue()">
	<table width=600 border="0" cellpadding="0" cellspacing="0">
	<tr>
	<th><div align="center"><label for="messageAddressee">������</label></div></th>
	<td><input type="text" id="messageAddressee" name="messageAddressee" maxlength="20"></td>
	</tr>
	<tr>
	<th><div align="center"><label for="messageSubject">��������</label></div></th>
	<td><input type="text" id="messageSubject" name="messageSubject" maxlength="100"></td>
	</tr>
	<tr>
	<th><div align="center"><label for="messageContent">��������</label></div></th>
	<td> <textarea id="messageContent" name="messageContent" cols="67" rows="15"></textarea>        </td>
	</tr>
	<tr  align="right">
	<td colspan="2">
	<input type="submit" value="������">
	<input type="button" onclick="window.close()" value="�ݱ�">
	</td>
	</tr>
	</table>
	</form>
</body>
</html>
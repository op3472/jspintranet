<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript" charset="utf-8" src="./js/message.js"></script>
<title>Insert title here</title>
</head>
<body>
	<table width=600 border="0" cellpadding="0" cellspacing="0">
	<tr>
	<th><div align="center">보낸사람</div></th>
	<td>${list.messageSender }</td>
	</tr>
	<tr>
	<th><div align="center">쪽지제목</div></th>
	<td>${list.messageSubject}</td>
	</tr>
	<tr>
	<th><div align="center">쪽지내용</div></th>
	<td>${list.messageContent } </td>
	</tr>
	<tr  align="right">
	<td colspan="2">
	<input type="button" onclick="location.href='./MessageListView.ms'" value="목록보기">
	<input type="button" onclick="location.href='./MessageDelete.ms?num=${list.messageId}'" value="삭제하기">	
	<input type="button" onclick="reloadclose()" value="닫기">
	</td>
	</tr>
	</table>
</body>
</html>
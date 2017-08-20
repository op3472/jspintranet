<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="css/navi.css" />
<link rel="stylesheet" href="css/Mypage.css">
<%@page import="net.member.db.MemberBean"%> 
<%@page import="net.member.db.MemberDAO"%> 


</head>
<body>
<% 
	String id= (String)session.getAttribute("id");
	MemberDAO dao = new MemberDAO();
	MemberBean mb=dao.getSelect(id);
%>
<div class="wrap">
		  <jsp:include page="/menu.jsp" flush="false"/>
		<div class="logo">
		</div>
		<div class="content">
		<div class="info">
		  <table cellspacing='0'>
	<thead>
		<tr>
			<th>목록</th>
			<th>값</th>
		</tr>
	</thead><!-- Table Header -->

	<tbody>
		<tr>
			<td>아이디</td>
			<td><%= mb.getMEMBER_ID() %></td>
			
		</tr><!-- Table Row -->
		<tr>
			<td>이름</td>
			<td><%= mb.getMEMBER_NAME()%></td>
		</tr>

		<tr>
			<td>나이</td>
			<td><%= mb.getMEMBER_AGE() %></td>
		</tr>

		<tr>
			<td>성별</td>
			<td><%= mb.getMEMBER_GENDER()%></td>
		</tr>

		<tr>
			<td>이메일</td>
			<td><%=mb.getMEMBER_EMAIL() %></td>
		</tr>
	</tbody>
</table>
	</div>
		</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>
<!-- /#wrapper --> 
</body>
</html>
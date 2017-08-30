<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="css/navi.css" />
</head>
<body>
<%
	String id= (String)session.getAttribute("id");
%>
	<div class="wrap">
		<div class='menu'>
			 <jsp:include page="/menu.jsp" flush="false"/>
		</div>
		<div class="logo">
		</div>
		<div class="content">
		<p class="login"><%=id%> 님 관리자 페이지입니다. </p>
		</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>
</body>
</html>
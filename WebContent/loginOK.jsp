<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, java.text.*"  %>
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
<%
 java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy�� MM�� dd�� HH�� mm�� ss��");
 String today = formatter.format(new java.util.Date());
%>
	<div class="wrap">
		  <jsp:include page="/menu.jsp" flush="false"/>
		<div class="logo">
		</div>
		<div class="content">
		<p class="login"><%=id%>�� �α��ο� �����ϼ̽��ϴ�.
		<br><br>���ӽð� : <%out.println(today);%> </p>
		</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>

</body>
</html>
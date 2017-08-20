<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<%@page import="java.io.BufferedInputStream" %>
<%@page import="java.io.BufferedOutputStream" %>
<%@page import="java.io.FileInputStream" %>
<%@page import="java.io.File" %>

</head>
<body>
<%
	String fileName=request.getParameter("file");
	String realDir="C:/Users/user/workspace/webreport/WebContent/boardupload";
	
	File file = new File(realDir+"/"+fileName);
	
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\";");
	
	FileInputStream fileInputStream = new FileInputStream(file);
	out.clear(); 
	out=pageContext.pushBody();
	ServletOutputStream servletOutputStream = response.getOutputStream();
	
	byte b [] = new byte[5*1024*1024];
	int data=0;
	
	while((data=(fileInputStream.read(b,0,b.length)))!= -1){
		servletOutputStream.write(b,0,data);
	}
	servletOutputStream.flush();
	servletOutputStream.close();
	fileInputStream.close();
	
%>
</body>
</html>
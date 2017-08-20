<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="./css/navi.css" />
<link rel="stylesheet" type="text/css" href="./css/application.css" />
<link rel="stylesheet" type="text/css" href="./css/pagination.css" />
<link rel="stylesheet" type="text/css" href="./css/detail.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="sun.java2d.loops.Blit"%> 
<%@page import="lecture.db.LectureBean"%> 
<%@page import="lecture.db.memberBean"%> 
<%@page import="java.util.List"%> 
<%@page import="net.member.db.MemberBean"%> 
<%@page import="net.member.db.MemberDAO"%>
<%@page import="lecture.db.memberBean"%>  
<% 

String id=null; 
if(session.getAttribute("id")!=null){ 
    id=(String)session.getAttribute("id"); 
} 
List memberList =(List)request.getAttribute("memberlist"); 
MemberDAO dao = new MemberDAO();
MemberBean mb=dao.getSelect(id);
int credit=0;
double result=0.0;
	%> 

</head>
<body>

<div class="wrap">

		  <jsp:include page="/menu.jsp" flush="false"/>
		<div class="logo">
		</div>
		
		<div class="content">
  <div class="appll3">
    	<table class="board" width=570 border="0" cellpadding="0" cellspacing="0"> 
      
	<tr>
	<td colspan="4" align="center" valign="middle">
	<p class="bold">나의 수강신청목록</p>
	</td>
	</tr>
    <tr class="tr1" align="center" valign="middle" > 
        <td> 수강과목 </td> 
        <td> 담당교수</td> 
        <td>학점</td> 
        <td>등급</td>
    </tr>     
	  <c:forEach var="member" items="${requestScope.memberlist}">
    <tr class="tr2" align="center" valign="middle"   >  
        <td>  
              ${member.lecture_subject}            
        </td>         
        <td> 
            ${member.lecture_name}
        </td> 
        <td> 
           ${member.lecture_credit} 
        </td> 
        <td>
        	 <c:choose>
                 <c:when test="${member.result >=4.5}">
                       A+
                  </c:when>
                  <c:when test="${member.result >=4.0}">
                       A
                  </c:when>
                  <c:when test="${member.result >=3.5}">
                       B+
                  </c:when>
                  <c:when test="${member.result >=3.0}">
                       B
                  </c:when>
                  <c:when test="${member.result >=2.5}">
                       C+
                  </c:when>
                  <c:when test="${member.result >=2.0}">
                       C
                  </c:when>
                  <c:when test="${member.result >=1.5}">
                       D+
                  </c:when>
                  <c:when test="${member.result >=1.0}">
                       D
                  </c:when>
                  <c:when test="${member.result >=0}">
                       F
                  </c:when>
                  
              </c:choose>   
        </td>

    </tr> 
   
    </c:forEach>
       <tr align="right"> 
        <td class="bold" colspan="4"> 
        	<%
        	for(int i=0 ; i<memberList.size() ; i++){ 
        		memberBean bl=(memberBean)memberList.get(i);
        		credit+=bl.getLecture_credit();
        		result+=bl.getResult()*bl.getLecture_credit();
        	}
         
    		String format="#.##";
    		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
    		String result2 = df.format(result/credit);
    		
        	%>
        	
			평점:<%= result2%>/4.5
		<td>
    </tr>
</table>
</div>
  		</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>
</body>
</html>
</body>
</html>
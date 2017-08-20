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
<%@page import="sun.java2d.loops.Blit"%> 
<%@page import="lecture.db.LectureBean"%> 
<%@page import="lecture.db.memberBean"%> 
<%@page import="java.util.List"%> 
<%@page import="net.member.db.MemberBean"%> 
<%@page import="net.member.db.MemberDAO"%> 
<% 

String id=null; 
if(session.getAttribute("id")!=null){ 
    id=(String)session.getAttribute("id"); 
} 
List memberList =(List)request.getAttribute("memberlist"); 
int listcount=((Integer)request.getAttribute("listcount")).intValue(); 
int nowpage=((Integer)request.getAttribute("page")).intValue(); 
int maxpage=((Integer)request.getAttribute("maxpage")).intValue(); 
int startpage=((Integer)request.getAttribute("startpage")).intValue(); 
int endpage=((Integer)request.getAttribute("endpage")).intValue(); 
String board_subject = ((String)request.getAttribute("board_subject"));
MemberDAO dao = new MemberDAO();
MemberBean mb=dao.getSelect(id);
	
%> 

</head>
<body>

<div class="wrap">

		  <jsp:include page="/menu.jsp" flush="false"/>
		<div class="logo">
		</div>
		
		<div class="content">
		<div class="appli1">
    	<table class="member" width=570 border="0" cellpadding="0" cellspacing="0"> 
        

    <tr class="tr1" align="center" valign="middle" > 
        <td>아이디 </td> 
        <td>이름</td> 
        <td>나이</td> 
        <td>성별</td>              
        <td>이메일</td>
    </tr>     
    <tr height="2" bgcolor="#e5e5e5"><td colspan="5"></td></tr>


    <% 
        for(int i=0 ; i<memberList.size() ; i++){ 
        	MemberBean bl=(MemberBean)memberList.get(i);                     
    %> 
    <tr class="tr2" align="center" valign="middle"   >  
        <td> 
            <a href="./Member_view.le?id=<%=bl.getMEMBER_ID()%>"> 
                <%=bl.getMEMBER_ID() %></a>             
        </td>         
        <td> 
            <%=bl.getMEMBER_NAME() %>
        </td> 
        <td> 
           <%=bl.getMEMBER_AGE() %> 
        </td> 
        <td> 
        	<%=bl.getMEMBER_GENDER() %>
        </td> 
        <td> 
            <%=bl.getMEMBER_EMAIL()%>
        </td> 
    </tr> 
    <%} %> 

	<td colspan="5">
	    <div class="pagination">
			 <%if(nowpage<=1){ %> <a href ="./memberList.le?page=<%=nowpage-1%>" class="direction"><span>Prev</span>
           	</a>&nbsp;         
            <%}else{ %> 
                <a href ="./memberList.le?page=<%=nowpage-1%>">Prev</a>&nbsp; 
            <%} %> 
             
            <%for(int a=startpage ; a<=endpage ; a++){  
                    if(a==nowpage){ %>  
                       <a href="./memberList.le?page=<%=a %>"><strong><%=a%></strong></a>&nbsp;  
               <%}else{ %> 
                <a href="./memberList.le?page=<%=a %>"><strong><%=a%></strong></a>&nbsp; 
                <%} %> 
             <%} %>  
              
             <%if(nowpage>=maxpage){ %> <a href ="./memberList.le?page=<%=nowpage+1%>" class="direction"><span>Next</span>
             </a>
            <%}else{ %> 
                <a href ="./memberList.le?page=<%=nowpage+1%>">Next</a> 
            <%} %>
      
             </td>
    </div>
	
</table>
</div>

  		</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>
</body>
</html>
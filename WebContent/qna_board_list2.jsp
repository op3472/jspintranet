<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="css/navi.css" />
<link rel="stylesheet" type="text/css" href="css/pagination.css" />
<%@page import="sun.java2d.loops.Blit"%> 
<%@page import="net.board.db.BoardBean"%> 
<%@page import="java.util.List"%> 
<% 

String id=null; 
if(session.getAttribute("id")!=null){ 
    id=(String)session.getAttribute("id"); 
}
List boardList =(List)request.getAttribute("boardlist"); 
int listcount=((Integer)request.getAttribute("listcount")).intValue(); 
int nowpage=((Integer)request.getAttribute("page")).intValue(); 
int maxpage=((Integer)request.getAttribute("maxpage")).intValue(); 
int startpage=((Integer)request.getAttribute("startpage")).intValue(); 
int endpage=((Integer)request.getAttribute("endpage")).intValue(); 
String board_subject = ((String)request.getAttribute("board_subject"));
%> 


</head>
<body>

<div class="wrap">

		  <jsp:include page="/menu.jsp" flush="false"/>
		<div class="logo">
		</div>
		
		<div class="content">
		<table class="board2" width=570 border="0" cellpadding="0" cellspacing="0"> 
    <tr align="center" valign="middle" bgcolor = "FAFAFA"> 
    <tr height="1" bgcolor="#e5e5e5"><td colspan="5"></td></tr>
        <td colspan="4" align="center">자유게시판</td> 
        <td align=right> Total : ${listcount}</td> 
    <tr height="1" bgcolor="#000000"><td colspan="5"></td></tr>    
    </table>
    	<table class="board" width=570 border="0" cellpadding="0" cellspacing="0"> 
        
    <tr height="1" bgcolor="#e5e5e5"><td colspan="5"></td></tr>

    <tr align="center" valign="middle" > 
        <td> 
            <div align="center"> 번호</div> 
        </td> 
        <td> 
            <div align="center"> 제목</div> 
        </td> 
        <td> 
            <div align="center"> 작성자</div> 
        </td> 
        <td> 
            <div align="center"> 날짜</div> 
        </td> 
        <td > 
            <div align="center"> 조회수</div> 
        </td>                 
    </tr>     
    <tr height="2" bgcolor="#e5e5e5"><td colspan="5"></td></tr>


    <% 
        for(int i=0 ; i<boardList.size() ; i++){ 
            BoardBean bl=(BoardBean)boardList.get(i);                     
    %> 
    <tr align="center" valign="middle"   > 
        <td><%=bl.getBOARD_NUM() %></td> 
        <td> 
            <div align="left"> 
            <%if(bl.getBOARD_RE_LEV()!=0){ %> 
                <%for(int a=0 ; a<=bl.getBOARD_RE_LEV()*2 ; a++){ %> 
                    &nbsp; 
                <%} %> 
                    <img src="images/reply_icon.gif">
            <%} %> 
            <a href="./BoardDetailAction.bo?num=<%=bl.getBOARD_NUM() %>"> 
                <%=bl.getBOARD_SUBJECT() %></a></div>                 
        </td>         
        <td> 
            <div align="center"><%=bl.getBOARD_ID() %></div> 
        </td> 
        <td> 
            <div align="center"><%=bl.getBOARD_DATE() %></div> 
        </td> 
        <td> 
            <div align="center"><%=bl.getBOARD_READCOUNT() %></div> 
        </td> 
    </tr> 
    <tr height="1" bgcolor="#e5e5e5"><td colspan="5"></td></tr>
    <%} %> 
    <tr height="1" bgcolor="#e5e5e5"><td colspan="5"></td></tr>
	<td colspan="5">
	<div class="pagination">
      
            <%if(nowpage<=1){ %> <a href ="./BoardList2.bo?page=<%=nowpage-1%>" class="direction"><span>Prev</span>
            </a>&nbsp;         
            <%}else{ %> 
                <a href ="./BoardList2.bo?page=<%=nowpage-1%>&board_subject=<%= board_subject%>">Prev</a>&nbsp; 
            <%} %> 
             
            <%for(int a=startpage ; a<=endpage ; a++){  
                    if(a==nowpage){ %>  
                       <a href="./BoardList2.bo?page=<%=a %>&board_subject=<%= board_subject%>"><strong><%=a%></strong></a>&nbsp;  
               <%}else{ %> 
                <a href="./BoardList2.bo?page=<%=a %>&board_subject=<%= board_subject%>"><strong><%=a%></strong></a>&nbsp; 
                <%} %> 
             <%} %>  
              
             <%if(nowpage>=maxpage){ %> <a href ="./BoardList2.bo?page=<%=nowpage+1%>&board_subject=<%= board_subject%>" class="direction"><span>Next</span>
             </a>
            <%}else{ %> 
                <a href ="./BoardList2.bo?page=<%=nowpage+1%>&board_subject=<%= board_subject%>">Next</a> 
            <%} %>
      
             </td>
    </div>
   <tr align="right"> 
        <td colspan="5"> 
			<% String boardsubject = request.getParameter("board_subject1"); %>    
        		<form action="./BoardList2.bo?page=<%=nowpage%> method="POST">
        	     
              	<span class ="green_window"><input type="text" class="input_text" name="board_subject"></span>
						   	
         	     <button type="submit" class="sch_smit" >검색</button>
     	
        		</form>
    </tr> 

</table>
  
  		</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>
</body>
</html>
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
request.setCharacterEncoding("EUC-KR");

String id=null; 
if(session.getAttribute("id")!=null){ 
    id=(String)session.getAttribute("id"); 
} 
List boardList =(List)request.getAttribute("lecturelist"); 
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
        <td> 수강과목 </td> 
        <td> 담당교수</td> 
        <td>  학점</td> 
        <td colspan="2">   평점 </td>              
        <td>
        </td>
    </tr>     

    <% 
        for(int i=0 ; i<boardList.size() ; i++){ 
            LectureBean bl=(LectureBean)boardList.get(i);                     
    %> 
    <tr class="tr2" align="center" valign="middle"   >  
        <td> 
            <a href="./LectureDetailAction.le?num=<%=bl.getLecture_num() %>"> 
                <%=bl.getLecture_subject() %></a>             
        </td>         
        <td> 
            <%=bl.getLecture_name() %>
        </td> 
        <td> 
           <%=bl.getLecture_credit() %> 
        </td> 
        <td class="grade"> 
        	<%if(bl.graderesult()>=5.0){ %>
           	 ★★★★★
           	<%}else if(bl.graderesult()>=4.0){ %>
           	 ★★★★☆
           	<%}else if(bl.graderesult()>=3.0){ %>
          	 ★★★☆☆
          	<%}else if(bl.graderesult()>=2.0){ %>
         	 ★★☆☆☆
         	<%}else if(bl.graderesult()>=1.0){ %>
         	 ★☆☆☆☆
         	<%}else if(bl.graderesult()>=0){ %>
        	 ☆☆☆☆☆
        	<%} %>
        </td> 
        <td>
        	<%
        		String format="#.#";
        		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
        		String result = df.format(bl.graderesult());
        		%>
            <%=result%>
        </td> 
        <td>
        	<a class="application" href="./applicationAction.le?num=<%=bl.getLecture_num()%>&id=<%=id %>">수강신청</a>
        </td>
    </tr> 
    <%} %> 

	<td colspan="5">
	    <div class="pagination">
			 <%if(nowpage<=1){ %> <a href ="./LectureList.le?page=<%=nowpage-1%>" class="direction"><span>Prev</span>
           	</a>&nbsp;         
            <%}else{ %> 
                <a href ="./LectureList.le?page=<%=nowpage-1%>">Prev</a>&nbsp; 
            <%} %> 
             
            <%for(int a=startpage ; a<=endpage ; a++){  
                    if(a==nowpage){ %>  
                       <a href="./LectureList.le?page=<%=a %>"><strong><%=a%></strong></a>&nbsp;  
               <%}else{ %> 
                <a href="./LectureList.le?page=<%=a %>"><strong><%=a%></strong></a>&nbsp; 
                <%} %> 
             <%} %>  
              
             <%if(nowpage>=maxpage){ %> <a href ="./LectureList.le?page=<%=nowpage+1%>" class="direction"><span>Next</span>
             </a>
            <%}else{ %> 
                <a href ="./LectureList.le?page=<%=nowpage+1%>">Next</a> 
            <%} %>
      
             </td>
    </div>
	 <tr align="right"> 
        <td colspan="5"> 
			<% String lecture_name = request.getParameter("lecture_name"); %>    
        		<form action="./LectureList2.le?page=<%=nowpage%>" method="POST">
        	     
              	<span class ="green_window"><input type="text" class="input_text" name="lecture_name"></span>
						   	
         	     <button type="submit" class="sch_smit">검색</button>
     			        	    			
        		</form>
    </tr> 
</table>
</div>
  <div class="appli2">
    	<table class="board" width=570 border="0" cellpadding="0" cellspacing="0"> 
      
	<tr>
	<td colspan="4" align="center" valign="middle">
	<p class="bold">나의 수강신청목록</p>
	</td>
	</tr>
    <tr class="tr1" align="center" valign="middle" > 
        <td> 수강과목 </td> 
        <td> 담당교수</td> 
        <td>  학점</td> 
        <td> </td>
    </tr>     

    <% 
        for(int i=0 ; i<memberList.size() ; i++){ 
            memberBean ml=(memberBean)memberList.get(i);                     
    %> 
    <tr class="tr2" align="center" valign="middle"   >  
        <td>  
              <%=ml.getLecture_subject() %>            
        </td>         
        <td> 
            <%=ml.getLecture_name() %>
        </td> 
        <td> 
           <%=ml.getLecture_credit() %> 
        </td> 
        <td>
        	<a class="application" href="./applideleteAction.le?num=<%=ml.getLecture_num()%>&id=<%=id %>">수강취소</a>
        </td>
    </tr> 
    <%} %> 
</table>
</div>
  		</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>
</body>
</html>
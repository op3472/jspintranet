<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="./css/navi.css" />
<link rel="stylesheet" type="text/css" href="./css/detail.css" />
<link rel="stylesheet" type="text/css" href="css/pagination.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="lecture.db.LectureBean"%>

<% 
int listcount=((Integer)request.getAttribute("listcount")).intValue(); 
int nowpage=((Integer)request.getAttribute("page")).intValue(); 
int maxpage=((Integer)request.getAttribute("maxpage")).intValue(); 
int startpage=((Integer)request.getAttribute("startpage")).intValue(); 
int endpage=((Integer)request.getAttribute("endpage")).intValue(); 
int num = Integer.parseInt(request.getParameter("num")); 
String id=null; 
if(session.getAttribute("id")!=null){ 
    id=(String)session.getAttribute("id"); 
} 
LectureBean lecture = (LectureBean)request.getAttribute("lecturedata"); 

%> 


        
 <script type="text/javascript">
 var httpRequest = null;
 
 // httpRequest 객체 생성
 function getXMLHttpRequest(){
     var httpRequest = null;
 
     if(window.ActiveXObject){
         try{
             httpRequest = new ActiveXObject("Msxml2.XMLHTTP");    
         } catch(e) {
             try{
                 httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
             } catch (e2) { httpRequest = null; }
         }
     }
     else if(window.XMLHttpRequest){
         httpRequest = new window.XMLHttpRequest();
     }
     return httpRequest;    
 }
 
 // 댓글 등록
 function writeCmt()
 {
     var form = document.getElementById("writeCommentForm");
     
     var board = form.comment_board.value
     var id = form.comment_id.value
     var content = form.comment_content.value;
     var foods = document.getElementsByName('grade');
     var grade; // 여기에 선택된 radio 버튼의 값이 담기게 된다.
     for(var i=0; i<foods.length; i++) {
         if(foods[i].checked) {
             grade = foods[i].value;
         }
     }

     if(!content)
     {
         alert("내용을 입력하세요.");
         return false;
     }
     else
     {    
         var param="comment_board="+board+"&comment_id="+id+"&comment_content="+content+"&grade="+grade;
             
         httpRequest = getXMLHttpRequest();
         httpRequest.onreadystatechange = checkFunc;
         httpRequest.open("POST", "CommentWriteAction.le", true);    
         httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'); 
         httpRequest.send(param);
     }
 }
 function checkFunc(){
     if(httpRequest.readyState == 4){
         // 결과값을 가져온다.
         var resultText = httpRequest.responseText;
         if(resultText == 1){ 
             document.location.reload(); // 상세보기 창 새로고침
         }
     }
 }
 function cmDeleteOpen(comment_num){
     var msg = confirm("코멘트을 삭제합니다.");    
     if(msg == true){ // 확인을 누를경우
         deleteCmt(comment_num);
     }
     else{
         return false; // 삭제취소
     }
 }
 function deleteCmt(num)
 {
	 var board="<%= num%>"
	 var param="num="+num+"&board="+board;
	     
     httpRequest = getXMLHttpRequest();
     httpRequest.onreadystatechange = checkFunc;
     httpRequest.open("POST", "commentdelete.le", true);    
     httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'); 
     httpRequest.send(param);
 }
 function cmUpdateOpen(num){
     window.name = "parentForm";
     window.open("CommentUpdateFormAction.le?num="+num,
                 "updateForm", "top=200 ,left=500, width=570, height=350, resizable = no, scrollbars = no");
 }
    </script>


</head>
<body>
	<div class="wrap">
		  <jsp:include page="/menu.jsp" flush="false"/>
		<div class="logo">
		</div>
		<div class="content">
		 <table class="board" cellpadding="0" cellspacein="0"> 
     
    <tr> 
        <td> 
            <div align="center">수강과목&nbsp;&nbsp;</div> 
        </td>         
        <td>  
            <%=lecture.getLecture_subject() %>  
        </td>         
    </tr>
    <tr> 
        <td> 
            <div align="center">담당교수&nbsp;&nbsp;</div> 
        </td>         
        <td>  
            <%=lecture.getLecture_name() %>  
        </td>         
    </tr> 
     <tr> 
        <td> 
            <div align="center">학점&nbsp;&nbsp;</div> 
        </td>         
        <td>  
            <%=lecture.getLecture_credit() %>  
        </td>         
    </tr>   
    <tr bgcolor="ccccc"> 
        <td colspan="2" style="height:1px"></td> 
    </tr> 
    <tr> 
        <td> 
            <div align="center">내 용</div> 
        </td> 
        <td> 
            <table border=0 width=490 height=250 style="table-layout:fixed"> 
                <tr> 
                    <td valign=top> 
                        <%=lecture.getLecture_content() %> 
                    </td> 
                </tr> 
            </table>     
        </td> 
    </tr> 
    <tr> 
        
    </tr> 
    <tr align="center" valign="middle"> 
        <td colspan="5"> 
            <font size=2> 
                <a class="application2" href="./applicationAction.le?num=<%=num%>&id=<%=id %>">수강신청</a> 
                          
            </font> 
        </td> 
    </tr> 
 </table> 
    <table class="comment"   cellpadding="0" cellspacein="0">
    <!-- 댓글 목록 -->    
    <c:if test="${requestScope.commentList != null}">
        <c:forEach var="comment" items="${requestScope.commentList}">
            <tr >
            	 <td align="center" valign="middle">
                    <div>
                    <div class="grade">
                    	 <c:choose>
                 <c:when test="${comment.lecture_grade>=5.0}">
                       		★★★★★
                    </c:when>
                       <c:when test="${comment.lecture_grade>=4.0}">
                       		 ★★★★☆
                    </c:when>  
                    <c:when test="${comment.lecture_grade>=3.0}">
                          	★★★☆☆
                    </c:when>
                      <c:when test="${comment.lecture_grade>=2.0}">
                       			★★☆☆☆
                    </c:when>
                      <c:when test="${comment.lecture_grade>=1.0}">
                       		 ★☆☆☆☆
                    </c:when>
                      <c:when test="${comment.lecture_grade>=0}">
                       		 ☆☆☆☆☆
                    </c:when>
                    
                    </c:choose> 
                    </div>
                    </br>  
                           ${comment.lecture_date}
                        ${comment.lecture_id}<br>
                      <div>
                 
                </td>
                <!-- 아이디, 작성날짜 -->
  
                <!-- 본문내용 -->
                <td >
                    <div class="text_wrapper">
                    
                        ${comment.lecture_content}
                    </div>
                </td>
                <!-- 버튼 -->
                <td align="center" valign="middle">
                    <div id="btn" style="text-align:center;">

                    <!-- 댓글 작성자만 수정, 삭제 가능하도록 -->    
                      <c:choose>
                 <c:when test="${comment.lecture_id == sessionScope.id}">
                        <a href="#" onclick="cmUpdateOpen(${comment.lecture_num})"><img src="./images/btn_e.jpg"></a><br>    
                        <a href="#" onclick="cmDeleteOpen(${comment.lecture_num})"><img src="./images/btn_d1.jpg"></a>
                    </c:when>
                     <c:when test="${grade == 2}">
                        <a href="#" onclick="cmDeleteOpen(${comment.lecture_num})"><img src="./images/btn_d1.jpg"></a>
                    </c:when>
                    </c:choose>   
                         
                    </div>
                      
                </td>
            </tr>
            
        </c:forEach>
        	<td colspan="5">
	<div class="pagination">
      
            <%if(nowpage<=1){ %> <a href ="./LectureDetailAction.le?page=<%=nowpage-1%>&num=<%= num%>" class="direction"><span>Prev</span>
            </a>&nbsp;         
            <%}else{ %> 
                <a href ="./LectureDetailAction.le?page=<%=nowpage-1%>&num=<%= num%>">Prev</a>&nbsp; 
            <%} %> 
             
            <%for(int a=startpage ; a<=endpage ; a++){  
                    if(a==nowpage){ %>  
                       <a href="./LectureDetailAction.le?page=<%=a %>&num=<%= num%>"><strong><%=a%></strong></a>&nbsp;  
               <%}else{ %> 
                <a href="./LectureDetailAction.le?page=<%=a %>&num=<%= num%>"><strong><%=a%></strong></a>&nbsp; 
                <%} %> 
             <%} %>  
              
             <%if(nowpage>=maxpage){ %> <a href ="./LectureDetailAction.le?page=<%=nowpage+1%>&num=<%= num%>" class="direction"><span>Next</span>
             </a>
            <%}else{ %> 
                <a href ="./LectureDetailAction.le?page=<%=nowpage+1%>&num=<%= num%>">Next</a> 
            <%} %>
      
             </td>
    </div>
    </c:if>
            
            <!-- 로그인 했을 경우만 댓글 작성가능 -->
            <c:if test="${sessionScope.id !=null}">
            <tr>
            <form id="writeCommentForm">
                <input type="hidden" name="comment_board" value=<%=Integer.toString(num) %>>
                <input type="hidden" name="comment_id" value="${sessionScope.id}">
                <!-- 아이디-->
                <td align="center" valign="middle">
                    <div>
                        ${sessionScope.id}
                    </div>
                </td>
                <!-- 본문 작성-->
                <td >
                    <div>
                        <textarea name="comment_content" rows="4" cols="60" ></textarea>
                    </div>
                </td>
                <!-- 댓글 등록 버튼 -->
                <td align="center" valign="middle">
                    <div id="btn" style="text-align:center;">
                        <p><a href="#" onclick="writeCmt()"><img src="images/btn_r2.gif"></a></p>    
                    </div>
                </td>
            
            </tr>
            <tr>
            <td class="grade2" colspan="3" align="center" valign="middle">
            	<label for="ck_1">★★★★★</label>
            	<input type="radio" name="grade" value="5" id="ck_1" checked>
            	<label for="ck_2">★★★★☆</label>
            	<input type="radio" name="grade" value="4" id="ck_2">
            	<label for="ck_3">★★★☆☆</label>
            	<input type="radio" name="grade" value="3" id="ck_3">
            	<label for="ck_4">★★☆☆☆</label>
            	<input type="radio" name="grade" value="2" id="ck_4">
            	<label for="ck_5">★☆☆☆☆</label>
            	<input type="radio" name="grade" value="1" id="ck_5">
            	<label for="ck_6">☆☆☆☆☆</label>
            	<input type="radio" name="grade" value="0" id="ck_6" >
            	
            </td>
            </tr>
            </form>
            </c:if>
    
        </table>
</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>

</body>
</html>
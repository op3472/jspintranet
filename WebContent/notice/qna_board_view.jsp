<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/navi.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="net.board.db.BoardBean"%> 
<%
		BoardBean board = (BoardBean)request.getAttribute("boarddata"); 

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
            
            if(!content)
            {
                alert("내용을 입력하세요.");
                return false;
            }
            else
            {    
                var param="comment_board="+board+"&comment_id="+id+"&comment_content="+content;
                    
                httpRequest = getXMLHttpRequest();
                httpRequest.onreadystatechange = checkFunc;
                httpRequest.open("POST", "CommentnoticeWriteAction.co", true);    
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
        function cmReplyOpen(comment_num)
        {
            var userId = '${sessionScope.id}';
            
            if(userId == "" || userId == null){
                alert("로그인후 사용가능합니다.");
                return false;
            }
            else{
                // 댓글 답변창 open
                window.name = "parentForm";
                window.open("CommentnoticeReplyFormAction.co?num="+comment_num,
                            "replyForm", "top=200 ,left=500,width=570, height=350, resizable = no, scrollbars = no");
            }
        }
        function cmUpdateOpen(comment_num){
            window.name = "parentForm";
            window.open("CommentnoticeUpdateFormAction.co?num="+comment_num,
                        "updateForm", "top=200 ,left=500, width=570, height=350, resizable = no, scrollbars = no");
        }
        function cmDeleteOpen(comment_num){
            var msg = confirm("댓글을 삭제합니다.");    
            if(msg == true){ // 확인을 누를경우
                deleteCmt(comment_num);
            }
            else{
                return false; // 삭제취소
            }
        }
        function deleteCmt(comment_num)
        {
            var param="comment_num="+comment_num;
            
            httpRequest = getXMLHttpRequest();
            httpRequest.onreadystatechange = checkFunc;
            httpRequest.open("POST", "CommentnoticeDeleteAction.co", true);    
            httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=EUC-KR'); 
            httpRequest.send(param);
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
    <tr align="center" valign="middle"> 
        <td colspan="5">공지사항</td> 
    </tr>  
    <tr> 
        <td> 
            <div align="center">제목 &nbsp;&nbsp;</div> 
        </td>         
        <td>  
            <%=board.getBOARD_SUBJECT() %>  
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
                        <%=board.getBOARD_CONTENT() %> 
                    </td> 
                </tr> 
            </table>     
        </td> 
    </tr> 
    <tr> 
        <td> 
            <div align="center">첨부파일</div> 
        </td> 
        <td> 
            <%if(!(board.getBOARD_FILE()==null)){ %> 
                <a href="#" onclick="location.href='filedown.jsp?file=<%=board.getBOARD_FILE() %>'"value="<%= board.getBOARD_FILE()%>"> 
                    <%=board.getBOARD_FILE() %> 
                </a> 
                <%} %> 
        </td> 
    </tr> 
    <tr bgcolor="cccccc"> <td colspan="2" style="height:1px;"></td> </tr> 
    <tr> <td colspan="2">&nbsp;</td>    </tr> 
    <tr align="center" valign="middle"> 
        <td colspan="5"> 
            <font size=2> 
                <a href="./NoticeModify.bo?num=<%=board.getBOARD_NUM() %>"><img src="images/btn_e.jpg"></a>                 
                <a href="./NoticeDelete.bo?num=<%=board.getBOARD_NUM() %>"><img src="images/btn_d1.jpg"></a> 
                <a href="./NoticeList.bo"><img src="images/btn_l.jpg"></a>                 
            </font> 
        </td> 
    </tr> 
 </table> 
     <table class="comment"   cellpadding="0" cellspacein="0">
    <!-- 댓글 목록 -->    
    <c:if test="${requestScope.commentList != null}">
        <c:forEach var="comment" items="${requestScope.commentList}">
        	
            <tr>
                <!-- 아이디, 작성날짜 -->
                <td align="center" valign="middle">
                    <div>
                        ${comment.comment_date}
                        ${comment.comment_id}<br>
                      
                    </div>
                </td>
                <!-- 본문내용 -->
                <td >
                    <div class="text_wrapper">
                    	 <c:if test="${comment.comment_lev >= 1}">
                    	 <c:forEach var="a" begin="1" end="${comment.comment_lev}" step="1">
                    	 &nbsp; &nbsp;  
                		 </c:forEach>
                            <img src="images/reply_icon.gif">
                        </c:if>
                        ${comment.comment_content}
                    </div>
                </td>
                <!-- 버튼 -->
                <td align="center" valign="middle">
                    <div id="btn" style="text-align:center;">
                        <a href="#" onclick="cmReplyOpen(${comment.comment_num})"><img src="images/btn_r.png"></a><br>
                    <!-- 댓글 작성자만 수정, 삭제 가능하도록 -->    
                          <c:choose>
                 <c:when test="${comment.comment_id == sessionScope.id}">
                        <a href="#" onclick="cmUpdateOpen(${comment.comment_num})"><img src="images/btn_e.jpg"></a><br>    
                        <a href="#" onclick="cmDeleteOpen(${comment.comment_num})"><img src="images/btn_d1.jpg"></a>
                    </c:when>
                     <c:when test="${grade == 2}">
                        <a href="#" onclick="cmDeleteOpen(${comment.comment_num})"><img src="images/btn_d1.jpg"></a>
                    </c:when>
                    </c:choose>   
                              
                    </div>
                </td>
            </tr>
            
        </c:forEach>
    </c:if>
            
            <!-- 로그인 했을 경우만 댓글 작성가능 -->
            <c:if test="${sessionScope.id !=null}">
            <tr>
            <form id="writeCommentForm">
                <input type="hidden" name="comment_board" value=<%=Integer.toString(board.getBOARD_NUM()) %>>
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
            </form>
            </tr>
            </c:if>
    
        </table>

</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>

</body>
</html>
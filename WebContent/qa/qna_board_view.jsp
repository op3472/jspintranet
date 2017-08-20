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
                httpRequest.open("POST", "CommentWriteAction.co", true);    
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
                window.open("CommentReplyFormAction.co?num="+comment_num,
                            "replyForm", "top=200 ,left=500,width=570, height=350, resizable = no, scrollbars = no");
            }
        }
        function cmUpdateOpen(comment_num){
            window.name = "parentForm";
            window.open("CommentUpdateFormAction.co?num="+comment_num,
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
            httpRequest.open("POST", "CommentDeleteAction.co", true);    
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
        <td colspan="5">Q&A게시판</td> 
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
            <c:if test="${grade == 2}">
                <a href="./QAReplyAction.bo?num=<%=board.getBOARD_NUM() %>"><img src="images/btn_r.png"></a>
             </c:if> 
                <a href="./QAModify.bo?num=<%=board.getBOARD_NUM() %>"><img src="images/btn_e.jpg"></a>                 
                <a href="./QADelete.bo?num=<%=board.getBOARD_NUM() %>"><img src="images/btn_d1.jpg"></a> 
                <a href="./QAList.bo"><img src="images/btn_l.jpg"></a>                 
            </font> 
        </td> 
    </tr> 
 </table> 

</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>

</body>
</html>
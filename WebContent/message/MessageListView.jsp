<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="./js/message.js"></script>
<link rel="stylesheet" type="text/css" href="./css/pagination.css" />
<link rel="stylesheet" type="text/css" href="./css/message.css" />
<%@page import="java.util.List"%>
<%@page import="message.db.MessageBean"%>
<title>Insert title here</title>
<script type="text/javascript">
function checkAll(){
    if( $("#th_checkAll").is(':checked') ){
      $("input[name=checkRow]").prop("checked", true);
    }else{
      $("input[name=checkRow]").prop("checked", false);
    }
}

function deleteAction(){
	  var checkRow = "";
	  $( "input[name='checkRow']:checked" ).each (function (){
	    checkRow = checkRow + $(this).val()+"," ;
	  });
	  checkRow = checkRow.substring(0,checkRow.lastIndexOf( ",")); //맨끝 콤마 지우기
	 
	  if(checkRow == ''){
	    alert("삭제할 대상을 선택하세요.");
	    return false;
	  }
	  if(confirm("쪽지를 삭제 하시겠습니까?")){
	      location.href="MessageCheckboxDelete.ms?idx="+checkRow;
	  }
	}
</script>
</head>
<body>
	<table width=600 border="0" cellpadding="0" cellspacing="0"> 
    <tr align="center" valign="middle" bgcolor = "FAFAFA"> 
    <tr height="1" bgcolor="#e5e5e5"><td colspan="5"></td></tr>
        <td colspan="4" align="center">쪽지함</td> 
        <td align=right> Total : ${listcount}</td> 
    <tr height="1" bgcolor="#000000"><td colspan="5"></td></tr>    
    </table>
    	<table width=600 border="0" cellpadding="0" cellspacing="0"> 
        
    <tr height="1" bgcolor="#e5e5e5"><td colspan="5"></td></tr>

    <tr align="center" valign="middle" > 
        <th width="150"> 
            <div align="center">보낸이</div> 
        </th> 
        <th> 
            <div align="center">제목</div> 
        </th> 
        <th width="150"> 
            <div align="center"> 날짜</div> 
        </th>
        <th width="50">
        	<input type="checkbox" name="checkAll" id="th_checkAll" onclick="checkAll();">
        </th>
    </tr>     
    <tr height="2" bgcolor="#e5e5e5"><td colspan="5"></td></tr>

	<c:forEach var="row" items="${list}">
	<c:if test="${row.messageReadcheck == 1}">
    <tr align="center" valign="middle"> 
        <td> 
             <div align="center"><strong>${row.messageSender}</strong></div>        
        </td>         
        <td> 
        	<div align="left"><strong><a href="./MessageDetail.ms?num=${row.messageId}">${row.messageSubject}</a></strong></div>
        </td> 
        <td> 
            <div align="center"><strong><fmt:formatDate value="${row.messageDate}"  pattern="yyyy.MM.dd"/></strong></div>  
        </td> 
        <td>
        	<input type="checkbox" name="checkRow" value="${row.messageId}">
        </td>
    </tr> 
    <tr height="1" bgcolor="#e5e5e5"><td colspan="5"></td></tr>
    </c:if>
    	<c:if test="${row.messageReadcheck == 2}">
    <tr align="center" valign="middle"> 
        <td> 
             <div align="center">${row.messageSender}</div>        
        </td>         
        <td> 
        	<div align="left"><a href="./MessageDetail.ms?num=${row.messageId}">${row.messageSubject}</a></div>
        </td> 
        <td> 
            <div align="center"><fmt:formatDate value="${row.messageDate}"  pattern="yyyy.MM.dd"/></div>  
        </td> 
    </tr> 
    <tr height="1" bgcolor="#e5e5e5"><td colspan="5"></td></tr>
    </c:if>
	</c:forEach>
    <tr height="1" bgcolor="#e5e5e5"><td colspan="5"></td></tr>
	<td colspan="5">
	
   <tr align="right"> 
        <td colspan="5">
        		<div class="pagination">
         <c:if test="${page>1}">
			<a href="./MessageListView.ms?page=${page-1}">이전</a>
		</c:if>
		<c:forEach var="pno" begin="${startpage}" end="${endpage}">
		<c:choose>
        <c:when test="${pno==page}">
            <a href="./MessageListView.ms?page=${pno}"><strong>${pno}</strong></a>
       </c:when>
        <c:otherwise>
       		<a href="./MessageListView.ms?page=${pno}">${pno}</a>
        </c:otherwise>
    </c:choose>
		</c:forEach>
		<c:if test="${page<maxpage}">
			<a href="./MessageListView.ms?page=${page+1}" class="direction">다음</a>
		</c:if>
    </div>
        </td>
        		
    </tr>
       <tr align="right"> 
        <td colspan="5">
        		<input type="button" onclick="location.href='./message/MessageWriteForm.jsp'" value="쪽지보내기">
 				<input type="button" onclick="deleteAction()" value="쪽지삭제">
        		<input type="button" onclick="reloadclose()" value="닫기">
  		</td>
    </tr> 
     
</table>
</body>
</html>
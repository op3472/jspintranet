<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="css/navi.css" />
<%@page import="net.board.db.BoardBean"%> 
<% 
    String id=(String)session.getAttribute("id"); 
    BoardBean board=(BoardBean)request.getAttribute("boarddata"); 
%> 

</head>
<body>
	<div class="wrap">
		  <jsp:include page="/menu.jsp" flush="false"/>
		<div class="logo">
		</div>
		<div class="content">
		<form name="boardform" action="./QAReplyView.bo" method="post" > 
    <input type="hidden" name="BOARD_NUM" value="<%=board.getBOARD_NUM() %>"> 
    <input type="hidden" name="BOARD_RE_REF" value="<%=board.getBOARD_RE_REF() %>"> 
    <input type="hidden" name="BOARD_RE_LEV" value="<%=board.getBOARD_RE_LEV() %>"> 
    <input type="hidden" name="BOARD_RE_SEQ" value="<%=board.getBOARD_RE_SEQ() %>"> 
    <input type="hidden" name="BOARD_ID" value="<%=id%>"> 

<table class="board" cellpadding="0" cellspacing="0"> 
    <tr align="center" valign="middle"> 
        <td colspan="5"><input type="image" src="images/title_free.gif"/></td>     
    </tr> 
    <tr> 
        <td> 
        <div align="center">글쓴이</div></td>     
        <td><%=id %></td>     
    </tr> 
    <tr> 
        <td> 
            <div align="center">제목</div> 
        </td> 
        <td> 
            <input name="BOARD_SUBJECT" type="text" size="50" maxlength="100" value="Re:<%=board.getBOARD_SUBJECT()%>"/>             
        </td>         
    </tr> 
    <tr> 
        <td><div align="center">내용</div></td> 
        <td> 
            <textarea name="BOARD_CONTENT" cols="67" rows="15" align="left">RE:<%=board.getBOARD_CONTENT() %></textarea> 
        </td> 
    </tr> 
    <tr> 
        <td><div align="center">비밀번호</div></td> 
        <td><input name="BOARD_PASS" type="password"></td> 
    </tr> 
    <tr bgcolor="cccccc"> <td colspan="2" style="height:1px"> </td> </tr> 
    <tr><td colspan="2"> &nbsp;</td></tr> 
    <tr align="center" valign="middel"> 
        <td colspan="5"> 
            <a href="javascript:boardform.submit()"><img src="images/btn_r.png"></a>&nbsp;&nbsp; 
            <a href ="javascript:history.go(-1)"><img src="images/btn_l.jpg"></a> 
        </td> 
    </tr>     
</table> 
</form> 
		</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>

</body>
</html>
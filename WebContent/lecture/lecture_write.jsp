<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="./css/navi.css" />
<script type="text/javascript">
function checkValue()
{
	var form = document.boardform;
	var chkStyle = /\d/;
	if(!form.LECTURE_SUBJECT.value){
		alert("과목을 입력하세요.");
		return false;
	}
	if(!form.LECTURE_NAME.value){
		alert("담당교수을 입력하세요.");
		return false;
	}
	if(!chkStyle.test(form.LECTURE_CREDIT.value)){
		alert("학점을 숫자로 입력하세요.");
		return false;
	}
	
	if(!form.LECTURE_CONTENT.value){
		alert("내용을 입력하세요.");
		return false;
	}
}

</script>

<% 
    String id=(String)session.getAttribute("id"); 
%> 

</head>
<body>
	<div class="wrap">
		  <jsp:include page="/menu.jsp" flush="false"/>
		<div class="logo">
		</div>
		<div class="content">
			<form  name="boardform" action="./LectureAddAction.le" method="post"  onsubmit="return checkValue()"> 

<table class="board" cellpadding="0" cellspacing="0"> 
      <tr> 
        <td> 
            <div align="center">과목</div> 
        </td> 
        <td> 
            <input name="LECTURE_SUBJECT" type="text" size="20" maxlength="100"/> 
        </td>         
    </tr> 
    <tr> 
        <td> 
            <div align="center">담당교수</div> 
        </td> 
        <td> 
            <input name="LECTURE_NAME" type="text" size="20" maxlength="100" /> 
        </td>     
    </tr> 
    <tr> 
         <td> 
            <div align="center">학점</div> 
        </td> 
        <td> 
            <input name="LECTURE_CREDIT" type="text" size="20" maxlength="100" /> 
        </td>  
    </tr> 
      <tr> 
           <td> 
            <div align="center">과목설명</div> 
        </td> 
          <td> 
            <textarea name="LECTURE_CONTENT" cols="67" rows="15"></textarea>             
        </td>    
    </tr> 
    <tr bgcolor="cccccc"> 
        <td colspan="2" style="height:1px;"></td> 
    </tr> 
     
    <tr><td colspan="2">&nbsp;</td></tr> 
    <tr align="center" valign="middle"> 
        <td colspan="5"> 
           	<input type="image" src="images/btn_save.gif"/>&nbsp;&nbsp;
            <a href="javascript:history.go(-1)"><img src="images/btn_cancel.gif"></a>            
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
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="css/navi.css" />
<link rel="stylesheet" type="text/css" href="css/modify.css" />
<%@page import="net.member.db.MemberBean"%> 
<%@page import="net.member.db.MemberDAO"%> 
<script type="text/javascript">
function checkValue()
{
	var form = document.modiform;
	// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
	if(form.MEMBER_PW.value != form.MEMBER_PWC.value){
		alert("비밀번호를 동일하게 입력하세요.");
		return false;
	}	
	
}
</script>
<style type="text/css">
h1, h2, h3, h4, h5, h6 {  
	font-size:100%;   
	font-weight:normal;   
	} 
fieldset, img, abbr,acronym { border:0 none; } 

ol, ul { list-style:none; }


table {
	border-collapse: separate;
	border-spacing:0;
	border:0 none;
	}
caption, th, td {
	text-align:left;
	font-weight: normal;
	border:0;
}


address, caption, strong, em, cite {
	font-weight:normal;
	font-style:normal;
	}
ins { text-decoration:none; }
del { text-decoration:line-through; }


blockquote:before, blockquote:after, q:before, q:after { content:""; }
blockquote,q { quotes:"" ""; }


hr { display:none; }


.modimain {
	margin:100px 150px 0;
	}
</style>
</head>
<body>
<% 
	String id= (String)session.getAttribute("id");
	MemberDAO dao = new MemberDAO();
	MemberBean mb=dao.getSelect(id);
%>
<div class="wrap">
		  <jsp:include page="/menu.jsp" flush="false"/>
		<div class="logo">
		</div>
		<div class="content">
		<form class="modimain" name="modiform"action="modify.jsp" method="post" onsubmit="return checkValue()">    
          <table width="450px" border="1" cellspacing="0" cellpadding="0" class="joinData">
           <tr>
            
			<img src="images/modify_img.gif">
			
			<div class="upper">비밀번호를 한번 더 입력해주세요</div></p>
			회원님의 정보를 안전하게 보호하기 위해 비밀번호를 한번 </p>더 확인합니다
			<th scope="row">
                <label for="username">아이디</label></th>
              <td class="td1">
              	<%= mb.getMEMBER_ID() %>
    		  </td>
    		 
            </tr>
            <tr>
              <th>
              <label for="passwd">password</label></th>
              <td class="inform">
                <input type="password" name="MEMBER_PW" id="passwd" class="textBox"  maxlength="15" required onkeypress="check_capslock('confirm_mb_password');"/></td>
           		<input type="hidden" name="MEMBER_PWC" value="<%= mb.getMEMBER_PW() %>" />
        </table>
         </form>
		</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>
</body>
</html>
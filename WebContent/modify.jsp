<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="css/modify.css" />
<link rel="stylesheet" type="text/css" href="css/navi.css" />
<script type="text/javascript" charset="utf-8" src="js/modify.js"></script>
<%@page import="net.member.db.MemberBean"%> 
<%@page import="net.member.db.MemberDAO"%> 
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
		<form class="modify" name="joinform" action="./MemberModifyAction.me" method="post" onsubmit="return checkValue()">    
          <table width="700px" border="1" cellspacing="0" cellpadding="0" class="joinData">
			<tr>
              <th scope="row">
                <label for="username">아이디</label></th>
              <td class="td1">
              	<%= mb.getMEMBER_ID() %>
    		  </td>
    		  <input type="hidden" name="MEMBER_ID" value="<%= mb.getMEMBER_ID() %>" />
            </tr>
            <tr>
              <th>
              <label for="passwd">패스워드</label></th>
              <td class="inform">
                <input type="password" name="MEMBER_PW" id="passwd" class="textBox"  maxlength="16" /></td>
            </tr>
        
             <tr>
              <th>
              <label for="name">이름</label></th>
              <td><input type="text" name="MEMBER_NAME" id="name" class="textBox"  maxlength="15" value="<%= mb.getMEMBER_NAME() %>" /></td>
            </tr>
			  <tr>
              <th>
              <label for="age">나이</label></th>
              <td><input type="text" name="MEMBER_AGE" id="age" class="textBox" value="<%= mb.getMEMBER_AGE() %>"/></td>
            </tr>
			
              <th><label for="gender" class="gender">성별</label></th>
              <td class="mailing">
			  <input name="MEMBER_GENDER" type="radio" id="receivecd" value="남" checked="checked" />
                <label for="receivecd">남자 </label>
                <input type="radio" name="MEMBER_GENDER" id="receivecd2" value="여	" />
                <label for="receivecd2">여자</label></td>
            </tr>
			<tr>
              <th>
              <label for="email">이메일 주소</label></th>
              <td><input type="text" name="MEMBER_EMAIL" id="email" class="textBox"  maxlength="30"  value="<%= mb.getMEMBER_EMAIL()%>"/></td>
            </tr>
			<tr> 
            <td colspan="2">
				<p class="btn">
				<input class="btn_mo" type="submit" value="정보수정" class="btn_mo">
				</p>
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
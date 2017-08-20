<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="css/member.css" />
<script type="text/javascript" charset="utf-8" src="js/join.js"></script>
</head>
<body>
<form name="joinform" action="./MemberJoinAction.me" method="post" onsubmit="return checkValue()">    
          <table width="450px" border="1" cellspacing="0" cellpadding="0" class="joinData">
			<tr>
              <th scope="row">
                <label for="username">아이디</label></th>
              <td class="td1">
              	<input type="text" name="MEMBER_ID" id="username" class="textBox" maxlength="15" onkeydown="inputIdChk()"/>
              	<a onclick="openIdChk()"><img src="images/btn_id_doubleCheck.gif" alt="id중복확인" /></a>
              	<input type="hidden" name="idDuplication" value="idUncheck">
              	<input type="hidden" name="MEMBER_GRADE" value="1">
              </td>
            </tr>
            <tr>
              <th>
              <label for="passwd">패스워드</label></th>
              <td class="inform"><p class="password">영문/숫자 8~15자 이내</p>
                <input type="password" name="MEMBER_PW" id="passwd" class="textBox"  maxlength="15" /></td>
            </tr>
            <tr>
              <th>
              <label for="repassword">패스워드 확인</label></th>
              <td><input type="password" name="repassword" id="repassword" class="textBox"  maxlength="10"/></td>
            </tr>
             <tr>
              <th>
              <label for="name">이름</label></th>
              <td><input type="text" name="MEMBER_NAME" id="name" class="textBox"  maxlength="15" /></td>
            </tr>
			  <tr>
              <th>
              <label for="age">나이</label></th>
              <td><input type="text" name="MEMBER_AGE" id="age" class="textBox" /></td>
            </tr>
			
              <th><label for="gender" class="gender">성별</label></th>
              <td class="mailing">
			  <input name="MEMBER_GENDER" type="radio" id="receivecd" value="남" checked="checked" />
                <label for="receivecd">남자 </label>
                <input type="radio" name="MEMBER_GENDER" id="receivecd2" value="여" />
                <label for="receivecd2">여자</label></td>
            </tr>
			<tr>
              <th>
              <label for="email">이메일 주소</label></th>
              <td><input type="text" name="MEMBER_EMAIL" id="email" class="textBox"  maxlength="30" /></td>
            </tr>
			<tr> 
            <td colspan="2"> 
				<p class="btn">
				<input type="image" src="images/btn_join.gif"/>  
				</p>
            </td> 
        </tr>     
         </table>
         </form>
</body>
</html>
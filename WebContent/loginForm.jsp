<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="css/login.css" />
<link rel="stylesheet" type="text/css" href="css/navi.css" />
<link rel="stylesheet" type="text/css" href="css/login2.css" />

<script type="text/javascript">
function popupOpen(){

	var popUrl = "joinForm.jsp";

	var popOption = "top=200 ,left=500,width=450, height=360, resizable=yes, scrollbars=yes, status=no;";    //팝업창 옵션(optoin)

		window.open(popUrl,"",popOption);
}
</script>

</head>
<body>
	<div class="wrap">

	<img src="images/banner.PNG">
	<div class="loginForm">

	<form id="loginForm" action="./MemberLoginAction.me" method="post">

<div class="box">
   <input type="text" class="iText" name="MEMBER_ID" placeholder="Type your username" autofocus required>
   <br>
   <input type="password" class="iText"name="MEMBER_PW" placeholder="Type your password" required>
   <br>
   <p>
   <span class="fright"><input type="button" class="signUpButton" onclick="popupOpen()" value="회원가입"></span>
   </p>
   <input type="submit" value="로그인" class="loginBtn">
   </div>
</form>

</div>
<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
</div>
	
</body>
</html>
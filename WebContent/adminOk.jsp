<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="css/navi.css" />
</head>
<body>
<%
	String id= (String)session.getAttribute("id");
%>
	<div class="wrap">
		<div class='menu'>
			<ul>
				<li>
					<a href='adminOk.jsp'>Home</a>
				</li>
				<li>
					<a href='#'>About Us</a>
				</li>
				<li class='active sub'>
					<a href='#'>수강관련</a>
					<ul>
						<li>
							<a href='./LectureregList.le'>수강과목등록</a>
						</li>
						<li>
							<a href='./memberList.le'>성적입력</a>
						</li>
					</ul>
				</li>
				<li class='active sub'>
					<a href='#'>게시판</a>
					<ul>
						<li>
							<a href='./NoticeList.bo'>공지사항</a>
						</li>
						<li>
							<a href='./BoardList.bo'>자유게시판</a>
						</li>
						<li>
							<a href='./QAList.bo'>Q&A게시판</a>
						</li>
					</ul>
				</li>
				<li>
					<a href='#'>Demos</a>
				</li>
				<li class='last'>
					<a href='logout.jsp'>로그아웃</a>
				</li>
			</ul>
		</div>
		<div class="logo">
		</div>
		<div class="content">
		<p class="login"><%=id%> 님 관리자 페이지입니다. </p>
		</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>
</body>
</html>
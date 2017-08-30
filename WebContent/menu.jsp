<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<%@page import="net.member.db.MemberBean"%> 
<%@page import="net.member.db.MemberDAO"%>
<%@page import="message.db.MessageDao"%>
<title>Insert title here</title>
<% 

String id=null; 
if(session.getAttribute("id")!=null){ 
    id=(String)session.getAttribute("id"); 
}
MemberDAO dao = new MemberDAO();
MemberBean mb=dao.getSelect(id);

MessageDao messageDao = new MessageDao();
int newMessageCheck =messageDao.messageNewCheck(id);
%>
<script type="text/javascript">


function message(){
	var popUrl = "MessageListView.ms";

	var popOption = "top=200 ,left=500,width=610, height=500, resizable=yes, scrollbars=yes, status=no;";    //팝업창 옵션(optoin)

		window.open(popUrl,"",popOption);
}

</script>

</head>
<body>
	<% if(mb.getMEMBER_GRADE()!=2){ %>
	<div class='menu'>
					<ul>
				<li>
					<a href='loginOK.jsp'>Home</a>
				</li>
				<li>
					<a href='MyPage.jsp'>About Us</a>
				</li>
				<li class='active sub'>
					<a href='#'>my 정보</a>
					<ul>
						<li>
							<a href='modimain.jsp'>정보수정</a>
						</li>
						<li>
							<a href='./LectureList.le'>수강등록</a>
						</li>
						<li>
							<a href='./graderesult.le'>성적조회</a>
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
				<li class='active sub'>
					<a href='#'>일정</a>
					<ul>
						<li>
							<a href='./AcademicCanlendarView.le'>학사일정</a>
						</li>
						<li>
							<a href='./canlendar.le'>my일정관리</a>
						</li>
					</ul>
				</li>
				<li class='last'>
					<a href='logout.jsp'>로그아웃</a>
				</li>
				<% if(newMessageCheck>=1){ %>
				<li style="float:right">
					<a href="javascript:message()">새로운쪽지</a>
				</li>
				<%}else{ %>
				<li style="float:right">
					<a href="javascript:message()">쪽지</a>
				</li>
				<%} %>
			</ul>
		</div>
		<% }else{ %>
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
				<li class='active sub'>
					<a href='#'>일정</a>
					<ul>
						<li>
							<a href='./AcademicCanlendarView.le'>학사일정관리</a>
						</li>
					</ul>
				</li>
				<li class='last'>
					<a href='logout.jsp'>로그아웃</a>
				</li>
			</ul>
		</div>
		<%} %>
		 
</body>
</html>
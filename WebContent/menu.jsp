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

	var popOption = "top=200 ,left=500,width=610, height=500, resizable=yes, scrollbars=yes, status=no;";    //�˾�â �ɼ�(optoin)

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
					<a href='#'>my ����</a>
					<ul>
						<li>
							<a href='modimain.jsp'>��������</a>
						</li>
						<li>
							<a href='./LectureList.le'>�������</a>
						</li>
						<li>
							<a href='./graderesult.le'>������ȸ</a>
						</li>
					</ul>
				</li>
				<li class='active sub'>
					<a href='#'>�Խ���</a>
					<ul>
						<li>
							<a href='./NoticeList.bo'>��������</a>
						</li>
						<li>
							<a href='./BoardList.bo'>�����Խ���</a>
						</li>
						<li>
							<a href='./QAList.bo'>Q&A�Խ���</a>
						</li>
					</ul>
				</li>
				<li class='active sub'>
					<a href='#'>����</a>
					<ul>
						<li>
							<a href='./AcademicCanlendarView.le'>�л�����</a>
						</li>
						<li>
							<a href='./canlendar.le'>my��������</a>
						</li>
					</ul>
				</li>
				<li class='last'>
					<a href='logout.jsp'>�α׾ƿ�</a>
				</li>
				<% if(newMessageCheck>=1){ %>
				<li style="float:right">
					<a href="javascript:message()">���ο�����</a>
				</li>
				<%}else{ %>
				<li style="float:right">
					<a href="javascript:message()">����</a>
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
					<a href='#'>��������</a>
					<ul>
						<li>
							<a href='./LectureregList.le'>����������</a>
						</li>
						<li>
							<a href='./memberList.le'>�����Է�</a>
						</li>
					</ul>
				</li>
				<li class='active sub'>
					<a href='#'>�Խ���</a>
					<ul>
						<li>
							<a href='./NoticeList.bo'>��������</a>
						</li>
						<li>
							<a href='./BoardList.bo'>�����Խ���</a>
						</li>
						<li>
							<a href='./QAList.bo'>Q&A�Խ���</a>
						</li>
					</ul>
				</li>
				<li class='active sub'>
					<a href='#'>����</a>
					<ul>
						<li>
							<a href='./AcademicCanlendarView.le'>�л���������</a>
						</li>
					</ul>
				</li>
				<li class='last'>
					<a href='logout.jsp'>�α׾ƿ�</a>
				</li>
			</ul>
		</div>
		<%} %>
		 
</body>
</html>
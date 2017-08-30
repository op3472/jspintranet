<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript" charset="utf-8" src="js/calupdate.js"></script>
<title>Insert title here</title>
</head>
<body>
<form method="post" name="theForm" action="./CalUpdate.le"  onsubmit="return checkValue()">
<TABLE cellpadding="0" cellspacing="0" border="0" bgcolor="#ffffff" width="100%" height="100%" >
<TR>
	<TD align="center">
	<TABLE cellpadding="0" cellspacing="1" border="0" bgcolor="#ffffff" width="440">
		<TR height="10">
			<TD></TD>
		</TR>
		<TR>
			<TD align=center bgcolor="#ffffff">
			<TABLE cellpadding="5" cellspacing="1" border="0" bgcolor="#666666">
				<TR bgcolor="#ffffff">
					<TD class="main">
						일자
					</TD>
					<TD>
						${list.scheduleDate}
						<input type="hidden" name="schedule_id" value="${list.scheduleId}">
					</TD>
				</TR>
				<TR bgcolor="#ffffff">
					<TD class=main width="60">
						제목
					</TD>
					<TD width="380">
						<INPUT type="text" name="schedule_subject" class="text_gray" size="30" maxlength="50" value="${list.scheduleSubject}">
					</TD>
				</TR>
				<TR bgcolor="#ffffff">
					<TD class="main">
						내용
					</TD>
					<TD>
						<TEXTAREA NAME="schedule_content" class="textarea" ROWS="12" COLS="52" WRAP="VIRTUAL">${list.scheduleContent}</TEXTAREA>
					</TD>
				</TR>
			</TABLE>
			</TD>   
		</TR>     
		<TR>
			<TD colspan="2" height="5"></TD>
		</TR>
		<TR>
			<TD align="center">
				<input type="image" style="cursor:hand" border=0 src="images/edit.gif"/>
				<IMG src="images/close.gif" style="cursor:hand" border=0 onclick="window.close()">
			</TD>
		</TR>
		<TR>
			<TD colspan=2 height=5></TD>
		</TR>
	</TABLE>
	</TD>
</TR>
</TABLE>
</form>
</body>
</html>
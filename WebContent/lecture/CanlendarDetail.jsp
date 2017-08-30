<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
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
					</TD>
				</TR>
				<TR bgcolor="#ffffff">
					<TD class=main width="60">
						제목
					</TD>
					<TD width="380">
						${list.scheduleSubject}
					</TD>
				</TR>
				<TR bgcolor="#ffffff">
					<TD class="main">
						내용
					</TD>
					<TD>
						${list.scheduleContent}
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
				<a href="CalUpdateForm.le?num=${list.scheduleId}"><img src="images/edit.gif"></a>
				<a href="CalDelete.le?num=${list.scheduleId}"><img src="images/delete.gif"></a>
				<IMG src="images/close.gif" 	style="cursor:hand" border=0 onclick="window.close()">
			</TD>
		</TR>
		<TR>
			<TD colspan=2 height=5></TD>
		</TR>
	</TABLE>
	</TD>
</TR>
</TABLE>

</body>
</html>
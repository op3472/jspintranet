<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" type="text/css" href="./css/navi.css" />
<title>Insert title here</title>

<STYLE>
	BODY {
		FONT-SIZE: 9pt; FONT-FAMILY: tahoma
	}
	TABLE {
		FONT-SIZE: 9pt; COLOR: black; FONT-FAMILY: tahoma
	}
	A {
		COLOR: #999999; TEXT-DECORATION: none
	}
	A:hover {
		COLOR: red; TEXT-DECORATION: none
	}
	A:visited{
	COLOR: #999999; TEXT-DECORATION: none
	}
	TD.main {
		FONT-WEIGHT: bold; TEXT-ALIGN: right
	}
	TD.uline {
		FONT-SIZE: 7pt; COLOR: #999999; BACKGROUND-COLOR: #ffffff
	}
	TD.r_uline {
		FONT-SIZE: 7pt; COLOR: #999999; BACKGROUND-COLOR: #f4f4f4
	}
	INPUT {
		MARGIN: -5px
	}
</STYLE>
<script type="text/javascript">
function popupOpen(){

	var popUrl = "lecture/caninsert.jsp";

	var popOption = "top=200 ,left=500,width=500, height=400, resizable=yes, scrollbars=yes, status=no;";    //�˾�â �ɼ�(optoin)

		window.open(popUrl,"",popOption);
}

</script>
</head>
<body>

<div class="wrap">

		  <jsp:include page="/menu.jsp" flush="false"/>
		<div class="logo">
		</div>
		
		<div class="content">
		
		<div style="margin:100px 200px 0">
    	<input style="margin:0 55px 0" type="button" class="signUpButton" onclick="popupOpen()" value="�������">
<FORM name="theForm">
<%-- base table --%>
<TABLE cellpadding="0" cellspacing="0" border="0" bgcolor="#ffffff" width="620" height="665">
	<TR>
  		<TD align="right" width="365">
  			<A href="calender.le?type=MONTH&curYear=<c:out value="${curYear}"/>&curMonth=<c:out value="${curMonth-1}"/>&curDay=<c:out value="${curDay}"/>">��</a>
  				${curYear} Ҵ &nbsp;&nbsp;  ${curMonth}  �� 
  			<A href="calender.le?type=MONTH&curYear=<c:out value="${curYear}"/>&curMonth=<c:out value="${curMonth+1}"/>&curDay=<c:out value="${curDay}"/>">��</a>
  		</TD>
  		<TD align="left">
  			<IMG src="./images/monthly.gif" border=0>
  		</TD>
	</TR>
	<TR height="3">
		<TD colspan="2"></TD>
	</TR>
	<TR>
  		<TD align="center" colspan="3" valign="top">
  		<%-- body table --%>
  		<TABLE border="0" cellspacing="0" cellpadding="0">
  			<TR>
  				<TD valign="top" style="border:#666666 1px solid;padding:5px" align="center">
  				<%-- month outline table --%>
   			 	<TABLE border="0" cellspacing="0" cellpadding="0">
    				<TR height="30">
      					<TD align=center>
      						<FONT color=red>�Ͽ���</FONT>
      					</TD>
						<TD align=center>������</TD>
						<TD align=center>ȭ����</TD>
						<TD align=center>������</TD>
						<TD align=center>�����</TD>
						<TD align=center>�ݿ���</TD>
						<TD align=center>�����</TD>
    				</TR>
    				<TR><TD colspan=7 bgcolor=#888888 height=1></TD></TR>
    				<TR><TD colspan=7 bgcolor=#ffffff height=5></TD></TR>
					<TR>
      					<TD colspan=7>
      					<%-- month content table --%>
      					<TABLE border='0' cellspacing='1' cellpadding='0' bgcolor=#dddddd>
      						<TR>
								<c:if test="${firstDayOfWeek != '1'}">
      							<%-- �ش� ���� ���� ù°�ٿ� �ִ� ����κ��� ���ؼ� ó���Ѵ�.--%>
      							<c:forEach var="i" begin="1" end="${firstDayOfWeek-1}">
									<TD width="70" height="78" class="uline" valign="top" align="right" style="padding:5">
									</TD>
								</c:forEach>
								</c:if>
								
								<%-- �� ���� �������� �޸��� ����� ��¥(����)�� ����Ѵ� --%>
								<c:set var="dbIndex" value="0"/>
      							<c:forEach var="currentDay" begin="1" end="${lastDayOfMonth}">									
									<TD bgcolor="#ffffff" style="padding:5">
										<TABLE cellpadding="0" cellsping="0" border="0" width="70">
										<TR>
											<TD height="10" width="70" class="uline" valign="top" align="right">
										
												<c:choose>
												
													<c:when test="${((currentDay-(8-firstDayOfWeek)) % 7) == 1}">
														<!-- �Ͽ��� -->
														<FONT color="red">													
															<c:out value="${currentDay}"/>  
														</FONT>
													</c:when>
													<c:otherwise>
														<c:out value="${currentDay}"/>
													</c:otherwise>
												</c:choose>
											</TD>
										</TR>
										<TR>
											<TD height="68" width="70" valign="top">
											<TABLE>
												<c:forEach var="row" items="${list}">
													<c:if test="${currentDay == row.day}">
														<TR><TD>
														<A href="javascript:view('${row.scheduleId}')">
															${row.scheduleSubject}
															
														</A>
														</TD></TR>
													</c:if>
												</c:forEach>
											</TABLE>
											</TD>
										</TR>
										</TABLE>		
									</TD>
									<%-- ���� ������ ��������(�����)�̰� �� ���� ������ ���� �ƴ϶�� ���� �ٷ� �Ѿ��. --%>
									<c:if test="${((currentDay-(8-firstDayOfWeek)) % 7) == 0}">
										</TR>
										<TR>
									</c:if>
								</c:forEach>

								<%-- �ش� ���� ���� ������ �ٿ� �ִ� ����κ��� ���ؼ� ó���Ѵ�.--%>
								<c:if test="${lastDayOfLastWeek != '7'}">
								<c:forEach var="i" begin="1" end="${7-lastDayOfLastWeek}">
									<TD width=70 height=78 class=uline valign=top align=right style='padding:5'>
									</TD>
								</c:forEach>
								</c:if>							
							</TR>
						</TABLE>
						<%-- end month content table --%>
						</TD>
					</TR>
      			</TABLE>
      			<%-- end month outline table --%>
      			</TD>
    		</TR>
    	</TABLE>
    	<%-- end body table --%>
		</TD>
	</TR>
  	<TR height=10><TD></TD></TR>
	<TR>
		<TD align=right></TD>
	</TR>
</table>
<%-- end base table --%>
</FORM>
</div>
  		</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>
</body>
</html>
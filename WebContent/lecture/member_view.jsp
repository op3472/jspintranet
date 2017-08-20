<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="./css/navi.css" />
<link rel="stylesheet" type="text/css" href="./css/application.css" />
<link rel="stylesheet" type="text/css" href="./css/pagination.css" />
<link rel="stylesheet" type="text/css" href="./css/detail.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="sun.java2d.loops.Blit"%> 
<%@page import="lecture.db.LectureBean"%> 
<%@page import="lecture.db.memberBean"%> 
<%@page import="java.util.List"%> 
<%@page import="net.member.db.MemberBean"%> 
<%@page import="net.member.db.MemberDAO"%> 
<% 

String id=null; 
if(session.getAttribute("id")!=null){ 
    id=(String)session.getAttribute("id"); 
} 
List memberList =(List)request.getAttribute("memberlist"); 
MemberDAO dao = new MemberDAO();
MemberBean mb=dao.getSelect(id);
	
%> 
 <script type="text/javascript">
        var httpRequest = null;
        
        // httpRequest 객체 생성
        function getXMLHttpRequest(){
            var httpRequest = null;
        
            if(window.ActiveXObject){
                try{
                    httpRequest = new ActiveXObject("Msxml2.XMLHTTP");    
                } catch(e) {
                    try{
                        httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e2) { httpRequest = null; }
                }
            }
            else if(window.XMLHttpRequest){
                httpRequest = new window.XMLHttpRequest();
            }
            return httpRequest;    
        }
        function checkFunc(){
            if(httpRequest.readyState == 4){
                // 결과값을 가져온다.
                var resultText = httpRequest.responseText;
                if(resultText == 1){ 
                	alert("성공적으로 입력되었습니다.");
                }
            }
        }
        // 댓글 등록
        function registration(num)
        {
        	  var x=document.getElementById(num);
			  var grade=x.options[x.selectedIndex].value;



            var param="num="+num+"&grade="+grade;
                    
                httpRequest = getXMLHttpRequest();
                httpRequest.onreadystatechange = checkFunc;
                httpRequest.open("POST", "writegrade.le", true);    
                httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'); 
                httpRequest.send(param);
            
        }
      
    </script>
</head>
<body>

<div class="wrap">

		  <jsp:include page="/menu.jsp" flush="false"/>
		<div class="logo">
		</div>
		
		<div class="content">
  <div class="appll3">
    	<table class="board" width=570 border="0" cellpadding="0" cellspacing="0"> 
      
	<tr>
	<td colspan="4" align="center" valign="middle">
	<p class="bold">${ids}의 수강신청목록</p>
	</td>
	</tr>
    <tr class="tr1" align="center" valign="middle" > 
        <td> 수강과목 </td> 
        <td> 담당교수</td> 
        <td>  학점</td> 
        <td> </td>
    </tr>     
	  <c:forEach var="member" items="${requestScope.memberlist}">
    <tr class="tr2" align="center" valign="middle"   >  
        <td>  
              ${member.lecture_subject}            
        </td>         
        <td> 
            ${member.lecture_name}
        </td> 
        <td> 
           ${member.lecture_credit} 
        </td> 
        <td>
        	<select id="${member.lecture_num}">
 			<option value="4.5" selected="selected"">A+</option>
  			<option value="4.0">A</option>
  			<option value="3.5">B+</option>
  			<option value="3.0">B</option>
  			<option value="2.5">C+</option>
  			<option value="2.0">C</option>
  			<option value="1.5">D+</option>
  			<option value="1.0">D</option>
  			<option value="0">F</option>
			</select>
        	  <a class="bold" href="#" onclick="registration(${member.lecture_num})">성적입력</a>
        </td>
    </tr> 
   
    </c:forEach>
</table>
</div>
  		</div>
		<div class="footer">
		copyright-2017- email:rain_pero@naver.com
		</div>
	</div>
</body>
</html>
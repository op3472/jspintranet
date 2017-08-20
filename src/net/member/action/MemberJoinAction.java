package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import net.commons.action.Action; 
import net.commons.action.ActionForward; 
import net.member.db.MemberBean; 
import net.member.db.MemberDAO; 

public class MemberJoinAction implements Action{ 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        request.setCharacterEncoding("euc-kr"); //�ѱ�ó�� 
        ActionForward forward = new ActionForward(); 
        MemberDAO memberdao =new MemberDAO();
        MemberBean member = new MemberBean(); 
        boolean result=false; 
         
        /*�Է� ������ memberbean��ü�� ����*/ 
        member.setMEMBER_ID(request.getParameter("MEMBER_ID")); 
        member.setMEMBER_PW(request.getParameter("MEMBER_PW")); 
        member.setMEMBER_NAME(request.getParameter("MEMBER_NAME")); 
        member.setMEMBER_AGE(Integer.parseInt(request.getParameter("MEMBER_AGE"))); 
        member.setMEMBER_GENDER(request.getParameter("MEMBER_GENDER")); 
        member.setMEMBER_EMAIL(request.getParameter("MEMBER_EMAIL")); 
        member.setMEMBER_GRADE(Integer.parseInt(request.getParameter("MEMBER_GRADE")));
        result=memberdao.joinMember(member); // dao�� joinmember�޼��带 �����ؼ� ȸ������ó��         

        //ȸ������ ���н� null��ȯ 
        if(result==false){ 
        	   PrintWriter out = response.getWriter(); 
               out.println("<script>"); 
               out.println("alert('ȸ�����Կ� �����ϼ̽��ϴ�.');"); 
               out.println("location.href='./MemberJoin.me';"); 
               out.println("</script>"); 
               out.close(); 
        }     
        //ȸ������ ���� 
        forward.setRedirect(true); 
        forward.setPath("./joinOk.jsp");         
        return forward; 
    } 
}

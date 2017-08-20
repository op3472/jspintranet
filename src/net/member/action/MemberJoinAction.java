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
        request.setCharacterEncoding("euc-kr"); //한글처리 
        ActionForward forward = new ActionForward(); 
        MemberDAO memberdao =new MemberDAO();
        MemberBean member = new MemberBean(); 
        boolean result=false; 
         
        /*입력 정보를 memberbean객체에 저장*/ 
        member.setMEMBER_ID(request.getParameter("MEMBER_ID")); 
        member.setMEMBER_PW(request.getParameter("MEMBER_PW")); 
        member.setMEMBER_NAME(request.getParameter("MEMBER_NAME")); 
        member.setMEMBER_AGE(Integer.parseInt(request.getParameter("MEMBER_AGE"))); 
        member.setMEMBER_GENDER(request.getParameter("MEMBER_GENDER")); 
        member.setMEMBER_EMAIL(request.getParameter("MEMBER_EMAIL")); 
        member.setMEMBER_GRADE(Integer.parseInt(request.getParameter("MEMBER_GRADE")));
        result=memberdao.joinMember(member); // dao에 joinmember메서드를 실행해서 회원가입처리         

        //회원가입 실패시 null반환 
        if(result==false){ 
        	   PrintWriter out = response.getWriter(); 
               out.println("<script>"); 
               out.println("alert('회원가입에 실패하셨습니다.');"); 
               out.println("location.href='./MemberJoin.me';"); 
               out.println("</script>"); 
               out.close(); 
        }     
        //회원가입 성공 
        forward.setRedirect(true); 
        forward.setPath("./joinOk.jsp");         
        return forward; 
    } 
}

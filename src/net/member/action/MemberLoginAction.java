package net.member.action;


import java.io.PrintWriter; 

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; 

import net.commons.action.Action; 
import net.commons.action.ActionForward; 
import net.member.db.MemberBean; 
import net.member.db.MemberDAO; 

public class MemberLoginAction implements Action{ 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        ActionForward forward = new ActionForward(); 
        HttpSession session = request.getSession(); //ȸ�� ���� ������ ���̵� ���ǿ� ����� ���� ��ü ���� 
        MemberDAO memberdao =new MemberDAO();
        MemberBean member = new MemberBean(); 
         
        int result=-1; // �⺻ ��� ���� -1(���̵� �������� �ʴ´ٴ� �ǹ̷� ����) 
        /*�α��������� �Է��� ���� memberbean��ü�� ����*/ 
        member.setMEMBER_ID(request.getParameter("MEMBER_ID")); 
        member.setMEMBER_PW(request.getParameter("MEMBER_PW")); 
        result=memberdao.isMember(member); //dao�� ismember�޼ҵ�ȣ���Ͽ� ȸ������ 
         
        //�α��� ������ ��� 
        if(result==0){ //��й�ȣ Ʋ���ٴ� ����� ������ ��â ���� �ٽ� �α���â 
            response.setContentType("text/html;charset=euc-kr"); 
            PrintWriter out = response.getWriter(); 
            out.println("<script>"); 
            out.println("alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.');"); 
            out.println("location.href='./MemberLogin.me';"); 
            out.println("</script>"); 
            out.close(); 
            return null; 
        }else if(result==-1){ //���̵� �������� ������ ����â �� �ٽ� �α���â 
            response.setContentType("text/html;charset=euc-kr"); 
            PrintWriter out = response.getWriter(); 
            out.println("<script>"); 
            out.println("alert('���̵� �������� �ʽ��ϴ�.');"); 
            out.println("location.href='./MemberLogin.me';"); 
            out.println("</script>"); 
            out.close(); 
            return null; 
        }else if(result==1){         
        //�α��� ������ ��� 
        	session.setAttribute("id", member.getMEMBER_ID()); //���ǿ� id��� 
        	forward.setRedirect(true);//���Ӳ����ٰ� �ٽÿ����ϸ鼭 ���ο� ������ �����ش�. 
        	forward.setPath("./loginOK.jsp"); //�Խ��� ��� ���� ��û. 
      
        }else if(result==2){
        	session.setAttribute("id", member.getMEMBER_ID()); //���ǿ� id��� 
            forward.setRedirect(true);//���Ӳ����ٰ� �ٽÿ����ϸ鼭 ���ο� ������ �����ش�. 
            forward.setPath("./adminOk.jsp"); //�Խ��� ��� ���� ��û. 
        }
        return forward;
		
    } 
} 

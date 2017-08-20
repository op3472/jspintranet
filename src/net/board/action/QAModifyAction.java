package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class QAModifyAction implements Action{ 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        ActionForward forward = new ActionForward(); 
        request.setCharacterEncoding("euc-kr"); 
        boolean result=false; 
        int num=Integer.parseInt(request.getParameter("BOARD_NUM")); 
        String id = request.getParameter("BOARD_ID"); 
        BoardDAO boarddao = new BoardDAO(); 
        BoardBean boarddata = new BoardBean(); 

        boolean usercheck=boarddao.isqaWriter(num, id);         
        
            if(usercheck==false){ 
                response.setContentType("text/html;charset=euc-kr"); 
                PrintWriter out = response.getWriter(); 
                out.println("<script>"); 
                out.println("alert('������ ������ �����ϴ�.');"); 
                out.println("location.href='./QAList.bo';"); 
                out.println("</script>"); 
                out.close(); 
                return null; 
            } 
        
        try{         
            boarddata.setBOARD_NUM(num); 
            boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT")); 
            boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT")); 
            result= boarddao.qaModify(boarddata); 
            if(result==false){ 
                System.out.println("�Խ��� ���� ����"); 
                return null; 
            } 
            System.out.println("�Խ��� ���� �Ϸ�"); 
            forward.setRedirect(true); 
            forward.setPath("./QADetailAction.bo?num="+boarddata.getBOARD_NUM()); 
            return forward; 
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
        return null; 
    } 

}

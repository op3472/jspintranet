package net.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class QAListAction2 implements Action{ 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        ActionForward forward = new ActionForward(); 
        HttpSession session=request.getSession(); 
        String id=(String)session.getAttribute("id"); 
        if(id==null){ 
            forward.setRedirect(true); 
            forward.setPath("./MemberLogin.me"); 
            return forward; 
        } 
        BoardDAO boarddao = new BoardDAO(); 
        List boardlist = new ArrayList(); 
        String board_subject=null; 
        int page=1; 
        int limit=10; 
        int result = 0;
        if(request.getParameter("board_subject")!=null){ 
            board_subject = request.getParameter("board_subject"); 
            request.setAttribute("board_subject",board_subject);
        } 
        if(request.getParameter("page")!=null){ 
            page=Integer.parseInt(request.getParameter("page")); 
        } 
             
        int listcount=boarddao.getQAListCount2(board_subject); //�� ����Ʈ ���� �޾ƿ�. 
        boardlist = boarddao.getQAList2(board_subject,page,limit); //����Ʈ�� �޾ƿ�. 
             
        //�� ������ ��. 
           int maxpage=(int)((double)listcount/limit+0.95); //0.95�� ���ؼ� �ø� ó��. 
           //���� �������� ������ ���� ������ ��(1, 11, 21 ��...) 
           int startpage = (((int) ((double)page / limit + 0.9)) - 1) * limit + 1; 
           //���� �������� ������ ������ ������ ��.(10, 20, 30 ��...) 
           int endpage = maxpage; 
            
           if (endpage<startpage+limit-1) endpage=maxpage; 
            
           request.setAttribute("page", page);          //���� ������ ��. 
           request.setAttribute("maxpage", maxpage); //�ִ� ������ ��. 
           request.setAttribute("startpage", startpage); //���� �������� ǥ���� ù ������ ��. 
           request.setAttribute("endpage", endpage);     //���� �������� ǥ���� �� ������ ��. 
           request.setAttribute("listcount",listcount); //�� ��. 
           request.setAttribute("boardlist", boardlist); 
             
           forward.setRedirect(false); 
           forward.setPath("./qa/qna_board_list2.jsp"); 
           return forward; 
    } 

}

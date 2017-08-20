package net.board.action;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardBean; 
import net.board.db.BoardDAO; 
import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import net.commons.action.Action; 
import net.commons.action.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO; 

public class NoticeDetailAction  implements Action{
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        request.setCharacterEncoding("euc-kr"); 
        BoardDAO boarddao=new BoardDAO(); 
        BoardBean boarddata = new BoardBean(); 
        int num = Integer.parseInt(request.getParameter("num")); 
        boarddao.setReadCountnotice(num); 
        boarddata = boarddao.getnoticeDetail(num); 
        HttpSession session=request.getSession(); 
        CommentDAO commentDAO = CommentDAO.getInstance();
        ArrayList<CommentBean> commentList = commentDAO.getnoticeCommentList(num);
        String id=(String)session.getAttribute("id"); //세션에 저장되어 있는 아이디를 id변수에 저장한다. 

        MemberDAO dao = new MemberDAO();
        MemberBean mb=dao.getSelect(id);
        // 댓글이 1개라도 있다면 request에 commentList를 세팅한다.
        if(commentList.size() > 0)    request.setAttribute("commentList", commentList);

        if(boarddata==null){ 
            System.out.println("상세보기 실패"); 
            return null; 
        } 
        System.out.println("상세보기 성공"); 
         
        request.setAttribute("boarddata", boarddata); 

        request.setAttribute("grade",mb.getMEMBER_GRADE());
        ActionForward forward = new ActionForward(); 
        forward.setRedirect(false); 
        forward.setPath("./notice/qna_board_view.jsp"); 
        return forward; 
    } 
}

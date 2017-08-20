package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class NoticeModifyView  implements Action {
	   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
	        ActionForward forward = new ActionForward(); 
	        request.setCharacterEncoding("euc-kr"); 
	         
	        BoardDAO boarddao = new BoardDAO(); 
	        BoardBean boarddata = new BoardBean(); 
	         
	        int num=Integer.parseInt(request.getParameter("num")); 
	        boarddata=boarddao.getnoticeDetail(num); 
	         
	        if(boarddata ==null){ 
	            System.out.println("(수정_상세보기 실패"); 
	            return null; 
	        } 
	        System.out.println("수정_상세보기 성공"); 
	        request.setAttribute("boarddata", boarddata); 
	        forward.setRedirect(false); 
	        forward.setPath("./notice/qna_board_modify.jsp"); 
	        return forward; 
	    } 

}

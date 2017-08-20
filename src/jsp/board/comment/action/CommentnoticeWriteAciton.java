package jsp.board.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class CommentnoticeWriteAciton  implements Action {
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        CommentDAO dao = CommentDAO.getInstance();
        CommentBean comment = new CommentBean();
        
        // 파리미터 값을 가져온다.
        int comment_board = Integer.parseInt(request.getParameter("comment_board"));
        String comment_id = request.getParameter("comment_id");
        String comment_content = request.getParameter("comment_content");

     
        comment.setComment_board(comment_board);
        comment.setComment_id(comment_id);
        comment.setComment_content(comment_content);
        
        boolean result = dao.insertnoticeComment(comment);
 
        if(result){
            response.setContentType("text/html;charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("1");
            out.close();
        }
            
        return null;
    }
}

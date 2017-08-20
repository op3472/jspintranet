package jsp.board.comment.action;
 
import java.io.PrintWriter;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;
 
public class CommentReplyAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        // 파라미터를 가져온다.
    	int comment_num = Integer.parseInt(request.getParameter("comment_num"));
        int comment_board = Integer.parseInt(request.getParameter("comment_board"));
        String comment_id = request.getParameter("comment_id");
        String comment_content = request.getParameter("comment_content");
        int comment_parent=	Integer.parseInt(request.getParameter("comment_parent"));
        int comment_lev=Integer.parseInt(request.getParameter("comment_lev"));
        int comment_seq=Integer.parseInt(request.getParameter("comment_seq"));
        
        
        CommentDAO dao = CommentDAO.getInstance();
        
        CommentBean comment = new CommentBean();
        comment.setComment_num(comment_num);
        comment.setComment_board(comment_board);
        comment.setComment_id(comment_id);
        comment.setComment_content(comment_content);
        comment.setComment_parent(comment_parent);  // 부모댓글의 글번호를 세팅
        comment.setComment_lev(comment_lev);
        comment.setComment_seq(comment_seq);
        boolean result = dao.insertreComment(comment);
        
        response.setContentType("text/html;charset=euc-kr");
        PrintWriter out = response.getWriter();
        
        // 정상적으로 댓글을 저장했을경우 1을 전달한다.
        if(result) out.println("1");
        else out.println("0");
        
        out.close();
        
        return null;
    }
}
 


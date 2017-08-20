package jsp.board.comment.action;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;
 
public class CommentUpdateFormAction implements Action
{
 
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // 수정할 댓글의 글번호를 가져온다.
        int comment_num = Integer.parseInt(request.getParameter("num"));
 
        CommentDAO dao = CommentDAO.getInstance();
        CommentBean comment = dao.getComment(comment_num);
        
        // 댓글 정보를 request에 세팅한다.
        request.setAttribute("comment", comment);
        
        forward.setRedirect(false);
        forward.setPath("./CommentUpdateForm.jsp");
        
        return forward;
    }
}
 


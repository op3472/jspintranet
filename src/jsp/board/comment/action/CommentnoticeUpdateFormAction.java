package jsp.board.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class CommentnoticeUpdateFormAction implements Action
{
 
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // ������ ����� �۹�ȣ�� �����´�.
        int comment_num = Integer.parseInt(request.getParameter("num"));
 
        CommentDAO dao = CommentDAO.getInstance();
        CommentBean comment = dao.getnoticeComment(comment_num);
        
        // ��� ������ request�� �����Ѵ�.
        request.setAttribute("comment", comment);
        
        forward.setRedirect(false);
        forward.setPath("./notice/CommentUpdateForm.jsp");
        
        return forward;
    }
}
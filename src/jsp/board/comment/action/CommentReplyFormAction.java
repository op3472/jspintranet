package jsp.board.comment.action;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;
 
public class CommentReplyFormAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
     
        // �θ���� �۹�ȣ�� �����´�.
        int comment_num = Integer.parseInt(request.getParameter("num"));
 
        CommentDAO dao = CommentDAO.getInstance();
        CommentBean comment = dao.getComment(comment_num);
        
        // ��� ������ request�� �����Ѵ�.
        request.setAttribute("comment", comment);
       
        RequestDispatcher dispatcher=request.getRequestDispatcher("CommentReplyForm.jsp"); 
        dispatcher.forward(request,response);  
        return null;
    }
}
 


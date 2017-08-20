package lecture.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lecture.db.LectureDAO;
import lecture.db.CommentBean;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class CommentUpdateFormAction implements Action
{
 
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	 LectureDAO dao=new LectureDAO(); 
         CommentBean comment = new CommentBean();
        ActionForward forward = new ActionForward();
        
        // ������ ����� �۹�ȣ�� �����´�.
        int comment_num = Integer.parseInt(request.getParameter("num"));
        
         comment = dao.getComment(comment_num);
        
        // ��� ������ request�� �����Ѵ�.
        request.setAttribute("comment", comment);
        
        forward.setRedirect(false);
        forward.setPath("./lecture/CommentUpdateForm.jsp");
        
        return forward;
    }

}

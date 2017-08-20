package lecture.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lecture.db.LectureDAO;
import lecture.db.CommentBean;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class CommentUpdateAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	
    
        // �Ķ���͸� �����´�.
        int comment_num = Integer.parseInt(request.getParameter("comment_num"));
        String comment_content = request.getParameter("comment_content");
        System.out.println(comment_content);
        LectureDAO dao=new LectureDAO();
        
        CommentBean comment = new CommentBean();
        comment.setLecture_num(comment_num);
        comment.setLecture_content(comment_content);
        
        boolean result = dao.updateComment(comment);
        
        response.setContentType("text/html;charset=euc-kr");
        PrintWriter out = response.getWriter();
        
        // ���������� ����� ����������� 1�� �����Ѵ�.
        if(result) out.println("1");
        
        out.close();
        
        return null;
    }

}
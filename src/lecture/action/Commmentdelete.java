package lecture.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lecture.db.CommentBean;
import lecture.db.LectureDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class Commmentdelete implements Action {
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
    	 LectureDAO dao=new LectureDAO(); 
        CommentBean comment = new CommentBean();
        response.setContentType("text/html;charset=euc-kr");  
        // 파리미터 값을 가져온다.
        int num = Integer.parseInt(request.getParameter("num"));
        int board = Integer.parseInt(request.getParameter("board"));
        
        double grade = dao.commentboard(num);
        boolean result=dao.deleteComment(num);
        
        result=dao.deleteupdatecomment(grade,board);
        if(result){
            response.setContentType("text/html;charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("1");
            out.close();
        }
            
        return null;
    }

}

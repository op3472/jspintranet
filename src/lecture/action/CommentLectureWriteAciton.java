package lecture.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lecture.db.LectureDAO;
import lecture.db.CommentBean;
import net.commons.action.Action;
import net.commons.action.ActionForward;


public class CommentLectureWriteAciton implements Action {
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    
    	 LectureDAO dao=new LectureDAO(); 
        CommentBean comment = new CommentBean();
        response.setContentType("text/html;charset=euc-kr"); 
        System.out.println("커멘트상세보기 성공"); 
        // 파리미터 값을 가져온다.
        int comment_board = Integer.parseInt(request.getParameter("comment_board"));
        String comment_id = request.getParameter("comment_id");
        String comment_content = request.getParameter("comment_content");
        double grade = Double.parseDouble(request.getParameter("grade"));
       
        comment.setLecture_board(comment_board);
        comment.setLecture_id(comment_id);
        comment.setLecture_content(comment_content);
        comment.setLecture_grade(grade);
        boolean result = dao.insertComment(comment);
        dao.updateComment(comment_board,grade);
        if(result){
            response.setContentType("text/html;charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("1");
            out.close();
        }
            
        return null;
    }
}

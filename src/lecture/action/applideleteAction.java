package lecture.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lecture.db.LectureDAO;
import lecture.db.memberBean;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class applideleteAction implements Action {
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward(); 
    	LectureDAO dao=new LectureDAO(); 

        
        // 파리미터 값을 가져온다.
        String id = request.getParameter("id");
        int num= Integer.parseInt(request.getParameter("num"));
   
        dao.deleteclass(num,id);
 
        forward.setRedirect(false); 
        forward.setPath("./LectureList.le"); 
        return forward; 
    }

}

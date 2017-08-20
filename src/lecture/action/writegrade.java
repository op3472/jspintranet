package lecture.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lecture.db.LectureDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class writegrade implements Action {
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	  LectureDAO dao = new LectureDAO(); 
    		
        int num = Integer.parseInt(request.getParameter("num"));
        double grade = Double.parseDouble(request.getParameter("grade"));
        boolean result = dao.updatelecture(grade, num);
        

        if(result){
            response.setContentType("text/html;charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("1");
            out.close();
        }
            
        return null;
    }

}

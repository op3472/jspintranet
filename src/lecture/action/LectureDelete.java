package lecture.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lecture.db.LectureBean;
import lecture.db.LectureDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class LectureDelete implements Action{ 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        
        ActionForward forward = new ActionForward(); 
        LectureDAO lecturedao=new LectureDAO(); 
       
        int num = Integer.parseInt(request.getParameter("num")); 
        lecturedao.lectureDelete(num); 
       
        forward.setPath("./LectureregList.le"); 
        return forward; 
    } 
}

package lecture.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lecture.db.LectureDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class AcademinCanlendarUpdateForm implements Action
{
	 public ActionForward execute(HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
		 ActionForward forward = new ActionForward(); 
		int num = Integer.parseInt(request.getParameter("num"));
		LectureDAO dao = new LectureDAO();
		request.setAttribute("list",dao.getAcademicCalendarDetail(num));
		 
		 
		 
		  forward.setRedirect(false); 
         forward.setPath("./lecture/AcademinCanlendarUpdate.jsp"); 
         return forward; 		 
	 }

}


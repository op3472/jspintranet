package lecture.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lecture.db.LectureDAO;
import lecture.db.canlanderBean;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class CalUpdate  implements Action {
	 public ActionForward execute(HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
		 request.setCharacterEncoding("euc-kr"); //한글처리 
		 ActionForward forward = new ActionForward(); 
		 LectureDAO dao = new LectureDAO();
		 canlanderBean bean = new canlanderBean();
		 bean.setScheduleId(Integer.parseInt(request.getParameter("schedule_id")));
		 bean.setScheduleSubject(request.getParameter("schedule_subject"));
		 bean.setScheduleContent(request.getParameter("schedule_content"));
		 
		 dao.calUpdate(bean);
		 forward.setRedirect(false);
         forward.setPath("./lecture/CanUpdateSuccess.jsp"); 
         return forward; 	
	 }

}

package lecture.action;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lecture.db.LectureDAO;
import lecture.db.canlanderBean;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class AcademicAddAction implements Action {
	 public ActionForward execute(HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
		 request.setCharacterEncoding("euc-kr"); //한글처리 
		 	ActionForward forward = new ActionForward(); 
		  	HttpSession session=request.getSession(); 
		  	LectureDAO Dao = new LectureDAO();
		    String id=(String)session.getAttribute("id");
		    Calendar calendar = Calendar.getInstance();
		    calendar.set(Integer.parseInt(request.getParameter("date_Y")),Integer.parseInt(request.getParameter("date_M"))-1, Integer.parseInt(request.getParameter("date_D")));
		    Date scheduleTime = new Date(calendar.getTimeInMillis());
		    canlanderBean Bean = new canlanderBean();
		    Bean.setMemberId(id);
		    Bean.setScheduleSubject(request.getParameter("schedule_subject"));
		    Bean.setScheduleContent(request.getParameter("schedule_content"));
		    Bean.setScheduleDate(scheduleTime);
		    
		    int check=Dao.checkAcademicCanlendar(Bean);
		    
		    if(check==1){
		    	  forward.setRedirect(false); 
		           forward.setPath("./lecture/CalInsertFail.jsp"); 
		           return forward; 
		    }
		    else{
		    Dao.insertAcademicCanlendar(Bean);
		    	forward.setRedirect(false); 
	           forward.setPath("./lecture/CalInsertSuccess.jsp"); 
	           return forward; 
		    }
		   
		 
	 }

}
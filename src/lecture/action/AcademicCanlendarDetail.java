package lecture.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lecture.db.LectureDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class AcademicCanlendarDetail implements Action
{
	 public ActionForward execute(HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
		 ActionForward forward = new ActionForward(); 
		 HttpSession session=request.getSession();
		  String id=(String)session.getAttribute("id"); 
		int num = Integer.parseInt(request.getParameter("num"));
		LectureDAO dao = new LectureDAO();
		MemberDAO memberDao = new MemberDAO();
        MemberBean memberBean = memberDao.getSelect(id);
        request.setAttribute("grade",memberBean.getMEMBER_GRADE());
		request.setAttribute("list",dao.getAcademicCalendarDetail(num));
		 
		 
		 
		  forward.setRedirect(false); 
         forward.setPath("./lecture/AcademicCanlendarDetail.jsp"); 
         return forward; 		 
	 }

}
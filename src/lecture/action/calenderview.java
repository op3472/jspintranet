package lecture.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lecture.db.LectureDAO;

import net.commons.action.Action;
import net.commons.action.ActionForward;

public class calenderview implements Action{ 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
    	request.setCharacterEncoding("euc-kr"); //한글처리 
    	  ActionForward forward = new ActionForward(); 
    	  HttpSession session=request.getSession(); 
    	  Calendar calendar = Calendar.getInstance();
    	  String id=(String)session.getAttribute("id"); 
          LectureDAO lecturedao = new LectureDAO();
    	  String strType = (String)request.getParameter("type");

    	  if(strType != null && !strType.equals("")) {
    	  	int intYear 	= Integer.parseInt(request.getParameter("curYear"));
    	  	int intMonth 	= Integer.parseInt(request.getParameter("curMonth"));
    	  	int intDay 		= Integer.parseInt(request.getParameter("curDay"));

    	  	if(intMonth > 12) {
    	  		intYear += 1;
    	  		intMonth = 1;
    	  	}
    	  	if(intMonth < 1) {
    	  		intYear -= 1;
    	  		intMonth = 12;
    	  	}

    	  	calendar.set(intYear, intMonth-1, intDay);
    	  }

    	  //today 정보
    	  request.setAttribute("today", 		calendar.getTime());
    	  request.setAttribute("curYear", 	calendar.get(Calendar.YEAR));
    	  request.setAttribute("curMonth", 	(calendar.get(Calendar.MONTH) + 1));
    	  request.setAttribute("curDay", 		calendar.get(Calendar.DATE));


    	  //해당월의 1일로 캘린더 설정.
    	  calendar.set(Calendar.DATE, 1); 
    	  request.setAttribute("firstDayOfMonth", calendar.getTime());
    	  Date firstDayOfMonth = calendar.getTime();
    	  session.setAttribute("firstDayOfWeek", calendar.get(Calendar.DAY_OF_WEEK));
    	  session.setAttribute("lastDayOfMonth", 	calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

    	  //해당월의 마지막일로 캘린더 설정.
    	  calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    	  session.setAttribute("lastDayOfLastWeek", calendar.get(Calendar.DAY_OF_WEEK));

    	  //다음달의 1일로 설정.
    	  calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
    	  calendar.set(Calendar.DATE, 1);
    	  request.setAttribute("firstDayOfNextMonth", calendar.getTime());
    	  Date firstDayOfNextMonth = calendar.getTime();
    	  List canlist = lecturedao.getcanlender(id,firstDayOfMonth,firstDayOfNextMonth);
          request.setAttribute("list", canlist);     
            forward.setRedirect(false); 
            forward.setPath("./lecture/calenderview.jsp"); 
            return forward; 
    } 

}

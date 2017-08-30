package message.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.db.MessageDao;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class MessageDelete implements Action{
	 public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		 ActionForward forward = new ActionForward(); 
		 int num = Integer.parseInt(request.getParameter("num"));
		 MessageDao dao = new MessageDao();    
		 dao.messageDelete(num);
		 forward.setRedirect(false); 
		 forward.setPath("./MessageListView.ms"); 
		 return forward; 
	 }
}

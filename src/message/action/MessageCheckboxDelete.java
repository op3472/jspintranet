package message.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.db.MessageDao;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class MessageCheckboxDelete  implements Action{
	 public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		 ActionForward forward = new ActionForward();
		 MessageDao dao = new MessageDao();
		 String[] arrIdx =request.getParameter("idx").split(",");
		 for (int i=0; i<arrIdx.length; i++) {
			 dao.messageDelete(Integer.parseInt(arrIdx[i]));
		 }

		 
		 forward.setRedirect(false); 
		 forward.setPath("./MessageListView.ms"); 
		 return forward; 
	 }
}

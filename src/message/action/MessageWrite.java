package message.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import message.db.MessageBean;
import message.db.MessageDao;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class MessageWrite implements Action{
	 public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		    ActionForward forward = new ActionForward(); 
		    HttpSession session=request.getSession(); 
		    request.setCharacterEncoding("euc-kr"); //한글처리 
			String id=(String)session.getAttribute("id"); 
			MessageDao dao = new MessageDao();    
			MessageBean bean = new MessageBean();
			bean.setMessageSubject(request.getParameter("messageSubject"));
			bean.setMessageContent(request.getParameter("messageContent"));
			bean.setMessageSender(id);
			bean.setMessageAddressee(request.getParameter("messageAddressee"));
			dao.messageInsert(bean);
	 
		    
		    forward.setRedirect(false); 
		    forward.setPath("./message/MessageInsertSuccess.jsp"); 
		    return forward; 
	 }
	 

}

package message.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import message.db.MessageDao;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class MessageListView  implements Action{
	 public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
    ActionForward forward = new ActionForward(); 
	HttpSession session=request.getSession(); 
	String id=(String)session.getAttribute("id"); 
	MessageDao dao = new MessageDao();        
    int page=1; 
    int limit=10; 
    int rowsize=10;
         
    if(request.getParameter("page")!=null){ 
        page=Integer.parseInt(request.getParameter("page")); 
    } 
         
    int listcount=dao.getMessageListCount(id); //총 리스트 수를 받아옴. 
    List messageList =dao.getMessageList(id,page,rowsize); //리스트를 받아옴. 
    int maxpage =(int)Math.ceil(listcount/(double)rowsize);
    int startpage =((page-1)/limit*limit)+1;
    int endpage  = ((page-1)/limit*limit)+limit;
    if(endpage>maxpage) {endpage= maxpage;}
       request.setAttribute("listcount",listcount); //글 수.
       request.setAttribute("page",page);
       request.setAttribute("maxpage",maxpage);
       request.setAttribute("startpage", startpage);
       request.setAttribute("endpage",endpage);
      
       request.setAttribute("list",messageList); 
         
    forward.setRedirect(false); 
       forward.setPath("./message/MessageListView.jsp"); 
       return forward; 
	 }   
}

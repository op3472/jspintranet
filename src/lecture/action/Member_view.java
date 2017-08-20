package lecture.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lecture.db.LectureDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.member.db.MemberDAO;

public class Member_view  implements Action{ 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        ActionForward forward = new ActionForward(); 
        HttpSession session=request.getSession(); 
        String id=request.getParameter("id"); 
        
        LectureDAO lecturedao = new LectureDAO(); 

        List memberlist = new ArrayList(); 
        
        memberlist = lecturedao.getmemberList(id); //리스트를 받아옴. 
   
        request.setAttribute("memberlist", memberlist); 
        request.setAttribute("ids", id);
        forward.setRedirect(false); 
           forward.setPath("./lecture/member_view.jsp"); 
           return forward; 
    } 


}

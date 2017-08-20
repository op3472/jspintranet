package lecture.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lecture.db.LectureDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class LectureregList implements Action{ 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        ActionForward forward = new ActionForward(); 
        HttpSession session=request.getSession(); 
        String id=(String)session.getAttribute("id"); 
        if(id==null){ 
            forward.setRedirect(true); 
            forward.setPath("./MemberLogin.me"); 
            return forward; 
        } 
        LectureDAO lecturedao = new LectureDAO(); 
        List lecturelist = new ArrayList(); 
        List memberlist = new ArrayList(); 
        
         int page=1; 
        int limit=10; 
             
        if(request.getParameter("page")!=null){ 
            page=Integer.parseInt(request.getParameter("page")); 
        } 
             
        int listcount=lecturedao.getListCount(); //총 리스트 수를 받아옴. 
        lecturelist = lecturedao.getLectureList(page, limit); //리스트를 받아옴. 
        memberlist = lecturedao.getmemberList(id); //리스트를 받아옴. 
             
        //총 페이지 수. 
           int maxpage=(int)((double)listcount/limit+0.95); //0.95를 더해서 올림 처리. 
           //현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...) 
           int startpage = (((int) ((double)page / limit + 0.9)) - 1) * limit + 1; 
           //현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...) 
           int endpage = maxpage; 
            
           if (endpage<startpage+limit-1) endpage=maxpage; 
            
           request.setAttribute("page", page);          //현재 페이지 수. 
           request.setAttribute("maxpage", maxpage); //최대 페이지 수. 
           request.setAttribute("startpage", startpage); //현재 페이지에 표시할 첫 페이지 수. 
           request.setAttribute("endpage", endpage);     //현재 페이지에 표시할 끝 페이지 수. 
        request.setAttribute("listcount",listcount); //글 수. 
        request.setAttribute("lecturelist", lecturelist); 
        request.setAttribute("memberlist", memberlist);      
        forward.setRedirect(false); 
           forward.setPath("./lecture/registration_list.jsp"); 
           return forward; 
    } 

}

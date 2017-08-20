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
             
        int listcount=lecturedao.getListCount(); //�� ����Ʈ ���� �޾ƿ�. 
        lecturelist = lecturedao.getLectureList(page, limit); //����Ʈ�� �޾ƿ�. 
        memberlist = lecturedao.getmemberList(id); //����Ʈ�� �޾ƿ�. 
             
        //�� ������ ��. 
           int maxpage=(int)((double)listcount/limit+0.95); //0.95�� ���ؼ� �ø� ó��. 
           //���� �������� ������ ���� ������ ��(1, 11, 21 ��...) 
           int startpage = (((int) ((double)page / limit + 0.9)) - 1) * limit + 1; 
           //���� �������� ������ ������ ������ ��.(10, 20, 30 ��...) 
           int endpage = maxpage; 
            
           if (endpage<startpage+limit-1) endpage=maxpage; 
            
           request.setAttribute("page", page);          //���� ������ ��. 
           request.setAttribute("maxpage", maxpage); //�ִ� ������ ��. 
           request.setAttribute("startpage", startpage); //���� �������� ǥ���� ù ������ ��. 
           request.setAttribute("endpage", endpage);     //���� �������� ǥ���� �� ������ ��. 
        request.setAttribute("listcount",listcount); //�� ��. 
        request.setAttribute("lecturelist", lecturelist); 
        request.setAttribute("memberlist", memberlist);      
        forward.setRedirect(false); 
           forward.setPath("./lecture/registration_list.jsp"); 
           return forward; 
    } 

}

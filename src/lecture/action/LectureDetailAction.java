package lecture.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import lecture.db.LectureBean;

import lecture.db.LectureDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;


public class LectureDetailAction implements Action{ 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        request.setCharacterEncoding("euc-kr"); 
        LectureDAO lecturedao=new LectureDAO(); 
        LectureBean lecturedata = new LectureBean(); 
        int num = Integer.parseInt(request.getParameter("num")); 
        lecturedata = lecturedao.getDetail(num); 
        HttpSession session=request.getSession(); 
        int page=1; 
        int limit=10; 
             
        if(request.getParameter("page")!=null){ 
            page=Integer.parseInt(request.getParameter("page")); 
        } 
             
        int listcount=lecturedao.getcommentCount(num); //�� ����Ʈ ���� �޾ƿ�. 
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
        List commentlist = new ArrayList();
        commentlist = lecturedao.getcomment(num,page, limit);
        if(lecturedata==null){ 
            System.out.println("�󼼺��� ����"); 
            return null; 
        } 
        System.out.println("�󼼺��� ����"); 
         
        request.setAttribute("lecturedata",lecturedata); 
        request.setAttribute("commentList",commentlist); 
        request.setAttribute("num",num); 
        ActionForward forward = new ActionForward(); 
        forward.setRedirect(false); 
        forward.setPath("./lecture/lecture_view.jsp"); 
        return forward; 
    } 

}

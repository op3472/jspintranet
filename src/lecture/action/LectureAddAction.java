package lecture.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import lecture.db.LectureDAO;
import lecture.db.LectureBean;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class LectureAddAction implements Action{ 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
    	request.setCharacterEncoding("euc-kr"); //한글처리 
    	LectureDAO dao = new LectureDAO(); 
        LectureBean data = new LectureBean(); 
        ActionForward forward = new ActionForward(); 
        data.setLecture_subject(request.getParameter("LECTURE_SUBJECT"));
        data.setLecture_name(request.getParameter("LECTURE_NAME"));
        data.setLecture_credit(Integer.parseInt(request.getParameter("LECTURE_CREDIT")));
        data.setLecture_content(request.getParameter("LECTURE_CONTENT"));
        boolean result=dao.lectureInsert(data); 
             
            if(result==false){ 
                System.out.println("과목 등록 실패"); 
                return null; 
            } 
            System.out.println("과목 등록 완료");             
            forward.setRedirect(true); 
            forward.setPath("./LectureregList.le"); 
            return forward; 
    } 

}

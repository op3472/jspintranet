package lecture.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import lecture.db.LectureDAO;
import lecture.db.memberBean;

public class applicationAction implements Action {
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward(); 
    	LectureDAO dao=new LectureDAO(); 
    	 memberBean bean = new memberBean(); 
        
        // �ĸ����� ���� �����´�.
        String id = request.getParameter("id");
        int num= Integer.parseInt(request.getParameter("num"));
        boolean check=dao.classcheck(num,id);
        if(check==false){ // �۾��̰� �ƴ� ��� ��� �޽����� ��� �� �Խ��� ���� �������� �̵�. 
            response.setContentType("text/html;charset=euc-kr"); 
            PrintWriter out = response.getWriter(); 
            out.println("<script>"); 
            out.println("alert('������ ������ �ֽ��ϴ�.');"); 
            out.println("location.href='./LectureList.le'"); 
            out.println("</script>"); 
            out.close(); 
            return null; 
        } 
        bean= dao.selectlecture(id,num);
        
        boolean result = dao.insertapplication(bean);
 
        forward.setRedirect(false); 
        forward.setPath("./LectureList.le"); 
        return forward; 
    }
}




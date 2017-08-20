package net.board.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import net.board.db.BoardDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class NoticeDeleteAction implements Action {
 	
    Vector<Integer> v = new Vector<Integer>();
    int idx = 1; //삭제리스트에 맨 처음 추가된 것 바로 다음을 재귀호출하도록


    public void addDeleteList(int no) {
        v.addElement(new Integer(no));
    }

    public void getDeleteList(int no) {
    	 Connection con;
	        try{ 
	            Context init = new InitialContext(); 
	              DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/test"); 
	              con = ds.getConnection(); 
	        }catch(Exception ex){ 
	            System.out.println("DB 연결 실패 : " + ex); 
	            return; 
	        } 
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        try {
            stmt =con.prepareStatement("SELECT board_num FROM membernotice WHERE board_parent = ?");
            stmt.setInt(1, no);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int target = rs.getInt(1);
                v.addElement(new Integer(target));
            }
            if(idx < v.size()) {
                Integer value = (Integer) v.elementAt(idx++);
                getDeleteList(value.intValue());
            }
        } catch (SQLException e) {
        	   System.out.println("getDeleteList 에러 : " + e); 
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
ActionForward forward = new ActionForward(); 
request.setCharacterEncoding("euc-kr"); 
HttpSession session=request.getSession(); 
String id=(String)session.getAttribute("id"); //세션에 저장되어 있는 아이디를 id변수에 저장한다. 

MemberDAO dao = new MemberDAO();
MemberBean mb=dao.getSelect(id);

boolean result=false; 
int num=Integer.parseInt(request.getParameter("num")); //삭제할 글 번호를 num 변수에 저장한다. 
 
BoardDAO boarddao = new BoardDAO(); 
boolean usercheck=boarddao.isNoticeWriter(num, id); //dao 클래스의 isBoardWriter()메소드에 전달한다. 
if(mb.getMEMBER_GRADE()!=2){ 
    if(usercheck==false){ // 글쓴이가 아닐 경우 경고 메시지를 띄운 후 게시판 메인 페이지로 이동. 
        response.setContentType("text/html;charset=euc-kr"); 
        PrintWriter out = response.getWriter(); 
        out.println("<script>"); 
        out.println("alert('삭제할 권한이 없습니다.');"); 
        out.println("location.href='./NoticeList.bo'"); 
        out.println("</script>"); 
        out.close(); 
        return null; 
    } 
} 
addDeleteList(num);
getDeleteList(num);

result=boarddao.Noticedelete(v); 

if(result==false){ //글 삭제 실패할 경우 null 
    System.out.println("게시판 삭제 실패"); 
    return null; 
} 
//글 삭제 성공할때 게시판 메인 페이지로 이동 
System.out.println("게시판 삭제 성공"); 
forward.setRedirect(true); 
forward.setPath("./NoticeList.bo"); 
return forward; 
}
}

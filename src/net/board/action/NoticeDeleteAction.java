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
    int idx = 1; //��������Ʈ�� �� ó�� �߰��� �� �ٷ� ������ ���ȣ���ϵ���


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
	            System.out.println("DB ���� ���� : " + ex); 
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
        	   System.out.println("getDeleteList ���� : " + e); 
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
String id=(String)session.getAttribute("id"); //���ǿ� ����Ǿ� �ִ� ���̵� id������ �����Ѵ�. 

MemberDAO dao = new MemberDAO();
MemberBean mb=dao.getSelect(id);

boolean result=false; 
int num=Integer.parseInt(request.getParameter("num")); //������ �� ��ȣ�� num ������ �����Ѵ�. 
 
BoardDAO boarddao = new BoardDAO(); 
boolean usercheck=boarddao.isNoticeWriter(num, id); //dao Ŭ������ isBoardWriter()�޼ҵ忡 �����Ѵ�. 
if(mb.getMEMBER_GRADE()!=2){ 
    if(usercheck==false){ // �۾��̰� �ƴ� ��� ��� �޽����� ��� �� �Խ��� ���� �������� �̵�. 
        response.setContentType("text/html;charset=euc-kr"); 
        PrintWriter out = response.getWriter(); 
        out.println("<script>"); 
        out.println("alert('������ ������ �����ϴ�.');"); 
        out.println("location.href='./NoticeList.bo'"); 
        out.println("</script>"); 
        out.close(); 
        return null; 
    } 
} 
addDeleteList(num);
getDeleteList(num);

result=boarddao.Noticedelete(v); 

if(result==false){ //�� ���� ������ ��� null 
    System.out.println("�Խ��� ���� ����"); 
    return null; 
} 
//�� ���� �����Ҷ� �Խ��� ���� �������� �̵� 
System.out.println("�Խ��� ���� ����"); 
forward.setRedirect(true); 
forward.setPath("./NoticeList.bo"); 
return forward; 
}
}

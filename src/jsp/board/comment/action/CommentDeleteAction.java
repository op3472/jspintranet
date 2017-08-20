package jsp.board.comment.action;
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


import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import net.commons.action.Action; 
import net.commons.action.ActionForward; 
public class CommentDeleteAction implements Action{

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
		                stmt =con.prepareStatement("SELECT comment_num FROM board_comment WHERE parent = ?");
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
	        int num = Integer.parseInt(request.getParameter("comment_num"));
	        
	        CommentDAO dao = CommentDAO.getInstance();
	        
	        response.setContentType("text/html;charset=euc-kr");
	        PrintWriter out = response.getWriter();
	 
	        // 정상적으로 댓글을 삭제했을경우 1을 전달한다.
	        
	        addDeleteList(num);
	        getDeleteList(num);

	        boolean result=dao.delete(v); 
	     
	        if(result) out.println("1");
	        
	        out.close();
	        return null;
	    }

	}



package jsp.board.comment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
 
public class CommentDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static CommentDAO instance;

	private CommentDAO(){
	
	        try{ 
	            Context init = new InitialContext(); 
	              DataSource ds =  
	                  (DataSource) init.lookup("java:comp/env/jdbc/test"); 
	              con = ds.getConnection(); 
	        }catch(Exception ex){ 
	            System.out.println("DB 연결 실패 : " + ex); 
	            return; 
	        } 
	    
	}
	public static CommentDAO getInstance(){
	    if(instance==null)
	        instance=new CommentDAO();
	    return instance;
	}
	 public boolean insertComment(CommentBean comment)
	    {
		    int num =0; 
	        boolean result = false;
	        
	       
	        try {
	            // 자동 커밋을 false로 한다.
	        	
	            con.setAutoCommit(false);
	            
	            pstmt=con.prepareStatement("select max(comment_num) from board_comment"); 
	            rs = pstmt.executeQuery(); 
	             
	            if(rs.next()) 
	                num =rs.getInt(1)+1;  //글이 등록되어있으면 글 번호 +1 
	            else 
	                num=1;//글 등록이 되어있지 않으면 num=1로 
	            
	            String sql ="INSERT INTO BOARD_COMMENT (COMMENT_BOARD, COMMENT_ID, COMMENT_DATE, COMMENT_PARENT,COMMENT_LEV,COMMENT_SEQ , COMMENT_CONTENT,parent) VALUES(?,?,sysdate(),?,0,9999,?,0)";

	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setInt(1, comment.getComment_board());
	            pstmt.setString(2, comment.getComment_id());
	            pstmt.setInt(3,num);
	            pstmt.setString(4, comment.getComment_content());
	            
	            int flag = pstmt.executeUpdate();
	            if(flag > 0){
	                result = true;
	                con.commit(); // 완료시 커밋
	            }
	            
	        } catch (Exception e) {
	            try {
	                con.rollback(); // 오류시 롤백
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	            } 
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }finally{ 
	            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
	            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
	        } 
	        return result;    
	    } // end boardInsert(); 
	 public boolean insertreComment(CommentBean comment)
	    {
		 
		    int num =0; 
	        boolean result1 = false;
	        int result=0; 
	        String sql1=""; 
	        int re_ref=comment.getComment_parent(); //원본글 번호 
	        int re_lev=comment.getComment_lev(); // 답변글의 깊이 
	        int re_seq=comment.getComment_seq(); //답변글의 순서 
	         

	        try {
	            // 자동 커밋을 false로 한다.
	        	
	            con.setAutoCommit(false);
	            
	          
	            sql1="update BOARD_COMMENT set COMMENT_SEQ=COMMENT_SEQ+1 "; 
	            sql1+="where COMMENT_PARENT=? and COMMENT_SEQ<?"; 
	            pstmt = con.prepareStatement(sql1); 
	            pstmt.setInt(1,re_ref); 
	            pstmt.setInt(2,re_seq); 
	            result=pstmt.executeUpdate(); 

	            re_seq = re_seq - 1; 
	            re_lev = re_lev+1;



	            String sql ="INSERT INTO BOARD_COMMENT (COMMENT_BOARD, COMMENT_ID, COMMENT_DATE, COMMENT_PARENT,COMMENT_LEV,COMMENT_SEQ , COMMENT_CONTENT,parent) VALUES(?,?,sysdate(),?,?,?,?,?)";

	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setInt(1, comment.getComment_board());
	            pstmt.setString(2, comment.getComment_id());
	            pstmt.setInt(3,re_ref);
	            pstmt.setInt(4,re_lev);
	            pstmt.setInt(5,re_seq);
	            pstmt.setString(6, comment.getComment_content());
	            pstmt.setInt(7,comment.getComment_num()); 
	            int flag = pstmt.executeUpdate();
	            if(flag > 0){
	                result1 = true;
	                con.commit(); // 완료시 커밋
	            }
	            
	        } catch (Exception e) {
	            try {
	                con.rollback(); // 오류시 롤백
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	            } 
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }finally{ 
	            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
	            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
	        } 
	        return result1;    
	    } // end boardInsert(); 	
	 public ArrayList<CommentBean> getCommentList(int boardNum)
	    {
	        ArrayList<CommentBean> list = new ArrayList<CommentBean>();

	        try {
	            String sql="select * from BOARD_COMMENT where comment_board= ? order by COMMENT_PARENT asc,COMMENT_SEQ desc";
	            
	            
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setInt(1, boardNum);
	         
	            rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	                CommentBean comment = new CommentBean();
	                
	                comment.setComment_num(rs.getInt("COMMENT_NUM"));
	                comment.setComment_board(rs.getInt("COMMENT_BOARD"));
	                comment.setComment_id(rs.getString("COMMENT_ID"));
	                comment.setComment_date(rs.getDate("COMMENT_DATE"));
	                comment.setComment_parent(rs.getInt("COMMENT_PARENT"));
	                comment.setComment_lev(rs.getInt("COMMENT_LEV"));
	                comment.setComment_seq(rs.getInt("COMMENT_SEQ"));
	                comment.setComment_content(rs.getString("COMMENT_CONTENT"));
	                list.add(comment);
	            }
	                
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }finally{ 
	            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
	            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
	        } 
	        return list;
	    } // end getCommentList
	 public boolean updateComment(CommentBean comment) 
	    {
	        boolean result = false;
	        
	        try{
	   
	            con.setAutoCommit(false); // 자동 커밋을 false로 한다.
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("UPDATE BOARD_COMMENT SET");
	            sql.append(" COMMENT_CONTENT = ?");
	            sql.append(" WHERE COMMENT_NUM = ?");
	 
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setString(1, comment.getComment_content());
	            pstmt.setInt(2, comment.getComment_num());
	 
	            int flag = pstmt.executeUpdate();
	            if(flag > 0){
	                result = true;
	                con.commit(); // 완료시 커밋
	            }
	            
	        } catch (Exception e) {
	            try {
	                con.rollback(); // 오류시 롤백
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	            }
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }finally{ 
	            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
	            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
	        } 
	        return result;
	    } // end updateComment    
	        
	   public boolean delete( 
	            Vector<Integer> v) {
	        
	        PreparedStatement stmt = null;
	        String sql2 = "DELETE FROM board_comment WHERE comment_num = ?";
	        
	        try {
	            stmt = con.prepareStatement(sql2);
	            for(int i = 0; i < v.size(); i++) {
	                Integer value = (Integer) v.elementAt(i);
	                stmt.setInt(1, value.intValue());
	                stmt.executeUpdate();
	            }
	        } catch (SQLException e) {
	        		System.out.println("delete 에러 : " + e); 
	                return false; 
	        } finally {
	            v.removeAllElements();
	            if (stmt != null) {
	                try {
	                    stmt.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        return true; 
	    }

	 public CommentBean getComment(int comment_num)
	    {
	        CommentBean comment = null;
	        
	        try {
	            StringBuffer sql = new StringBuffer();
	            sql.append("SELECT * FROM BOARD_COMMENT WHERE COMMENT_NUM = ?");
	            
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setInt(1, comment_num);
	            
	            rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	                comment = new CommentBean();
	                comment.setComment_num(rs.getInt("COMMENT_NUM"));
	                comment.setComment_board(rs.getInt("COMMENT_BOARD"));
	                comment.setComment_id(rs.getString("COMMENT_ID"));
	                comment.setComment_date(rs.getDate("COMMENT_DATE"));
	                comment.setComment_parent(rs.getInt("COMMENT_PARENT"));
	                comment.setComment_lev(rs.getInt("COMMENT_LEV"));
	                comment.setComment_seq(rs.getInt("COMMENT_SEQ"));
	                comment.setComment_content(rs.getString("COMMENT_CONTENT"));
	            }
	            }catch (Exception e) {
		            e.printStackTrace();
		            throw new RuntimeException(e.getMessage());
		        }finally{ 
		            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
		            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		        } 
		        return comment; 
		        
	        
	    }
	 public ArrayList<CommentBean> getnoticeCommentList(int boardNum)
	    {
	        ArrayList<CommentBean> list = new ArrayList<CommentBean>();

	        try {
	            String sql="select * from NOTICE_COMMENT where comment_board= ? order by COMMENT_PARENT asc,COMMENT_SEQ desc";
	            
	            
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setInt(1, boardNum);
	         
	            rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	                CommentBean comment = new CommentBean();
	                
	                comment.setComment_num(rs.getInt("COMMENT_NUM"));
	                comment.setComment_board(rs.getInt("COMMENT_BOARD"));
	                comment.setComment_id(rs.getString("COMMENT_ID"));
	                comment.setComment_date(rs.getDate("COMMENT_DATE"));
	                comment.setComment_parent(rs.getInt("COMMENT_PARENT"));
	                comment.setComment_lev(rs.getInt("COMMENT_LEV"));
	                comment.setComment_seq(rs.getInt("COMMENT_SEQ"));
	                comment.setComment_content(rs.getString("COMMENT_CONTENT"));
	                list.add(comment);
	            }
	                
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }finally{ 
	            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
	            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
	        } 
	        return list;
	    }
	    
	 public boolean insertnoticeComment(CommentBean comment)
	    {
		    int num =0; 
	        boolean result = false;
	        
	       
	        try {
	            // 자동 커밋을 false로 한다.
	        	
	            con.setAutoCommit(false);
	            
	            pstmt=con.prepareStatement("select max(comment_num) from notice_comment"); 
	            rs = pstmt.executeQuery(); 
	             
	            if(rs.next()) 
	                num =rs.getInt(1)+1;  //글이 등록되어있으면 글 번호 +1 
	            else 
	                num=1;//글 등록이 되어있지 않으면 num=1로 
	            
	            String sql ="INSERT INTO NOTICE_COMMENT (COMMENT_BOARD, COMMENT_ID, COMMENT_DATE, COMMENT_PARENT,COMMENT_LEV,COMMENT_SEQ , COMMENT_CONTENT,parent) VALUES(?,?,sysdate(),?,0,9999,?,0)";

	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setInt(1, comment.getComment_board());
	            pstmt.setString(2, comment.getComment_id());
	            pstmt.setInt(3,num);
	            pstmt.setString(4, comment.getComment_content());
	            
	            int flag = pstmt.executeUpdate();
	            if(flag > 0){
	                result = true;
	                con.commit(); // 완료시 커밋
	            }
	            
	        } catch (Exception e) {
	            try {
	                con.rollback(); // 오류시 롤백
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	            } 
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }finally{ 
	            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
	            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
	        } 
	        return result;    
	    }
	 public CommentBean getnoticeComment(int comment_num)
	    {
	        CommentBean comment = null;
	        
	        try {
	            StringBuffer sql = new StringBuffer();
	            sql.append("SELECT * FROM NOTICE_COMMENT WHERE COMMENT_NUM = ?");
	            
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setInt(1, comment_num);
	            
	            rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	                comment = new CommentBean();
	                comment.setComment_num(rs.getInt("COMMENT_NUM"));
	                comment.setComment_board(rs.getInt("COMMENT_BOARD"));
	                comment.setComment_id(rs.getString("COMMENT_ID"));
	                comment.setComment_date(rs.getDate("COMMENT_DATE"));
	                comment.setComment_parent(rs.getInt("COMMENT_PARENT"));
	                comment.setComment_lev(rs.getInt("COMMENT_LEV"));
	                comment.setComment_seq(rs.getInt("COMMENT_SEQ"));
	                comment.setComment_content(rs.getString("COMMENT_CONTENT"));
	            }
	            }catch (Exception e) {
		            e.printStackTrace();
		            throw new RuntimeException(e.getMessage());
		        }finally{ 
		            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
		            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		        } 
		        return comment; 
		        
	        
	    }
	 public boolean insertrenoticeComment(CommentBean comment)
	    {
		 
		    int num =0; 
	        boolean result1 = false;
	        int result=0; 
	        String sql1=""; 
	        int re_ref=comment.getComment_parent(); //원본글 번호 
	        int re_lev=comment.getComment_lev(); // 답변글의 깊이 
	        int re_seq=comment.getComment_seq(); //답변글의 순서 
	         

	        try {
	            // 자동 커밋을 false로 한다.
	        	
	            con.setAutoCommit(false);
	            
	          
	            sql1="update NOTICE_COMMENT set COMMENT_SEQ=COMMENT_SEQ+1 "; 
	            sql1+="where COMMENT_PARENT=? and COMMENT_SEQ<?"; 
	            pstmt = con.prepareStatement(sql1); 
	            pstmt.setInt(1,re_ref); 
	            pstmt.setInt(2,re_seq); 
	            result=pstmt.executeUpdate(); 

	            re_seq = re_seq - 1; 
	            re_lev = re_lev+1;



	            String sql ="INSERT INTO NOTICE_COMMENT (COMMENT_BOARD, COMMENT_ID, COMMENT_DATE, COMMENT_PARENT,COMMENT_LEV,COMMENT_SEQ , COMMENT_CONTENT,parent) VALUES(?,?,sysdate(),?,?,?,?,?)";

	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setInt(1, comment.getComment_board());
	            pstmt.setString(2, comment.getComment_id());
	            pstmt.setInt(3,re_ref);
	            pstmt.setInt(4,re_lev);
	            pstmt.setInt(5,re_seq);
	            pstmt.setString(6, comment.getComment_content());
	            pstmt.setInt(7,comment.getComment_num()); 
	            int flag = pstmt.executeUpdate();
	            if(flag > 0){
	                result1 = true;
	                con.commit(); // 완료시 커밋
	            }
	            
	        } catch (Exception e) {
	            try {
	                con.rollback(); // 오류시 롤백
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	            } 
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }finally{ 
	            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
	            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
	        } 
	        return result1;    
	    }	 public boolean noticeupdateComment(CommentBean comment) 
	    {
	        boolean result = false;
	        
	        try{
	   
	            con.setAutoCommit(false); // 자동 커밋을 false로 한다.
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("UPDATE notice_COMMENT SET");
	            sql.append(" COMMENT_CONTENT = ?");
	            sql.append(" WHERE COMMENT_NUM = ?");
	 
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setString(1, comment.getComment_content());
	            pstmt.setInt(2, comment.getComment_num());
	 
	            int flag = pstmt.executeUpdate();
	            if(flag > 0){
	                result = true;
	                con.commit(); // 완료시 커밋
	            }
	            
	        } catch (Exception e) {
	            try {
	                con.rollback(); // 오류시 롤백
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	            }
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }finally{ 
	            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
	            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
	        } 
	        return result;
	    } // end updateComment    
	       
     
public boolean noticedelete( 
         Vector<Integer> v) {
     
     PreparedStatement stmt = null;
     String sql2 = "DELETE FROM notice_comment WHERE comment_num = ?";
     
     try {
         stmt = con.prepareStatement(sql2);
         for(int i = 0; i < v.size(); i++) {
             Integer value = (Integer) v.elementAt(i);
             stmt.setInt(1, value.intValue());
             stmt.executeUpdate();
         }
     } catch (SQLException e) {
     		System.out.println("delete 에러 : " + e); 
             return false; 
     } finally {
         v.removeAllElements();
         if (stmt != null) {
             try {
                 stmt.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
     }

     return true; 
 }
	
}

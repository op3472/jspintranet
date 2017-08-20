package lecture.db;

import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.naming.Context; 
import javax.naming.InitialContext; 
import javax.sql.DataSource;

import lecture.db.LectureBean;
import net.board.db.BoardBean;
import lecture.db.CommentBean;
public class LectureDAO {

	private static LectureDAO instance;
    Connection con; 
    PreparedStatement pstmt; 
    ResultSet rs; 
    public LectureDAO() { 
        try{ 
            Context init = new InitialContext(); 
              DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/test"); 
              con = ds.getConnection(); 
        }catch(Exception ex){ 
            System.out.println("DB 연결 실패 : " + ex); 
            return; 
        } 
    } 
    public static LectureDAO getInstance(){
	    if(instance==null)
	        instance=new LectureDAO();
	    return instance;
	}
    public int getListCount() { 
        int x= 0; 
         
        try{ 
            pstmt=con.prepareStatement("select count(*) from lecture"); 
            rs = pstmt.executeQuery(); 
             
            if(rs.next()){ 
                x=rs.getInt(1);  //count(*)한값을 x에 저장한다. 
            } 
        }catch(Exception ex){ 
            System.out.println("getListCount 에러: " + ex);             
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return x; 
    } 
    
    public int getListCount2(String lecture_name) { 
        int x= 0; 
         
        try{ 
            pstmt=con.prepareStatement("select count(*) from lecture where lecture_subject like ?"); 
            pstmt.setString(1, "%"+lecture_name+"%");

            rs = pstmt.executeQuery(); 
             
            if(rs.next()){ 
                x=rs.getInt(1);  //count(*)한값을 x에 저장한다. 
            } 
        }catch(Exception ex){ 
            System.out.println("getListCount 에러: " + ex);             
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return x; 
    } 
    
    
    public List getLectureList(int page,int limit){ 
        String board_list_sql="select * from lecture order by lecture_num asc limit ?,?";
         
         
        List list = new ArrayList(); 
         
        int startrow=(page-1)*limit+1; //읽기 시작할 row 번호. 
        int endrow=startrow+limit-1; //읽을 마지막 row 번호.         
        try{ 
            pstmt = con.prepareStatement(board_list_sql); 
            pstmt.setInt(1, startrow-1); 
            pstmt.setInt(2, endrow-startrow+1); 
            rs = pstmt.executeQuery(); 
             
            while(rs.next()){ 
                LectureBean bean = new LectureBean(); 
                bean.setLecture_num(rs.getInt("lecture_num"));
                bean.setLecture_name(rs.getString("lecture_name"));
                bean.setLecture_subject(rs.getString("lecture_subject"));
                bean.setLecture_credit(rs.getInt("lecture_credit"));
                bean.setLecture_content(rs.getString("lecture_content"));
                bean.setLecture_grade(rs.getDouble("lecture_grade"));
                bean.setLecture_check(rs.getInt("lecture_check"));
                list.add(bean); 
            } 
             
            return list; 
        }catch(Exception ex){ 
            System.out.println("getLectureList 에러 : " + ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    }
    
    public List getLectureList2(int page,int limit,String lecture_name){ 
        String board_list_sql="select * from lecture where lecture_subject like ? order by lecture_num asc limit ?,?";
         
         
        List list = new ArrayList(); 
         
        int startrow=(page-1)*limit+1; //읽기 시작할 row 번호. 
        int endrow=startrow+limit-1; //읽을 마지막 row 번호.         
        try{ 
            pstmt = con.prepareStatement(board_list_sql);
            pstmt.setString(1, "%"+lecture_name+"%");
            pstmt.setInt(2, startrow-1); 
            pstmt.setInt(3, endrow-startrow+1); 
            rs = pstmt.executeQuery(); 
             
            while(rs.next()){ 
                LectureBean bean = new LectureBean(); 
                bean.setLecture_num(rs.getInt("lecture_num"));
                bean.setLecture_name(rs.getString("lecture_name"));
                bean.setLecture_subject(rs.getString("lecture_subject"));
                bean.setLecture_credit(rs.getInt("lecture_credit"));
                bean.setLecture_content(rs.getString("lecture_content"));
                bean.setLecture_grade(rs.getDouble("lecture_grade"));
                bean.setLecture_check(rs.getInt("lecture_check"));
                list.add(bean); 
            } 
             
            return list; 
        }catch(Exception ex){ 
            System.out.println("getLectureList 에러 : " + ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    }
    
    public LectureBean getDetail(int num) throws Exception{ 

        try{ 
            pstmt = con.prepareStatement( 
                    "select * from lecture where lecture_num = ?"); 
            pstmt.setInt(1, num); 
             
            rs= pstmt.executeQuery(); 
            LectureBean bean = new LectureBean(); 
            if(rs.next()){ 
            	
                 bean.setLecture_num(rs.getInt("lecture_num"));
                 bean.setLecture_name(rs.getString("lecture_name"));
                 bean.setLecture_subject(rs.getString("lecture_subject"));
                 bean.setLecture_credit(rs.getInt("lecture_credit"));
                 bean.setLecture_content(rs.getString("lecture_content"));
                 bean.setLecture_grade(rs.getDouble("lecture_grade"));
            } 
            return bean; 
        }catch(Exception ex){ 
            System.out.println("getDetail 에러 : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
    public boolean lectureInsert(LectureBean board){ 
    
        int result=0; 
        String sql=""; 
        
        try{ 
            
            sql="insert into lecture (LECTURE_NAME,LECTURE_SUBJECT,"; 
            sql+="LECTURE_CREDIT, LECTURE_CONTENT,LECTURE_GRADE,"+ 
                "LECTURE_CHECK) values(?,?,?,?,0,0)"; 
             
            pstmt = con.prepareStatement(sql); 
            pstmt.setString(1,board.getLecture_name() ); 
            pstmt.setString(2, board.getLecture_subject()); 
            pstmt.setInt(3, board.getLecture_credit()); 
            pstmt.setString(4, board.getLecture_content()); 
    
             
            result=pstmt.executeUpdate(); 
            if(result==0)return false; 
             
            return true; 
        }catch(Exception ex){ 
            System.out.println("lectureInsert 에러 : "+ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return false; 
    } 
    public void lectureDelete(int num){ 
    	   
         
        try{ 
         	String sql="delete from lecture where lecture_num=?";
        	pstmt = con.prepareStatement(sql.toString());
        	pstmt.setInt(1,num);
        	pstmt.executeUpdate(); 
    
            
        }catch(Exception ex){
            System.out.println("boardDelete 에러 : "+ex); 
            
        }finally{ 
            try{ 
                if(pstmt!=null)pstmt.close(); 
            }catch(Exception ex) {} 
        } 
         
     
    } 
     
    //조회수 업데이트(글 내용을 확인하는 순간 호출된다) 
    public void setReadCountUpdate(int num) throws Exception{ 
        String sql="update memberboard set BOARD_READCOUNT = "+ 
            "BOARD_READCOUNT+1 where BOARD_NUM = "+num; 
         
        try{ 
            pstmt=con.prepareStatement(sql); 
            pstmt.executeUpdate(); 
        }catch(SQLException ex){ 
            System.out.println("setReadCountUpdate 에러 : "+ex); 
        } 
    } 
	 public ArrayList<CommentBean> getcomment(int boardNum,int page,int limit)
	    {
	        ArrayList<CommentBean> list = new ArrayList<CommentBean>();
	        int startrow=(page-1)*limit+1; //읽기 시작할 row 번호. 
	        int endrow=startrow+limit-1; //읽을 마지막 row 번호.     
	        try {
	            String sql="select * from lecture_COMMENT where lecture_board= ? order by LECTURE_SEQ desc limit ?,?";
	            
	            
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setInt(1, boardNum);
	            pstmt.setInt(2, startrow-1); 
	            pstmt.setInt(3, endrow-startrow+1); 
	         
	            rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	                CommentBean comment = new CommentBean();
	                
	                comment.setLecture_num(rs.getInt("lecture_num"));
	                comment.setLecture_board(rs.getInt("lecture_board"));
	                comment.setLecture_id(rs.getString("lecture_id"));
	                comment.setLecture_date(rs.getDate("LECTURE_DATE"));
	                comment.setLecture_seq(rs.getInt("lecture_seq"));
	                comment.setLecture_content(rs.getString("lecture_content"));
	                comment.setLecture_grade(rs.getDouble("lecture_grade"));
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
	 public CommentBean getComment(int comment_num)
	    {
	        CommentBean comment = null;
	        
	        try {
	            StringBuffer sql = new StringBuffer();
	            sql.append("SELECT * FROM LECTURE_COMMENT WHERE LECTURE_NUM = ?");
	            
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setInt(1, comment_num);
	            
	            rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	                comment = new CommentBean();
	                comment.setLecture_num(rs.getInt("LECTURE_NUM"));
	                comment.setLecture_board(rs.getInt("LECTURE_BOARD"));
	                comment.setLecture_id(rs.getString("LECTURE_ID"));
	                comment.setLecture_date(rs.getDate("LECTURE_DATE"));
	                comment.setLecture_seq(rs.getInt("LECTURE_SEQ"));
	                comment.setLecture_content(rs.getString("LECTURE_CONTENT"));
	                comment.setLecture_grade(rs.getDouble("LECTURE_GRADE"));
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
	 public boolean updateComment(CommentBean comment) 
	    {
	        boolean result = false;
	        
	        try{
	   
	            con.setAutoCommit(false); // 자동 커밋을 false로 한다.
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("UPDATE LECTURE_COMMENT SET");
	            sql.append(" LECTURE_CONTENT = ?");
	            sql.append(" WHERE LECTURE_NUM = ?");
	 
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setString(1, comment.getLecture_content());
	            pstmt.setInt(2, comment.getLecture_num());
	 
	            int flag = pstmt.executeUpdate();
	            if(flag > 0){
	                result = true;
	                con.commit(); // 완료시 커밋
	            }
	            
	        } catch (Exception e) {

	            System.out.println("updateComment	 에러 : " + e); 
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
	    public int getcommentCount(int boardNum) { 
	        int x= 0; 
	         
	        try{ 
	            pstmt=con.prepareStatement("select count(*) from lecture_comment where lecture_board = ?"); 
	            pstmt.setInt(1, boardNum);
	            rs = pstmt.executeQuery(); 
	             
	            if(rs.next()){ 
	                x=rs.getInt(1);  //count(*)한값을 x에 저장한다. 
	            } 
	        }catch(Exception ex){ 
	            System.out.println("getListCount 에러: " + ex);             
	        }finally{ 
	            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
	            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
	        } 
	        return x; 
	    } 
		 public boolean insertComment(CommentBean comment)
		    {
			    int num =0; 
		        boolean result = false;
		        
		       
		        try {
		            // 자동 커밋을 false로 한다.
		        	
		           
		            
		            String sql ="INSERT INTO lecture_comment (lecture_board, lecture_id, lecture_date, lecture_seq , lecture_content,lecture_grade) VALUES(?,?,sysdate(),9999,?,?)";

		            pstmt = con.prepareStatement(sql.toString());
		            pstmt.setInt(1, comment.getLecture_board());
		            pstmt.setString(2, comment.getLecture_id());
		            pstmt.setString(3, comment.getLecture_content());
		            pstmt.setDouble(4, comment.getLecture_grade());
		            
		           
		            int flag = pstmt.executeUpdate();
		            
		          
		            if(flag > 0){
		                result = true;
		            }
		            
		        } catch (Exception e) {
		        	 System.out.println("insertComment 에러: " + e);     
		        }finally{ 
		            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
		            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
		        } 
		        return result;    
		    } 
		 public void updateComment(int num,Double grade){
		        try {
		      
		            
		            String sql ="update lecture set lecture_grade = lecture_grade + ?,lecture_check = lecture_check+1 where lecture_num = ?";

		            pstmt = con.prepareStatement(sql.toString());
		            pstmt.setDouble(1, grade);
		            pstmt.setInt(2,num);
		         
		            pstmt.executeUpdate();
		            
		         
		        } catch (Exception e) {
		        	System.out.println("updateComment 에러: " + e);     
		        }finally{ 
		            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
		            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
		        } 

		        
		 }
		  public List getmemberList(String id){ 
		        String board_list_sql="select * from lecture_member where lecture_id = ? order by lecture_num asc ";
		         
		         
		        List list = new ArrayList(); 
		   
		        try{ 
		            pstmt = con.prepareStatement(board_list_sql); 
		            pstmt.setString(1,id);
		              
		            rs = pstmt.executeQuery(); 
		   
		            while(rs.next()){ 
		                memberBean bean = new memberBean(); 
		                bean.setLecture_num(rs.getInt("lecture_num"));
		                bean.setLecture_id(rs.getString("lecture_id"));
		                bean.setLecture_board(rs.getInt("lecture_board"));
		                bean.setLecture_name(rs.getString("lecture_name"));
		                bean.setLecture_subject(rs.getString("lecture_subject"));
		                bean.setLecture_credit(rs.getInt("lecture_credit"));
		                bean.setResult(rs.getDouble("result"));
		                list.add(bean); 
		            } 
		             
		            return list; 
		        }catch(Exception ex){ 
		            System.out.println("getLectureList 에러 : " + ex); 
		        }finally{ 
		            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
		            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		        } 
		        return null; 
		    }
		  public memberBean selectlecture(String id,int num) throws Exception{ 

		        try{ 
		            pstmt = con.prepareStatement( 
		                    "select * from lecture where lecture_num = ?"); 
		            pstmt.setInt(1, num); 
		             
		            rs= pstmt.executeQuery(); 
		            memberBean bean = new memberBean(); 
		            if(rs.next()){ 
		            
		                 bean.setLecture_num(rs.getInt("lecture_num"));
		                 bean.setLecture_id(id);
		                 bean.setLecture_board(num);
		                 bean.setLecture_name(rs.getString("lecture_name"));
		                 bean.setLecture_subject(rs.getString("lecture_subject"));
		                 bean.setLecture_credit(rs.getInt("lecture_credit"));
		                 
		            } 
		            return bean; 
		        }catch(Exception ex){ 
		            System.out.println("getDetail 에러 : " + ex); 
		        }finally{ 
		            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
		            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
		        } 
		        return null; 
		    } 
		  public boolean insertapplication(memberBean bean)
		    {
			    int num =0; 
		        boolean result = false;
		        
		       
		        try {
		            // 자동 커밋을 false로 한다.
		        	
		           
		            
		            String sql ="INSERT INTO lecture_member (lecture_id, lecture_board, lecture_name, lecture_subject , lecture_credit,result) VALUES(?,?,?,?,?,0)";

		            pstmt = con.prepareStatement(sql.toString());
		            pstmt.setString(1, bean.getLecture_id());
		            pstmt.setInt(2, bean.getLecture_board());
		            pstmt.setString(3, bean.getLecture_name());
		            pstmt.setString(4, bean.getLecture_subject());
		            pstmt.setInt(5, bean.getLecture_credit());
		            
		           
		            int flag = pstmt.executeUpdate();
		            
		          
		            if(flag > 0){
		                result = true;
		            }
		            
		        } catch (Exception e) {
		        	 System.out.println("insertComment 에러: " + e);     
		        }finally{ 
		            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
		            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
		        } 
		        return result;    
		    }
		   public boolean classcheck(int num,String id){ 
		        String board_sql="select * from lecture_member where lecture_board=? and lecture_id = ?"; 
		         
		        try{ 
		            pstmt=con.prepareStatement(board_sql); 
		            pstmt.setInt(1, num);
		            pstmt.setString(2, id);
		            rs=pstmt.executeQuery(); 
		      
		             
		            if(rs.next()){ 
		                return false; 
		            } 
		        }catch(SQLException ex){ 
		            System.out.println("classcheck 에러 : "+ex); 
		        } 
		        return true; 
		    } 
		   public void deleteclass(int num,String id){ 
		        String board_sql="delete  from lecture_member where lecture_num=? and lecture_id = ?"; 
		         
		        try{ 
		            pstmt=con.prepareStatement(board_sql); 
		            pstmt.setInt(1, num);
		            pstmt.setString(2, id);
		            pstmt.executeUpdate();
		            
		             
		            
		        }catch(SQLException ex){ 
		            System.out.println("deleteclass 에러 : "+ex); 
		        } 
		      
		    } 
		   public boolean deleteComment(int num) {
		        
		        PreparedStatement stmt = null;
		        String sql2 = "DELETE FROM LECTURE_COMMENT  WHERE lecture_num = ?";
		        
		        try {
		            stmt = con.prepareStatement(sql2);
		                stmt.setInt(1, num);
		                stmt.executeUpdate();
		            
		        } catch (SQLException e) {
		        		System.out.println("delete 에러 : " + e); 
		                return false; 
		        } finally {
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
		   public double commentboard(int num){
		        String board_sql="select * from lecture_comment where lecture_num = ?"; 
		        Double board = 0.0;
		        try{ 
		            pstmt=con.prepareStatement(board_sql); 
		            pstmt.setInt(1, num);
		            rs=pstmt.executeQuery(); 
		            if(rs.next()){
		            board=rs.getDouble("lecture_grade");
		            		
		            }
		        }catch(SQLException ex){ 
		            System.out.println("commentboard 에러 : "+ex); 
		        } 
		        return board; 
		    } 
		   
		   public boolean deleteupdatecomment(double grade,int num){
		        String board_sql="update lecture set lecture_grade = lecture_grade - ?,lecture_check=lecture_check-1 where  lecture_num = ?"; 
		         
		        try{ 
		            pstmt=con.prepareStatement(board_sql);
		            pstmt.setDouble(1, grade);
		            pstmt.setInt(2, num);
		            pstmt.executeUpdate();
		      
		        }catch(SQLException ex){ 
		            System.out.println(" deleteupdatecomment 에러 : "+ex); 
		        } 
		        return true; 
		    } 
		   public boolean updatelecture(double grade,int num){
		        String board_sql="update lecture_member set result = ? where  lecture_num = ?"; 
		         
		        try{ 
		            pstmt=con.prepareStatement(board_sql);
		            pstmt.setDouble(1, grade);
		            pstmt.setInt(2, num);
		            pstmt.executeUpdate();
		      
		        }catch(SQLException ex){ 
		            System.out.println(" deleteupdatecomment 에러 : "+ex); 
		        } 
		        return true; 
		    } 
		    public List getcanlender(String id,Date first,Date last){ 
		        String board_list_sql="select schedule_id, DAY(schedule_date) as schedule_date,schedule_subject, schedule_content from tb_schedule where schedule_date >= ? AND	schedule_date < ? AND member_id = ? ORDER BY schedule_date";
		         
		        java.sql.Date sfirst = new java.sql.Date(first.getTime());
		        java.sql.Date slast = new java.sql.Date(last.getTime()); 
		        List list = new ArrayList(); 
		                
		        try{ 
		            pstmt = con.prepareStatement(board_list_sql);
		            pstmt.setDate(1,  sfirst);
		            pstmt.setDate(2, slast);
		            pstmt.setString(3, id);
				       
				       
		            rs = pstmt.executeQuery(); 
		             
		            while(rs.next()){
		            	canlanderBean bean = new canlanderBean();
		                bean.setScheduleId(rs.getInt("schedule_id"));
		                bean.setScheduleSubject(rs.getString("schedule_subject"));
		                bean.setScheduleContent(rs.getString("schedule_content"));
		                bean.setDay(rs.getInt("schedule_date"));
		                list.add(bean); 
		            } 
		             
		            return list; 
		        }catch(Exception ex){ 
		            System.out.println("getcanlender 에러 : " + ex); 
		        }finally{ 
		            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
		            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		        } 
		        return null; 
		    }
		   
}

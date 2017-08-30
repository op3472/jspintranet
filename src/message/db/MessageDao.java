package message.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.board.db.BoardBean;

public class MessageDao {
	  Connection con; 
	    PreparedStatement pstmt; 
	    ResultSet rs; 
	 public MessageDao(){
		  try{ 
	            Context init = new InitialContext(); 
	              DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/test"); 
	              con = ds.getConnection(); 
	        }catch(Exception ex){ 
	            System.out.println("DB 연결 실패 : " + ex); 
	            return; 
	        } 
	 }
	  public int getMessageListCount(String id) { 
	        int x= 0; 
	         
	        try{ 
	            pstmt=con.prepareStatement("select count(*) from message where message_addressee = ?"); 
	            pstmt.setString(1, id); 
	            rs = pstmt.executeQuery(); 
	             
	            if(rs.next()){ 
	                x=rs.getInt(1);  //count(*)한값을 x에 저장한다. 
	            } 
	        }catch(Exception ex){ 
	            System.out.println("getMessageListCount 에러: " + ex);             
	        }finally{ 
	            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
	            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
	        } 
	        return x; 
	    } 
	  public List getMessageList(String id,int page,int limit){ 
	        String board_list_sql="select * from message where message_addressee = ? order by message_date desc limit ?,?";
	         
	         
	        List list = new ArrayList(); 
	         
	        int startrow=(page-1)*limit+1; //읽기 시작할 row 번호. 
	        int endrow=startrow+limit-1; //읽을 마지막 row 번호.         
	        try{ 
	            pstmt = con.prepareStatement(board_list_sql); 
	            pstmt.setString(1, id); 
	            pstmt.setInt(2, startrow-1); 
	            pstmt.setInt(3, endrow-startrow+1); 
	            rs = pstmt.executeQuery(); 
	             
	            while(rs.next()){ 
	                MessageBean bean= new MessageBean();
	                bean.setMessageId(rs.getInt("message_id"));
	                bean.setMessageDate(rs.getDate("message_date"));
	                bean.setMessageSubject(rs.getString("message_subject"));
	                bean.setMessageContent(rs.getString("message_content"));
	                bean.setMessageSender(rs.getString("message_sender"));
	                bean.setMessageAddressee(rs.getString("message_addressee"));
	                bean.setMessageReadcheck(rs.getInt("message_readcheck"));
	                list.add(bean); 
	            } 
	             
	            return list; 
	        }catch(Exception ex){ 
	            System.out.println("getMessageList 에러 : " + ex); 
	        }finally{ 
	            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
	            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
	        } 
	        return null; 
	    } 
	    public void messageInsert(MessageBean bean){ 
	        String sql=""; 
	         
	         
	        try{
	             
	            sql="insert into message (message_date,message_subject,message_content,"; 
	            sql+="message_sender,message_addressee,message_readcheck)";
	            sql+="values(sysdate(),?,?,?,?,1)"; 
	             
	            pstmt = con.prepareStatement(sql); 
	            pstmt.setString(1, bean.getMessageSubject()); 
	            pstmt.setString(2, bean.getMessageContent()); 
	            pstmt.setString(3, bean.getMessageSender()); 
	            pstmt.setString(4, bean.getMessageAddressee());
	            pstmt.executeUpdate(); 
	        }catch(Exception ex){ 
	            System.out.println("messageInsert에러 : "+ex); 
	        }finally{ 
	            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
	            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
	        } 
	    } 
	    public MessageBean messageDetail(int num){ 
	        String board_list_sql="select * from message where message_id = ? ";
	         
	         
	               
	        try{ 
	            pstmt = con.prepareStatement(board_list_sql); 
	            pstmt.setInt(1, num); 
	            
	            rs = pstmt.executeQuery(); 
	            MessageBean bean= new MessageBean();
	            if(rs.next()){ 
	               
	                bean.setMessageId(rs.getInt("message_id"));
	                bean.setMessageDate(rs.getDate("message_date"));
	                bean.setMessageSubject(rs.getString("message_subject"));
	                bean.setMessageContent(rs.getString("message_content"));
	                bean.setMessageSender(rs.getString("message_sender"));
	                bean.setMessageAddressee(rs.getString("message_addressee"));
	                bean.setMessageReadcheck(rs.getInt("message_readcheck"));
	                
	            } 
	             
	            return bean; 
	        }catch(Exception ex){ 
	            System.out.println("messageDetail 에러 : " + ex); 
	        }finally{ 
	            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
	            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
	        } 
	        return null; 
	    } 
	    public void messageReadCheck(int num){ 
	        String sql=""; 
	         
	         
	        try{
	             
	            sql="update message set message_readcheck = 2 where message_id = ?"; 
	           
	             
	            pstmt = con.prepareStatement(sql); 
	            pstmt.setInt(1,num); 
	 
	            pstmt.executeUpdate(); 
	        }catch(Exception ex){ 
	            System.out.println("messageReadCheck에러 : "+ex); 
	        }finally{ 
	            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
	            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
	        } 
	    } 
	    public void messageDelete(int num){ 
	        String sql=""; 
	         
	         
	        try{
	             
	            sql="delete from message where message_id = ?"; 
	           
	             
	            pstmt = con.prepareStatement(sql); 
	            pstmt.setInt(1,num); 
	 
	            pstmt.executeUpdate(); 
	        }catch(Exception ex){ 
	            System.out.println("messageDelete에러 : "+ex); 
	        }finally{ 
	            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
	            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
	        } 
	    } 
	    public int messageNewCheck(String id){ 
	        String board_list_sql="select * from message where message_addressee = ? and message_readcheck = 1";
	        int result=0;
	        try{ 
	            pstmt = con.prepareStatement(board_list_sql); 
	            pstmt.setString(1, id); 
	            
	            rs = pstmt.executeQuery();
	            if(rs.next()){ 
	               result=1;
	            }
	        }catch(Exception ex){ 
	            System.out.println(" messageNewCheck 에러 : " + ex); 
	        }finally{ 
	            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
	            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
	        }
			return result; 
	       
	    } 
}

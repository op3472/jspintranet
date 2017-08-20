package net.member.db;

import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.ArrayList; 
import java.util.List; 

import javax.naming.Context; 
import javax.naming.InitialContext; 
import javax.sql.DataSource; 

public class MemberDAO { 
    Connection con; 
    PreparedStatement pstmt; 
    ResultSet rs; 
     
    public MemberDAO() { 
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

    public int isMember(MemberBean member) { 
        String sql ="select member_pw,member_grade from BOARDMEMBER where member_id=?"; 
        int result=-1; 
         
        try{ 
            pstmt=con.prepareStatement(sql); 
            pstmt.setString(1, member.getMEMBER_ID()); 
            rs = pstmt.executeQuery(); 
             
            if(rs.next()){ 
                if(rs.getString("MEMBER_PW").equals( 
                                    member.getMEMBER_PW())&&rs.getInt("MEMBER_GRADE")==1){ 
                    result=1;//일치. 
                }else if(rs.getString("MEMBER_PW").equals( 
                                    member.getMEMBER_PW())&&rs.getInt("MEMBER_GRADE")==2){
                	result=2;
                }
                else{ 
                    result=0;//불일치. 
                } 
            }else{ 
                result=-1;//아이디 존재하지 않음. 
            } 
        }catch(Exception ex){ 
            System.out.println("isMember 에러: " + ex);             
        }finally{ 
        	   try{
                   if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                   if(rs!=null) try{rs.close();}catch(SQLException ex){} 
                   if ( con != null ){ con.close(); con=null; }
               }catch(Exception e){
                   throw new RuntimeException(e.getMessage());
               }
        } 
         
        return result; 
    } 
     
    //회원 가입 
    public boolean joinMember(MemberBean member) { 
        String sql="INSERT INTO BOARDMEMBER VALUES (?,?,?,?,?,?,?)"; 
        int result=0; 
         
        try{ 
            pstmt=con.prepareStatement(sql); 
            pstmt.setString(1, member.getMEMBER_ID()); 
            pstmt.setString(2, member.getMEMBER_PW()); 
            pstmt.setString(3, member.getMEMBER_NAME()); 
            pstmt.setInt(4, member.getMEMBER_AGE()); 
            pstmt.setString(5, member.getMEMBER_GENDER()); 
            pstmt.setString(6, member.getMEMBER_EMAIL()); 
            pstmt.setInt(7, member.getMEMBER_GRADE());
            result=pstmt.executeUpdate(); 
             
            if(result!=0){ 
                return true; 
            } 
        }catch(Exception ex){ 
            System.out.println("joinMember 에러: " + ex);             
        }finally{ 
        	   try{
                   if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                   if(rs!=null) try{rs.close();}catch(SQLException ex){} 
                   if ( con != null ){ con.close(); con=null; }
               }catch(Exception e){
                   throw new RuntimeException(e.getMessage());
               }
        } 
         
        return false; 
    }    
    
    public boolean  updateMember(MemberBean member) { 
        String sql="UPDATE BOARDMEMBER SET member_pw = ?,Member_name = ?,member_age = ?,member_gender=?,member_email= ? WHERE member_id=?"; 
        int result=0; 
         
        try{ 
            pstmt=con.prepareStatement(sql); 
           
            pstmt.setString(1, member.getMEMBER_PW()); 
            pstmt.setString(2, member.getMEMBER_NAME()); 
            pstmt.setInt(3, member.getMEMBER_AGE()); 
            pstmt.setString(4, member.getMEMBER_GENDER()); 
            pstmt.setString(5, member.getMEMBER_EMAIL()); 
            pstmt.setString(6, member.getMEMBER_ID()); 
            result=pstmt.executeUpdate(); 
             
            if(result!=0){ 
                return true; 
            } 
        }catch(Exception ex){ 
            System.out.println("joinMember 에러: " + ex);             
        }finally{ 
        	   try{
                   if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                   if(rs!=null) try{rs.close();}catch(SQLException ex){} 
                   if ( con != null ){ con.close(); con=null; }
               }catch(Exception e){
                   throw new RuntimeException(e.getMessage());
               }
        } 
         
        return false; 
    }
 
    public MemberBean getSelect(String id){
    	String sql="SELECT * FROM BOARDMEMBER WHERE member_id=?";
    	try{ 
            pstmt=con.prepareStatement(sql); 
            pstmt.setString(1, id); 
            rs=pstmt.executeQuery(); 
            rs.next(); 
             
            MemberBean mb=new MemberBean(); 
            mb.setMEMBER_ID(rs.getString("MEMBER_ID")); 
            mb.setMEMBER_PW(rs.getString("MEMBER_PW")); 
            mb.setMEMBER_NAME(rs.getString("MEMBER_NAME")); 
            mb.setMEMBER_AGE(rs.getInt("MEMBER_AGE")); 
            mb.setMEMBER_GENDER(rs.getString("MEMBER_GENDER")); 
            mb.setMEMBER_EMAIL(rs.getString("MEMBER_EMAIL")); 
            mb.setMEMBER_GRADE(rs.getInt("MEMBER_GRADE")); 
            return mb; 
        }catch(Exception ex){ 
            System.out.println("getDeatilMember 에러: " + ex);             
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
            if ( con!= null ) try{con.close();}catch(SQLException ex){}
        } 
         
        return null; 
    } 

    
    public boolean duplicateIdCheck(String id)
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean x= false;
        
        try {
            // 쿼리
        	String sql="SELECT member_id FROM BOARDMEMBER WHERE member_id=?";
                        
         
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            if(rs.next()) x= true; //해당 아이디 존재
            
            return x;
            
        } catch (Exception sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if(rs!=null) try{rs.close();}catch(SQLException ex){} 
                if ( con != null ){ con.close(); con=null; }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    } 
    public int getmemberCount() { 
        int x= 0; 
         
        try{ 
            pstmt=con.prepareStatement("select count(*) from boardmember"); 
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
    public List getmemberList(int page,int limit){ 
        String sql="SELECT * FROM BOARDMEMBER limit ?,?"; 
        List memberlist=new ArrayList(); 
        int startrow=(page-1)*limit+1; //읽기 시작할 row 번호. 
        int endrow=startrow+limit-1; //읽을 마지막 row 번호.         
         
        try{ 
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, startrow-1); 
            pstmt.setInt(2, endrow-startrow+1);
            rs=pstmt.executeQuery(); 
            
            while(rs.next()){ 
            	if(rs.getInt("MEMBER_GRADE")!=2){
                MemberBean mb=new MemberBean(); 
                mb.setMEMBER_ID(rs.getString("MEMBER_ID")); 
                mb.setMEMBER_PW(rs.getString("MEMBER_PW")); 
                mb.setMEMBER_NAME(rs.getString("MEMBER_NAME")); 
                mb.setMEMBER_AGE(rs.getInt("MEMBER_AGE")); 
                mb.setMEMBER_GENDER(rs.getString("MEMBER_GENDER")); 
                mb.setMEMBER_EMAIL(rs.getString("MEMBER_EMAIL")); 
                 
                memberlist.add(mb); 
            	}
            } 
             
            return memberlist; 
        }catch(Exception ex){ 
            System.out.println("getDeatilMember 에러: " + ex);             
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 


}


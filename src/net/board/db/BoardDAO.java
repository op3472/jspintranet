package net.board.db;

import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.ArrayList; 
import java.util.List;
import java.util.Vector;

import javax.naming.Context; 
import javax.naming.InitialContext; 
import javax.sql.DataSource;

public class BoardDAO { 
    Connection con; 
    PreparedStatement pstmt; 
    ResultSet rs; 
     
    public BoardDAO() { 
        try{ 
            Context init = new InitialContext(); 
              DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/test"); 
              con = ds.getConnection(); 
        }catch(Exception ex){ 
            System.out.println("DB 연결 실패 : " + ex); 
            return; 
        } 
    } 
     
    /*글의 개수 구하기(전체 글의 개수를 반환한다) 
     * 글의 개수를 표시 및 페이징처리를 할때 사용된다.*/ 
    public int getListCount() { 
        int x= 0; 
         
        try{ 
            pstmt=con.prepareStatement("select count(*) from memberboard"); 
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
    public int getListCount2(String board_list) { 
        int x= 0; 
         
        try{ 
            pstmt=con.prepareStatement("select count(*) from memberboard  where board_subject like ? "); 
            pstmt.setString(1, "%"+board_list+"%");
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
      
    /*글 목록 보기 
     * List객체로 반환한다. 인수1) 출력할 페이지, 인수2) 한 페이지당 표시할 글 수*/ 
    public List getBoardList(int page,int limit){ 
        String board_list_sql="select * from memberboard order by BOARD_RE_REF desc,BOARD_RE_SEQ desc limit ?,?";
         
         
        List list = new ArrayList(); 
         
        int startrow=(page-1)*limit+1; //읽기 시작할 row 번호. 
        int endrow=startrow+limit-1; //읽을 마지막 row 번호.         
        try{ 
            pstmt = con.prepareStatement(board_list_sql); 
            pstmt.setInt(1, startrow-1); 
            pstmt.setInt(2, endrow-startrow+1); 
            rs = pstmt.executeQuery(); 
             
            while(rs.next()){ 
                BoardBean board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_ID(rs.getString("BOARD_ID")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE")); 
                list.add(board); 
            } 
             
            return list; 
        }catch(Exception ex){ 
            System.out.println("getBoardList 에러 : " + ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
    
    public List getBoardList2(String board_list, int page,int limit){ 
        String board_list_sql="select * from memberboard where board_subject like ?  order by BOARD_RE_REF desc,BOARD_RE_SEQ desc limit ?,?";
               
        List list = new ArrayList(); 
        int startrow=(page-1)*limit+1; //읽기 시작할 row 번호. 
        int endrow=startrow+limit-1; //읽을 마지막 row 번호.         
        try{ 
            pstmt = con.prepareStatement(board_list_sql); 
            pstmt.setString(1, "%"+board_list+"%");
            pstmt.setInt(2, startrow-1); 
            pstmt.setInt(3, endrow-startrow+1); 
            rs = pstmt.executeQuery(); 
             
            while(rs.next()){ 
                BoardBean board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_ID(rs.getString("BOARD_ID")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE")); 
                list.add(board); 
            } 
             
            return list; 
        }catch(Exception ex){ 
            System.out.println("getBoardList 에러 : " + ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
     
    //글 내용 보기 //글 레코드 번호를 인수로 받아온다. 
    public BoardBean getDetail(int num) throws Exception{ 
        BoardBean board = null; 
        try{ 
            pstmt = con.prepareStatement( 
                    "select * from memberboard where BOARD_NUM = ?"); 
            pstmt.setInt(1, num); 
             
            rs= pstmt.executeQuery(); 
             
            if(rs.next()){ 
                board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_ID(rs.getString("BOARD_ID")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE")); 
            } 
            return board; 
        }catch(Exception ex){ 
            System.out.println("getDetail 에러 : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
     
    /*글 등록 
     * 1) board_num필드의 최대값을 얻어온다. 이유는 중복되지 않은 값을 가져오기 위해 
     * 2) 글등록쿼리 : 글쓰기는 원글이므로 답변과 관련된 필드는 모두 0으로..ref값(글그룹번호만 새로운 번호로 지정)*/ 
    public boolean boardInsert(BoardBean board){ 
        int num =0; 
        String sql=""; 
         
        int result=0; 
         
        try{ 
            pstmt=con.prepareStatement( 
                    "select max(board_num) from memberboard"); 
            rs = pstmt.executeQuery(); 
             
            if(rs.next()) 
                num =rs.getInt(1)+1;  //글이 등록되어있으면 글 번호 +1 
            else 
                num=1;//글 등록이 되어있지 않으면 num=1로 
             
            sql="insert into memberboard (BOARD_NUM,BOARD_ID,BOARD_SUBJECT,"; 
            sql+="BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,"+ 
                "BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,"+ 
                "BOARD_DATE,BOARD_PARENT) values(?,?,?,?,?,?,?,?,?,sysdate(),0)"; 
             
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1, num); 
            pstmt.setString(2, board.getBOARD_ID()); 
            pstmt.setString(3, board.getBOARD_SUBJECT()); 
            pstmt.setString(4, board.getBOARD_CONTENT()); 
            pstmt.setString(5, board.getBOARD_FILE()); 
            pstmt.setInt(6, num); 
            pstmt.setInt(7, 0); 
            pstmt.setInt(8, 9999); 
            pstmt.setInt(9, 0); 
             
            result=pstmt.executeUpdate(); 
            if(result==0)return false; 
             
            return true; 
        }catch(Exception ex){ 
            System.out.println("boardInsert 에러 : "+ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return false; 
    } 
     
    /*글 답변 등록 */ 
    public int boardReply(BoardBean board){ 
        String board_max_sql="select max(board_num) from memberboard"; 
        String sql=""; 
        int num=0; 
        int result=0; 
         
        int re_ref=board.getBOARD_RE_REF(); //원본글 번호 
        int re_lev=board.getBOARD_RE_LEV(); // 답변글의 깊이 
        int re_seq=board.getBOARD_RE_SEQ(); //답변글의 순서 
         
        try{ 
            pstmt=con.prepareStatement(board_max_sql); 
            rs = pstmt.executeQuery(); 
            if(rs.next())num =rs.getInt(1)+1; //답변글이 있을경우 max에 +1해서 글번호를 먹여준다. 
            else num=1;  //글 등록이 되어있지 않으면 num=1 
             
            /*ref값과 seq값을 확인하여 원본 글에 다른 답변 글이 있으면,  
             * 답변 글 중 답변 글보다 상위에 있는 글의 seq보다 높은 글의 seq값을 1씩 증가시킨다.*/ 
            sql="update memberboard set BOARD_RE_SEQ=BOARD_RE_SEQ+1 "; 
            sql+="where BOARD_RE_REF=? and BOARD_RE_SEQ<?"; 
             
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1,re_ref); 
            pstmt.setInt(2,re_seq); 
            result=pstmt.executeUpdate(); 
             
            re_seq = re_seq -1; //답변 글이 원본 글 보다 아래에 출력되어야 하기 때문에 re_seq값을 1증가시킨다. 
            re_lev = re_lev+1; // 답변을 하는것이므로 현재 답변 레벨 단계에서 1을 증가시킨다. 
             
            sql="insert into memberboard (BOARD_NUM,BOARD_ID,BOARD_SUBJECT,"; 
            sql+="BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"; 
            sql+="BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE,BOARD_PARENT) "; 
            sql+="values(?,?,?,?,?,?,?,?,?,sysdate(),?)"; 
             
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1, num); 
            pstmt.setString(2, board.getBOARD_ID()); 
            pstmt.setString(3, board.getBOARD_SUBJECT()); 
            pstmt.setString(4, board.getBOARD_CONTENT()); 
            pstmt.setString(5, ""); //답장에는 파일을 업로드하지 않음. 
            pstmt.setInt(6, re_ref); 
            pstmt.setInt(7, re_lev); 
            pstmt.setInt(8, re_seq); 
            pstmt.setInt(9, 0); 
            pstmt.setInt(10, board.getBOARD_NUM()); 
            pstmt.executeUpdate(); 
            return num; 
        }catch(SQLException ex){ 
            System.out.println("boardReply 에러 : "+ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){} 
        } 
        return 0; 
    } 
     
    //글 수정. 
    public boolean boardModify(BoardBean modifyboard) throws Exception{ 
        String sql="update memberboard set BOARD_SUBJECT=?,"; 
        sql+="BOARD_CONTENT=? where BOARD_NUM=?"; 
         
        try{ 
            pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, modifyboard.getBOARD_SUBJECT()); 
            pstmt.setString(2, modifyboard.getBOARD_CONTENT()); 
            pstmt.setInt(3, modifyboard.getBOARD_NUM()); 
            pstmt.executeUpdate(); 
            return true; 
        }catch(Exception ex){ 
            System.out.println("boardModify 에러 : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){} 
            } 
        return false; 
    } 
    public boolean delete( 
            Vector<Integer> v) {
    	  int result=0; 
          
        PreparedStatement stmt = null;
        String sql="delete from BOARD_COMMENT where comment_board=?";
        String sql2 = "DELETE FROM memberboard WHERE board_num = ?";
        
        try {

        	con.setAutoCommit(false);

        	stmt = con.prepareStatement(sql.toString());

        	for(int i = 0; i < v.size(); i++) {
        		   
            Integer value = (Integer) v.elementAt(i);
        	stmt.setInt(1,value.intValue());
        	stmt.executeUpdate();
        	 }
            stmt = con.prepareStatement(sql2);
            for(int i = 0; i < v.size(); i++) {
                Integer value = (Integer) v.elementAt(i);
                stmt.setInt(1, value.intValue());
                result+=stmt.executeUpdate();
            }
            if(result!=0){
                con.commit(); // 완료시 커밋
            }

        } catch (SQLException e) {
        	try {
                con.rollback(); // 오류시 롤백
                return false; 
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } 

        		System.out.println("delete 에러 : " + e); 
             
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

    
    public boolean boardDelete(int num){ 
   
        String board_delete_sql=" update memberboard set board_re_lev =? where board_num= ?"; 
        
        int result=0; 
         
        try{ 
        	con.setAutoCommit(false);
         	String sql="delete from BOARD_COMMENT where comment_board=?";
        	pstmt = con.prepareStatement(sql.toString());
        	pstmt.setInt(1,num);
        	int flag = pstmt.executeUpdate();
	           
            pstmt=con.prepareStatement(board_delete_sql); 
            pstmt.setInt(1,-1);
            pstmt.setInt(2,num);
            
            result=pstmt.executeUpdate(); 
            if(result!=0){
                con.commit(); // 완료시 커밋
            }

            return true; 
            
        }catch(Exception ex){ 
        	try {
	                con.rollback(); // 오류시 롤백
	                return false; 
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	            } 
            System.out.println("boardDelete 에러 : "+ex); 
            
        }finally{ 
            try{ 
                if(pstmt!=null)pstmt.close(); 
            }catch(Exception ex) {} 
        } 
         
        return false; 
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
     
    //글쓴이인지 확인(글쓴이를 확인할 글의 정보를 얻는다. 
    public boolean isBoardWriter(int num,String id){ 
        String board_sql="select * from memberboard where BOARD_NUM=?"; 
         
        try{ 
            pstmt=con.prepareStatement(board_sql); 
            pstmt.setInt(1, num); 
            rs=pstmt.executeQuery(); 
            rs.next(); 
             
            if(id.equals(rs.getString("BOARD_ID"))){ 
                return true; 
            } 
        }catch(SQLException ex){ 
            System.out.println("isBoardWriter 에러 : "+ex); 
        } 
        return false; 
    } 
    public List getNoticeList(int page,int limit){ 
        String board_list_sql="select * from membernotice order by BOARD_RE_REF desc,BOARD_RE_SEQ desc limit ?,?";
         
         
        List list = new ArrayList(); 
         
        int startrow=(page-1)*limit+1; //읽기 시작할 row 번호. 
        int endrow=startrow+limit-1; //읽을 마지막 row 번호.         
        try{ 
            pstmt = con.prepareStatement(board_list_sql); 
            pstmt.setInt(1, startrow-1); 
            pstmt.setInt(2, endrow-startrow+1); 
            rs = pstmt.executeQuery(); 
             
            while(rs.next()){ 
                BoardBean board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_ID(rs.getString("BOARD_ID")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE")); 
                list.add(board); 
            } 
             
            return list; 
        }catch(Exception ex){ 
            System.out.println("getnoticeList 에러 : " + ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
    public List getNoticeList2(String board_list, int page,int limit){ 
        String board_list_sql="select * from membernotice where board_subject like ?  order by BOARD_RE_REF desc,BOARD_RE_SEQ desc limit ?,?";
               
        List list = new ArrayList(); 
        int startrow=(page-1)*limit+1; //읽기 시작할 row 번호. 
        int endrow=startrow+limit-1; //읽을 마지막 row 번호.         
        try{ 
            pstmt = con.prepareStatement(board_list_sql); 
            pstmt.setString(1, "%"+board_list+"%");
            pstmt.setInt(2, startrow-1); 
            pstmt.setInt(3, endrow-startrow+1); 
            rs = pstmt.executeQuery(); 
             
            while(rs.next()){ 
                BoardBean board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_ID(rs.getString("BOARD_ID")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE")); 
                list.add(board); 
            } 
             
            return list; 
        }catch(Exception ex){ 
            System.out.println("getBoardList 에러 : " + ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
    public boolean noticeInsert(BoardBean board){ 
        int num =0; 
        String sql=""; 
         
        int result=0; 
         
        try{ 
            pstmt=con.prepareStatement( 
                    "select max(board_num) from membernotice"); 
            rs = pstmt.executeQuery(); 
             
            if(rs.next()) 
                num =rs.getInt(1)+1;  //글이 등록되어있으면 글 번호 +1 
            else 
                num=1;//글 등록이 되어있지 않으면 num=1로 
             
            sql="insert into membernotice (BOARD_NUM,BOARD_ID,BOARD_SUBJECT,"; 
            sql+="BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,"+ 
                "BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,"+ 
                "BOARD_DATE,BOARD_PARENT) values(?,?,?,?,?,?,?,?,?,sysdate(),0)"; 
             
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1, num); 
            pstmt.setString(2, board.getBOARD_ID()); 
            pstmt.setString(3, board.getBOARD_SUBJECT()); 
            pstmt.setString(4, board.getBOARD_CONTENT()); 
            pstmt.setString(5, board.getBOARD_FILE()); 
            pstmt.setInt(6, num); 
            pstmt.setInt(7, 0); 
            pstmt.setInt(8, 9999); 
            pstmt.setInt(9, 0); 
             
            result=pstmt.executeUpdate(); 
            if(result==0)return false; 
             
            return true; 
        }catch(Exception ex){ 
            System.out.println("noticeInsert 에러 : "+ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return false; 
    }   
    
    public BoardBean getnoticeDetail(int num) throws Exception{ 
        BoardBean board = null; 
        try{ 
            pstmt = con.prepareStatement( 
                    "select * from membernotice where BOARD_NUM = ?"); 
            pstmt.setInt(1, num); 
             
            rs= pstmt.executeQuery(); 
             
            if(rs.next()){ 
                board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_ID(rs.getString("BOARD_ID")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE")); 
            } 
            return board; 
        }catch(Exception ex){ 
            System.out.println("getDetail 에러 : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
    public void setReadCountnotice(int num) throws Exception{ 
        String sql="update membernotice set BOARD_READCOUNT = "+ 
            "BOARD_READCOUNT+1 where BOARD_NUM = "+num; 
         
        try{ 
            pstmt=con.prepareStatement(sql); 
            pstmt.executeUpdate(); 
        }catch(SQLException ex){ 
            System.out.println("setReadCountUpdate 에러 : "+ex); 
        } 
    }  
    public int getnoticeListCount() { 
        int x= 0; 
         
        try{ 
            pstmt=con.prepareStatement("select count(*) from membernotice"); 
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

	public int getnoticeListCount2(String board_list) {
		   int x= 0; 
	         
	        try{ 
	            pstmt=con.prepareStatement("select count(*) from membernotice  where board_subject like ? "); 
	            pstmt.setString(1, "%"+board_list+"%");
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
    public boolean noticeModify(BoardBean modifyboard) throws Exception{ 
        String sql="update membernotice set BOARD_SUBJECT=?,"; 
        sql+="BOARD_CONTENT=? where BOARD_NUM=?"; 
         
        try{ 
            pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, modifyboard.getBOARD_SUBJECT()); 
            pstmt.setString(2, modifyboard.getBOARD_CONTENT()); 
            pstmt.setInt(3, modifyboard.getBOARD_NUM()); 
            pstmt.executeUpdate(); 
            return true; 
        }catch(Exception ex){ 
            System.out.println("noticeModify 에러 : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){} 
            } 
        return false; 
    } 
    public boolean isNoticeWriter(int num,String id){ 
        String board_sql="select * from membernotice where BOARD_NUM=?"; 
         
        try{ 
            pstmt=con.prepareStatement(board_sql); 
            pstmt.setInt(1, num); 
            rs=pstmt.executeQuery(); 
            rs.next(); 
             
            if(id.equals(rs.getString("BOARD_ID"))){ 
                return true; 
            }
        }catch(SQLException ex){ 
            System.out.println("isBoardWriter 에러 : "+ex); 
        } 
        return false; 
    }
    public boolean Noticedelete( 
            Vector<Integer> v) {
    	  int result=0; 
          
        PreparedStatement stmt = null;
        String sql="delete from BOARD_COMMENT where comment_board=?";
        String sql2 = "DELETE FROM memberNotice WHERE board_num = ?";
        
        try {

        	con.setAutoCommit(false);

        	stmt = con.prepareStatement(sql.toString());

        	for(int i = 0; i < v.size(); i++) {
        		   
            Integer value = (Integer) v.elementAt(i);
        	stmt.setInt(1,value.intValue());
        	stmt.executeUpdate();
        	 }
            stmt = con.prepareStatement(sql2);
            for(int i = 0; i < v.size(); i++) {
                Integer value = (Integer) v.elementAt(i);
                stmt.setInt(1, value.intValue());
                result+=stmt.executeUpdate();
            }
            if(result!=0){
                con.commit(); // 완료시 커밋
            }

        } catch (SQLException e) {
        	try {
                con.rollback(); // 오류시 롤백
                return false; 
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } 

        		System.out.println("delete 에러 : " + e); 
             
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
    public List getQAList(int page,int limit){ 
        String board_list_sql="select * from memberqa order by BOARD_RE_REF desc,BOARD_RE_SEQ desc limit ?,?";
         
         
        List list = new ArrayList(); 
         
        int startrow=(page-1)*limit+1; //읽기 시작할 row 번호. 
        int endrow=startrow+limit-1; //읽을 마지막 row 번호.         
        try{ 
            pstmt = con.prepareStatement(board_list_sql); 
            pstmt.setInt(1, startrow-1); 
            pstmt.setInt(2, endrow-startrow+1); 
            rs = pstmt.executeQuery(); 
             
            while(rs.next()){ 
                BoardBean board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_ID(rs.getString("BOARD_ID")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE")); 
                list.add(board); 
            } 
             
            return list; 
        }catch(Exception ex){ 
            System.out.println("getBoardList 에러 : " + ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    }   public int getQAListCount2(String board_list) { 
        int x= 0; 
         
        try{ 
            pstmt=con.prepareStatement("select count(*) from memberqa  where board_subject like ? "); 
            pstmt.setString(1, "%"+board_list+"%");
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
    }    public List getQAList2(String board_list, int page,int limit){ 
        String board_list_sql="select * from memberqa where board_subject like ?  order by BOARD_RE_REF desc,BOARD_RE_SEQ desc limit ?,?";
               
        List list = new ArrayList(); 
        int startrow=(page-1)*limit+1; //읽기 시작할 row 번호. 
        int endrow=startrow+limit-1; //읽을 마지막 row 번호.         
        try{ 
            pstmt = con.prepareStatement(board_list_sql); 
            pstmt.setString(1, "%"+board_list+"%");
            pstmt.setInt(2, startrow-1); 
            pstmt.setInt(3, endrow-startrow+1); 
            rs = pstmt.executeQuery(); 
             
            while(rs.next()){ 
                BoardBean board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_ID(rs.getString("BOARD_ID")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE")); 
                list.add(board); 
            } 
             
            return list; 
        }catch(Exception ex){ 
            System.out.println("getBoardList 에러 : " + ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    }   public boolean qaInsert(BoardBean board){ 
        int num =0; 
        String sql=""; 
         
        int result=0; 
         
        try{ 
            pstmt=con.prepareStatement( 
                    "select max(board_num) from memberqa"); 
            rs = pstmt.executeQuery(); 
             
            if(rs.next()) 
                num =rs.getInt(1)+1;  //글이 등록되어있으면 글 번호 +1 
            else 
                num=1;//글 등록이 되어있지 않으면 num=1로 
             
            sql="insert into memberqa (BOARD_NUM,BOARD_ID,BOARD_SUBJECT,"; 
            sql+="BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,"+ 
                "BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,"+ 
                "BOARD_DATE,BOARD_PARENT) values(?,?,?,?,?,?,?,?,?,sysdate(),0)"; 
             
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1, num); 
            pstmt.setString(2, board.getBOARD_ID()); 
            pstmt.setString(3, board.getBOARD_SUBJECT()); 
            pstmt.setString(4, board.getBOARD_CONTENT()); 
            pstmt.setString(5, board.getBOARD_FILE()); 
            pstmt.setInt(6, num); 
            pstmt.setInt(7, 0); 
            pstmt.setInt(8, 9999); 
            pstmt.setInt(9, 0); 
             
            result=pstmt.executeUpdate(); 
            if(result==0)return false; 
             
            return true; 
        }catch(Exception ex){ 
            System.out.println("noticeInsert 에러 : "+ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return false; 
    }   
      
    public BoardBean getqaDetail(int num) throws Exception{ 
        BoardBean board = null; 
        try{ 
            pstmt = con.prepareStatement( 
                    "select * from memberqa where BOARD_NUM = ?"); 
            pstmt.setInt(1, num); 
             
            rs= pstmt.executeQuery(); 
             
            if(rs.next()){ 
                board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_ID(rs.getString("BOARD_ID")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE")); 
            } 
            return board; 
        }catch(Exception ex){ 
            System.out.println("getDetail 에러 : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
    public void setReadCountqa(int num) throws Exception{ 
        String sql="update memberqa set BOARD_READCOUNT = "+ 
            "BOARD_READCOUNT+1 where BOARD_NUM = "+num; 
         
        try{ 
            pstmt=con.prepareStatement(sql); 
            pstmt.executeUpdate(); 
        }catch(SQLException ex){ 
            System.out.println("setReadCountUpdate 에러 : "+ex); 
        } 
    }    public int qaReply(BoardBean board){ 
        String board_max_sql="select max(board_num) from memberqa"; 
        String sql=""; 
        int num=0; 
        int result=0; 
         
        int re_ref=board.getBOARD_RE_REF(); //원본글 번호 
        int re_lev=board.getBOARD_RE_LEV(); // 답변글의 깊이 
        int re_seq=board.getBOARD_RE_SEQ(); //답변글의 순서 
         
        try{ 
            pstmt=con.prepareStatement(board_max_sql); 
            rs = pstmt.executeQuery(); 
            if(rs.next())num =rs.getInt(1)+1; //답변글이 있을경우 max에 +1해서 글번호를 먹여준다. 
            else num=1;  //글 등록이 되어있지 않으면 num=1 
             
            /*ref값과 seq값을 확인하여 원본 글에 다른 답변 글이 있으면,  
             * 답변 글 중 답변 글보다 상위에 있는 글의 seq보다 높은 글의 seq값을 1씩 증가시킨다.*/ 
            sql="update memberqa set BOARD_RE_SEQ=BOARD_RE_SEQ+1 "; 
            sql+="where BOARD_RE_REF=? and BOARD_RE_SEQ<?"; 
             
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1,re_ref); 
            pstmt.setInt(2,re_seq); 
            result=pstmt.executeUpdate(); 
             
            re_seq = re_seq -1; //답변 글이 원본 글 보다 아래에 출력되어야 하기 때문에 re_seq값을 1증가시킨다. 
            re_lev = re_lev+1; // 답변을 하는것이므로 현재 답변 레벨 단계에서 1을 증가시킨다. 
             
            sql="insert into memberqa (BOARD_NUM,BOARD_ID,BOARD_SUBJECT,"; 
            sql+="BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"; 
            sql+="BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE,BOARD_PARENT) "; 
            sql+="values(?,?,?,?,?,?,?,?,?,sysdate(),?)"; 
             
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1, num); 
            pstmt.setString(2, board.getBOARD_ID()); 
            pstmt.setString(3, board.getBOARD_SUBJECT()); 
            pstmt.setString(4, board.getBOARD_CONTENT()); 
            pstmt.setString(5, ""); //답장에는 파일을 업로드하지 않음. 
            pstmt.setInt(6, re_ref); 
            pstmt.setInt(7, re_lev); 
            pstmt.setInt(8, re_seq); 
            pstmt.setInt(9, 0); 
            pstmt.setInt(10, board.getBOARD_NUM()); 
            pstmt.executeUpdate(); 
            return num; 
        }catch(SQLException ex){ 
            System.out.println("boardReply 에러 : "+ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){} 
        } 
        return 0; 
    }     public boolean isqaWriter(int num,String id){ 
        String board_sql="select * from memberqa where BOARD_NUM=?"; 
         
        try{ 
            pstmt=con.prepareStatement(board_sql); 
            pstmt.setInt(1, num); 
            rs=pstmt.executeQuery(); 
            rs.next(); 
             
            if(id.equals(rs.getString("BOARD_ID"))){ 
                return true; 
            } 
        }catch(SQLException ex){ 
            System.out.println("isBoardWriter 에러 : "+ex); 
        } 
        return false; 
    } 
    public boolean qaModify(BoardBean modifyboard) throws Exception{ 
        String sql="update memberqa set BOARD_SUBJECT=?,"; 
        sql+="BOARD_CONTENT=? where BOARD_NUM=?"; 
         
        try{ 
            pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, modifyboard.getBOARD_SUBJECT()); 
            pstmt.setString(2, modifyboard.getBOARD_CONTENT()); 
            pstmt.setInt(3, modifyboard.getBOARD_NUM()); 
            pstmt.executeUpdate(); 
            return true; 
        }catch(Exception ex){ 
            System.out.println("boardModify 에러 : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){} 
            } 
        return false; 
    } 
    public boolean qadelete( 
            Vector<Integer> v) {
    	  int result=0; 
          
        PreparedStatement stmt = null;
        String sql="delete from BOARD_COMMENT where comment_board=?";
        String sql2 = "DELETE FROM memberqa WHERE board_num = ?";
        
        try {

        	con.setAutoCommit(false);

        	stmt = con.prepareStatement(sql.toString());

        	for(int i = 0; i < v.size(); i++) {
        		   
            Integer value = (Integer) v.elementAt(i);
        	stmt.setInt(1,value.intValue());
        	stmt.executeUpdate();
        	 }
            stmt = con.prepareStatement(sql2);
            for(int i = 0; i < v.size(); i++) {
                Integer value = (Integer) v.elementAt(i);
                stmt.setInt(1, value.intValue());
                result+=stmt.executeUpdate();
            }
            if(result!=0){
                con.commit(); // 완료시 커밋
            }

        } catch (SQLException e) {
        	try {
                con.rollback(); // 오류시 롤백
                return false; 
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } 

        		System.out.println("delete 에러 : " + e); 
             
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
    


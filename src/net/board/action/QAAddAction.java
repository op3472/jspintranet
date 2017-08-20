package net.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class QAAddAction implements Action{
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
        BoardDAO boarddao = new BoardDAO(); 
        BoardBean boarddata = new BoardBean(); 
        ActionForward forward = new ActionForward(); 
         
        String saveFolder="C:/Users/user/workspace/webreport/WebContent/boardupload";         
        int fileSize=5*1024*1024; 
         
        boolean result=false; 
         
        MultipartRequest multi; 
        try { 
            multi = new MultipartRequest(request, saveFolder, fileSize, "euc-kr", new DefaultFileRenamePolicy()); 
            boarddata.setBOARD_ID(multi.getParameter("BOARD_ID")); 
            boarddata.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT")); 
            boarddata.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT")); 
            boarddata.setBOARD_FILE(multi.getFilesystemName((String)multi.getFileNames().nextElement())); 
            result=boarddao.qaInsert(boarddata); 
             
            if(result==false){ 
                System.out.println("게시판 등록 실패"); 
                return null; 
            } 
            System.out.println("게시판 등록 완료");             
            forward.setRedirect(true); 
            forward.setPath("./QAList.bo"); 
            return forward; 
        } catch (IOException e) { 
            e.printStackTrace(); 
        }         
        return null; 
    } 

}

package message.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lecture.action.AcademicAddAction;
import lecture.action.AcademicCanlendarDetail;
import lecture.action.AcademicCanlendarUpdate;
import lecture.action.AcademicCanlendarView;
import lecture.action.AcademinCanlendarDelete;
import lecture.action.AcademinCanlendarUpdateForm;
import lecture.action.CalAddAction;
import lecture.action.CalDelete;
import lecture.action.CalDetail;
import lecture.action.CalUpdate;
import lecture.action.CalUpdateForm;
import lecture.action.CanlendarView;
import lecture.action.CommentLectureWriteAciton;
import lecture.action.CommentUpdateAction;
import lecture.action.CommentUpdateFormAction;
import lecture.action.Commmentdelete;
import lecture.action.LectureAddAction;
import lecture.action.LectureDelete;
import lecture.action.LectureDetailAction;
import lecture.action.LectureListAction;
import lecture.action.LectureListAction2;
import lecture.action.LectureregList;
import lecture.action.Member_view;
import lecture.action.applicationAction;
import lecture.action.applideleteAction;
import lecture.action.graderesult;
import lecture.action.memberList;
import lecture.action.writegrade;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class MessageController extends HttpServlet implements Servlet{
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        String RequestURI = request.getRequestURI(); 
        String contextPath=request.getContextPath(); 
        String command=RequestURI.substring(contextPath.length()); 
        ActionForward forward=null; 
        Action action=null; 
         
        if(command.equals("/MessageListView.ms")){ 
            action =new MessageListView();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }
        else if(command.equals("/MessageWrite.ms")){ 
            action =new MessageWrite();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }
        else if(command.equals("/MessageDetail.ms")){ 
            action =new MessageDetail();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }
        else if(command.equals("/MessageDelete.ms")){ 
            action =new MessageDelete();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }
        else if(command.equals("/MessageCheckboxDelete.ms")){ 
            action =new MessageCheckboxDelete();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }
      if(forward !=null){ 
            if(forward.isRedirect()){ 
                response.sendRedirect(forward.getPath()); 
            }else{ 
                RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath()); 
                dispatcher.forward(request,response);                 
            } 
        }     
    } 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        doProcess(request, response); 
    } 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        doProcess(request, response); 
    } 
 }


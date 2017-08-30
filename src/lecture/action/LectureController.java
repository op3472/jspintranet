package lecture.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class LectureController extends HttpServlet implements Servlet{
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        String RequestURI = request.getRequestURI(); 
        String contextPath=request.getContextPath(); 
        String command=RequestURI.substring(contextPath.length()); 
        ActionForward forward=null; 
        Action action=null; 
         
        if(command.equals("/LectureList.le")){ 
            action =new LectureListAction();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }
        else if(command.equals("/LectureList2.le")){ 
            action =new LectureListAction2();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }else if(command.equals("/LectureDetailAction.le")){ 
            action =new LectureDetailAction();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }
        else if(command.equals("/CommentWriteAction.le")){ 
            action =new CommentLectureWriteAciton();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }
        else if(command.equals("/applicationAction.le")){ 
            action =new applicationAction();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }
    else if(command.equals("/applideleteAction.le")){ 
        action =new applideleteAction();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }             
    }
    else if(command.equals("/commentdelete.le")){ 
        action =new Commmentdelete();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }             
    } else if(command.equals("/CommentUpdateFormAction.le")){ 
        action =new CommentUpdateFormAction();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }             
    } else if(command.equals("/CommentUpdateAction.le")){ 
        action =new CommentUpdateAction();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    }  else if(command.equals("/LectureregList.le")){ 
        action =new LectureregList();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    } else if(command.equals("/LectureWrite.le")){ 
            forward=new ActionForward(); 
            forward.setRedirect(false); 
            forward.setPath("./lecture/lecture_write.jsp"); 
    }
    else if(command.equals("/LectureAddAction.le")){ 
        action =new LectureAddAction();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    }   else if(command.equals("/LectureDelete.le")){ 
        action =new LectureDelete();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    }  else if(command.equals("/memberList.le")){ 
        action =new memberList();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    } else if(command.equals("/Member_view.le")){ 
        action =new Member_view();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    }else if(command.equals("/writegrade.le")){ 
        action =new writegrade();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    }else if(command.equals("/graderesult.le")){ 
        action =new graderesult();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    }
    else if(command.equals("/canlendar.le")){ 
        action =new CanlendarView();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    }
    else if(command.equals("/CalAddAction.le")){ 
        action =new CalAddAction();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    }
    else if(command.equals("/CalDetail.le")){ 
        action =new CalDetail();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    }
    else if(command.equals("/CalDelete.le")){ 
        action =new CalDelete();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    }    
    else if(command.equals("/CalUpdateForm.le")){ 
        action =new CalUpdateForm();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    } 
    else if(command.equals("/CalUpdate.le")){ 
        action =new CalUpdate();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    } 
    else if(command.equals("/AcademicCanlendarView.le")){ 
        action =new AcademicCanlendarView();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    }     
    else if(command.equals("/AcademicAddAction.le")){ 
        action =new AcademicAddAction();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    }     
    else if(command.equals("/AcademicCanlendarDetail.le")){ 
        action =new AcademicCanlendarDetail();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    }             
    else if(command.equals("/AcademinCanlendarUpdateForm.le")){ 
        action =new AcademinCanlendarUpdateForm();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    } 
    else if(command.equals("/AcademicCanlendarUpdate.le")){ 
        action =new AcademicCanlendarUpdate();
        try{ 
            forward = action.execute(request,  response); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        }      
    }     
    else if(command.equals("/AcademinCanlendarDelete.le")){ 
        action =new AcademinCanlendarDelete();
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



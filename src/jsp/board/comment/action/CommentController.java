package jsp.board.comment.action;

import java.io.IOException; 

import javax.servlet.RequestDispatcher; 
import javax.servlet.Servlet; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import net.commons.action.Action; 
import net.commons.action.ActionForward; 

public class CommentController  extends HttpServlet implements Servlet{
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        String RequestURI = request.getRequestURI(); 
        String contextPath=request.getContextPath(); 
        String command=RequestURI.substring(contextPath.length()); 
        ActionForward forward=null; 
        Action action=null; 
         
        if(command.equals("/CommentWriteAction.co")){ 
            action =new CommentWriteAciton();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }
        if(command.equals("/CommentReplyAction.co")){ 
            action =new CommentReplyAction();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }  
        if(command.equals("/CommentReplyFormAction.co")){ 
            action =new CommentReplyFormAction();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        } 
        if(command.equals("/CommentUpdateFormAction.co")){ 
            action =new CommentUpdateFormAction();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        } 	
        if(command.equals("/CommentUpdateAction.co")){ 
            action =new CommentUpdateAction();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }      
        }     
        if(command.equals("/CommentDeleteAction.co")){ 
            action =new CommentDeleteAction();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }      
        }if(command.equals("/CommentnoticeWriteAction.co")){ 
            action =new CommentnoticeWriteAciton();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        } if(command.equals("/CommentnoticeReplyFormAction.co")){ 
            action =new CommentnoticeReplyFormAction();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }   if(command.equals("/CommentnoticeReplyAction.co")){ 
            action =new CommentnoticeReplyAction();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        } if(command.equals("/CommentnoticeDeleteAction.co")){ 
            action =new CommentnoticeDeleteAction();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }      
        }   if(command.equals("/CommentnoticeUpdateAction.co")){ 
            action =new CommentnoticeUpdateAction();
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }      
        }     
        if(command.equals("/CommentnoticeUpdateFormAction.co")){ 
            action =new CommentnoticeUpdateFormAction();
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

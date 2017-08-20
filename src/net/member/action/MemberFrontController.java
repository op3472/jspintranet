package net.member.action;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher; 
import javax.servlet.Servlet; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import net.commons.action.Action; 
import net.commons.action.ActionForward; 

public class MemberFrontController extends HttpServlet implements Servlet{ 
    static final long serialVersionUID=1L; 
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        String RequestURI=request.getRequestURI(); 
        String contextPath=request.getContextPath(); 
        String command = RequestURI.substring(contextPath.length()); 
        ActionForward forward = null; 
        Action action = null; 
        if(command.equals("/MemberLogin.me")){ 
            forward=new ActionForward(); 
            forward.setRedirect(false); 
            forward.setPath("./loginForm.jsp"); 
        }else if(command.equals("/MemberLoginAction.me")){ 
            action=new MemberLoginAction(); 
            try { 
                forward=action.execute(request, response); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        }else if(command.equals("/MemberJoin.me")){ 
            forward = new ActionForward(); 
            forward.setRedirect(false); 
        }else if(command.equals("/MemberJoinAction.me")){ 
            action=new MemberJoinAction(); 
            try { 
                forward=action.execute(request, response); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        }
        else if(command.equals("/MemberModifyAction.me")){ 
            action=new MemberModifyAction(); 
            try { 
                forward=action.execute(request, response); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        }
        else if(command.equals("/MemberIdCheckAction.me")){
        	action=new MemberIdCheckAction();
        	try { 
                action.execute(request, response); 
               } catch (Exception e) { 
                   e.printStackTrace(); 
               } 
        }
        if(forward!=null){ 
            if(forward.isRedirect()){ 
                response.sendRedirect(forward.getPath()); 
            }else{ 
                RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath()); 
                dispatcher.forward(request, response); 
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





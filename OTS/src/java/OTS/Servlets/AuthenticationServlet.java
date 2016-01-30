/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.Servlets;

import OTS.Account;
import OTS.AuthenticationResponse;
import OTS.Credential;
import OTS.ISerializable;
import OTS.Message;
import OTS.ObjectModels.Response;
import OTS.ObjectModels.RoleAccount;
import OTS.University;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MEA
 */
public class AuthenticationServlet extends Servlet {

  
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
           
          String command=  this.ExtractRequestCommand(request);
          ISerializable ser=   this.ExecuteCommand(command, request);
           out.println(ser.ToJson());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected ISerializable ExecuteCommand(String action,HttpServletRequest request) {
           Message message= new Response("","");
       try{
         //  int error=   Integer.parseInt(request.getParameter("username"));
        switch(action){
              case  "login":
                 this.CreateUniversity(request);
                 University university=this.LoadUniversity(request);
               
                 Account account=new RoleAccount(message);
                 String userName = request.getParameter("username");
                 String password =request.getParameter("password");
                 Credential credential= new Credential(userName,password);
                 account.Login(university, credential);
                 this.CreateSession(request, credential.userProfile);
                 AuthenticationResponse ar=new AuthenticationResponse(credential);
                 message.ChangeContent(ar.ToJsonResponse());
                  break;
              case "logout":
              
                  request.getSession().invalidate();
                  message= new  Response("ok","-");
              case "createAccout":
                  
               break;
                  
              default:
                    return new  Response("invalid action","-");
               
          }
      
       }
       catch(Throwable ex){
            message.ChangeStatus("exception");
            message.UpdateError("AuthenticationServlet: " + ex.toString());
       }
         return  message;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.Servlets;

import OTS.DataModels.MySqlDataSource;
import OTS.ISerializable;
import OTS.ObjectModels.ITestItemGeneration;
import OTS.ObjectModels.PublicUniversity;
import OTS.ObjectModels.TestAlgorithms.ListTrueFalseCorrectAlgorithm;
import OTS.ObjectModels.UserProfile;
import OTS.University;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MEA
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public abstract class Servlet extends HttpServlet {


     private final String UniversityKey="university";
     private final String UserSession="usersession";
     
   
    protected  void CreateUniversity(HttpServletRequest request){
       if(!this.IsUniversityCreated(request)){
        University university = new PublicUniversity(new MySqlDataSource());
        HttpSession session=request.getSession(true);
        session.setAttribute(this.UniversityKey, university);
       }
    }
    protected  University LoadUniversity(HttpServletRequest request){
        HttpSession session= request.getSession(true);
        Object obj=  (University)session.getAttribute(UniversityKey);
        if(obj!=null){
             return (University)obj;
        }
        this.CreateUniversity(request);
        return (University)session.getAttribute(UniversityKey);
    }
    protected   void CreateSession(HttpServletRequest request,UserProfile userProfile){
        
          HttpSession session=request.getSession(true);
          //UserSession userSession= new UserSession(userId,request);
         session.setAttribute(this.UserSession, userProfile); 
    }
    protected  UserProfile LoadSession(HttpServletRequest request){
         HttpSession session= request.getSession(false);
         
         UserProfile userProfile= (UserProfile)session.getAttribute(this.UserSession);
           return userProfile;
    }
    protected  Boolean IsUniversityCreated(HttpServletRequest request){
       
        if(request.getSession(false)==null){
            return false;
        }
        HttpSession session= request.getSession(false);
        University u=  (University)session.getAttribute(UniversityKey);
        if(u==null){
          return false;
        }
        return true;
    }
       
    protected String ExtractRequestCommand(HttpServletRequest request){
    
        String command =request.getParameter("action");
        return command;
    }
    
     protected abstract ISerializable ExecuteCommand(String action,HttpServletRequest request);
     
     protected  Boolean IsAuthenticated(HttpServletRequest request){
     
          UserProfile userProfile= LoadSession(request);
          String referer=  request.getHeader("Referer");
          if(userProfile==null || referer.equals("") ){
              return  false;
          }
          return true;
     }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
      
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

}

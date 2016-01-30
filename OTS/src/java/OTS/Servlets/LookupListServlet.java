/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.Servlets;

import OTS.DataModels.MySqlDataSource;
import OTS.ISerializable;
import OTS.Message;
import OTS.ObjectModels.Courses;
import OTS.ObjectModels.LookupLists;
import OTS.ObjectModels.Response;
import OTS.ObjectModels.UserProfile;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MEA
 */
public class LookupListServlet extends Servlet {

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
    protected ISerializable ExecuteCommand(String action, HttpServletRequest request) {
            Message response= new Response("","");
           LookupLists db= new LookupLists( new MySqlDataSource());
        try{
       
         switch(action){
              case  "ListCognitiveTypes":
                   db.ListCognitiveTypes(response);
                  break;
              case "ListQuestiontTypes":
                 db.ListQuestionTypes(response);
                  break;
                  
              case "ListQuestionnatureTypes":  
                 db.ListQuestionnatureTypes(response);
                  break;   
                  
                 case "ListCourseTestKnowledgeMaps":
                  UserProfile userProfile=this.LoadSession(request);
                  int courseId= Integer.parseInt(request.getParameter("CourseId"));
                  db.ListCourseTestKnowledgeMaps(userProfile.UserId,courseId,response);
                  break;  
              default:
                  response.UpdateError("Invalid action");
                  response.UpdateID(0);
                  response.UpdateIdentity("-");
                  break;
           }
           
        }
        catch(Throwable ex){
             response.UpdateError(ex.toString());
             response.ChangeStatus("exception");
             response.ChangeContent("");
        }
        return response; 
    }
    

}

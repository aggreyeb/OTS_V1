/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.Servlets;

import OTS.DataModels.MySqlDataSource;
import OTS.ISerializable;
import OTS.Message;
import OTS.ObjectModels.ConceptNode;
import OTS.ObjectModels.CourseItem;
import OTS.ObjectModels.Courses;
import OTS.ObjectModels.Response;
import OTS.ObjectModels.UserProfile;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MEA
 */
public class CourseServlet extends Servlet {

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
        int techerId=0;
        int courseId=0;
        try{
        UserProfile userProfile=this.LoadSession(request);
          
            Message message= new Response("","");
          Courses courses = new Courses(new MySqlDataSource());
          ConceptNode conceptNode=null;
           switch(action){
              
               case "Delete":
              
                courseId= Integer.parseInt(request.getParameter("Id"));
                courses.Delete(courseId,response); 
                 break;
               
               
              case "Save":
              String  courseNumber= request.getParameter("Number");
                courseId= Integer.parseInt(request.getParameter("Id"));
                String courseName=request.getParameter("Name");
                
                CourseItem item= new CourseItem();
                item.CourseTypeId=courseId;
                item.Number=courseNumber;
                item.Name=courseName;
                courses.Save(item,response); 
                 break;
               
               case  "ListTeacherCourse":
              courses.ListTeacherCourse(userProfile.UserId,response); 
                  break;
                  
               case  "ListTeacherUnAssignedCourses":
                techerId= Integer.parseInt(request.getParameter("TeacherId"));
                  courses.ListTeacherUnAssignedCourses(techerId,response); 
                  break; 
                   
              case  "AssignTeacherCourse":
                  techerId= Integer.parseInt(request.getParameter("TeacherId"));
                  courseId= Integer.parseInt(request.getParameter("CourseId"));
                  courses.AssignTeacherCourse(courseId,techerId,response); 
                  break;  
                  
               case  "UnAssignTeacherCourse":
                  techerId= Integer.parseInt(request.getParameter("TeacherId"));
                  courseId= Integer.parseInt(request.getParameter("CourseId"));
                  courses.UnAssignTeacherCourse(courseId,techerId,response); 
                  break;  
                
                   
                case  "ListCoursesByTeacher":
                  techerId= Integer.parseInt(request.getParameter("TeacherId"));
                    courses.ListTeacherCourse(techerId,response); // get user id from session
                  break;
                
                  
              case "ListCourseKnowledgeMap":
                  int CourseId= Integer.parseInt(request.getParameter("CourseId"));
                  courses.ListCourseKnowledgeMap(userProfile.UserId,CourseId,response);
                  break;
                  
              case "ListAllCourseKnowledgeMap":    
               courses.ListAllCourseKnowledgeMap(userProfile.UserId,response); //get user id from session
            
                  break;
           
              case "AddCourseKnowledgeMap":
                   courseId= Integer.parseInt(request.getParameter("CourseId"));
                  int knowledgeMapId= Integer.parseInt(request.getParameter("KnowledgeMapId"));
                  courses.AddCourseKnowledgeMap(courseId,knowledgeMapId,userProfile.UserId,response);
                  break;   
                  
                 case "DeleteCourseKnowledgeMap":
                  int _courseId= Integer.parseInt(request.getParameter("CourseId"));
                  int _knowledgeMapId= Integer.parseInt(request.getParameter("KnowledgeMapId"));
                  courses.DeleteCourseKnowledgeMap(_courseId,_knowledgeMapId,userProfile.UserId,response);
                  break;   
                 
                   case "IsCourseKnowledgeMapSelected":
                  int __courseId= Integer.parseInt(request.getParameter("CourseId"));
                  int __knowledgeMapId= Integer.parseInt(request.getParameter("KnowledgeMapId"));
                  courses.IsCourseKnowledgeMapSelected(__courseId,__knowledgeMapId,userProfile.UserId,response);
                  break; 
                     
                  case "ListAllCourses":
                  //courses.ListAllCourse(response);
                    courses.ListStudentUnRegistertedCourses(userProfile.UserId, response);
                  break; 
                      
                 case "ListAvailableCourse":
                  courses.ListAvailableCourse(response);
                  break;    
                      
                  case "RegisterCourses":
                  String coursesJson=request.getParameter("courses");
                   CourseItem[] items =(CourseItem[] )(new Gson().fromJson(coursesJson,CourseItem[].class ));
                  courses.RegisterCourse(userProfile.UserId,items,response);
                  break;     
                   case "RegisteredCourseTest":
                  courses.RegisteredCourseTest(userProfile.UserId,response);
                  break; 
                       
                 case "UnRegisterCourse":
                 int studentCourseId= Integer.parseInt(request.getParameter("StudentCourseId"));
                  courses.UnRegisterCourse(studentCourseId, response);
                  break;       
                 case "ListSudentActivatedTest":
                  courses.ListSudentActivatedTest(userProfile.UserId, response);
                  break;       
                     
                     //ListSudentActivatedTest
              default:
                  response.UpdateError("Invalid action");
                  response.UpdateID(0);
                  response.UpdateIdentity("-");
                  break;
           }
           
        }
        catch(Throwable ex){
             response.UpdateError(ex.getMessage());
             response.ChangeStatus("exception");
             response.ChangeContent("yes");
        }
        return response; 
    }

}

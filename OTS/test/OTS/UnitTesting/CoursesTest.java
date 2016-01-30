/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.UnitTesting;

import OTS.DataModels.MySqlDataSource;
import OTS.Message;
import OTS.ObjectModels.Courses;
import OTS.ObjectModels.Response;
import org.junit.Test;

/**
 *
 * @author MEA
 */
public class CoursesTest {
    
    @Test
    public void ListTeacherCourse(){
      int userId=1;
       Message message= new Response("","");
      Courses courses= new Courses( new MySqlDataSource());
       courses.ListTeacherCourse(userId, message);
      System.out.print(message.ToJson());
    }
    
    @Test
    public void  ListCourseKnowledgeMap(){
       int userId=1;
       int courseId=1;
       Message message= new Response("","");
      Courses courses= new Courses( new MySqlDataSource());
       courses.ListCourseKnowledgeMap(userId,courseId, message);
      System.out.print(message.ToJson());
    }
    
    @Test
    public void ListAllCourseKnowledgeMap(){
       int userId=1;
       Message message= new Response("","");
       Courses courses= new Courses( new MySqlDataSource());
       courses.ListAllCourseKnowledgeMap(userId, message);
       System.out.print(message.ToJson());
    }
    
    @Test
    public void AddCourseKnowledgeMap(){
        int courseId=1;
        int knowledgeMapId=1;
        int userId=1;
       Message message= new Response("","");
       Courses courses= new Courses( new MySqlDataSource());
       courses.AddCourseKnowledgeMap(courseId,knowledgeMapId,userId, message);
       System.out.print(message.ToJson());
    }
    
       @Test
    public void DeleteCourseKnowledgeMap(){
        int courseId=1;
        int knowledgeMapId=1;
        int userId=1;
       Message message= new Response("","");
       Courses courses= new Courses( new MySqlDataSource());
       courses.DeleteCourseKnowledgeMap(courseId,knowledgeMapId,userId, message);
       System.out.print(message.ToJson());
    }
    
    
    @Test
    public void IsCourseKnowledgeMapSelected(){
        int courseId=1;
        int knowledgeMapId=1;
        int userId=1;
       Message message= new Response("","");
       Courses courses= new Courses( new MySqlDataSource());
       courses.IsCourseKnowledgeMapSelected(courseId,knowledgeMapId,userId, message);
       System.out.print(message.ToJson());
    }
    
    
     @Test
    public void ListAllCourse(){
       
       Message message= new Response("","");
       Courses courses= new Courses( new MySqlDataSource());
       courses.ListAllCourse(message);
       System.out.print(message.ToJson());
    }
    
    
      @Test
    public void ListAvailableCourse(){
       
       Message message= new Response("","");
       Courses courses= new Courses( new MySqlDataSource());
       courses.ListAvailableCourse(message);
       System.out.print(message.ToJson());
    }
    
    
      @Test
    public void ListAllStudentRegistertedTest(){
       
       Message message= new Response("","");
       Courses courses= new Courses( new MySqlDataSource());
       int studentId=39;
       courses.RegisteredCourseTest(studentId,message);
       System.out.print(message.ToJson());
    }
    
    
    @Test
    public void AssignTeacherCourseTest(){
       
       Message message= new Response("","");
       Courses courses= new Courses( new MySqlDataSource());
       int teacherId=1;
       int courseId=3;
       courses.AssignTeacherCourse(courseId, teacherId, message);
       System.out.print(message.ToJson());
    }
    
    @Test
    public void UnAssignTeacherCourseTest(){
       
       Message message= new Response("","");
       Courses courses= new Courses( new MySqlDataSource());
       int teacherId=1;
       int courseId=3;
       courses.UnAssignTeacherCourse(courseId, teacherId, message);
       System.out.print(message.ToJson());
    }
}

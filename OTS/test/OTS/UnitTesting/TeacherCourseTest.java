/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.UnitTesting;

import OTS.DataModels.MySqlDataSource;
import OTS.Message;
import OTS.ObjectModels.AcademicTestDescription;
import OTS.ObjectModels.AcademicTests;
import OTS.ObjectModels.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;

/**
 *
 * @author MEA
 */
public class TeacherCourseTest {
   @Test 
   public void ListTeacherCourse(){
     int courseId=1;
       int teacherId=1;
       Message message= new Response("","");
       AcademicTests ac= new AcademicTests( new MySqlDataSource());
       //Act
       ac.ListTeacherCourse( teacherId, message);
      //Assert
       System.out.print(message.ToJson());
   } 
    
    
   
    @Test 
   public void ListTeacherCourseTest(){
     int courseId=1;
       int teacherId=1;
       Message message= new Response("","");
       AcademicTests ac= new AcademicTests( new MySqlDataSource());
       //Act
       ac.ListCourseTest(courseId,teacherId, message);
      //Assert
       System.out.print(message.ToJson());
   } 
    
    
    @Test
    public void CreateNewTest(){
       //Arrange
       int courseId=1;
       int teacherId=1;
       Message message= new Response("","");
       AcademicTests ac= new AcademicTests( new MySqlDataSource());
       AcademicTestDescription desc= new AcademicTestDescription();
       desc.Name="Introdution Botany Level 1";
       desc.StartDate=new Date("12/20/2014");
       desc.NumberOfQuestion=20;
       desc.StartTime= "11:00";
       desc.EndTime="11:45";
       desc.TotalMark=80F;
       
       //Act
        ac.CreateNew(courseId, teacherId, desc, message);
        
      //Assert
         System.out.print(message.ToJson());
    }
    
    @Test
    public void UpdateTest(){
       //Arrange
       Message message= new Response("","");
       AcademicTests ac= new AcademicTests( new MySqlDataSource());
       AcademicTestDescription desc= new AcademicTestDescription();
       desc.TestId=3;
       desc.Name="Introdution Botany Level 2-Update-22";
       desc.StartDate=new Date("01/01/2015");
       desc.NumberOfQuestion=50;
       desc.StartTime= "11:00";
       desc.EndTime="12:00";
       desc.TotalMark=80F;
       //Act
        ac.Update(desc, message);
      //Assert
       System.out.print(message.ToJson());
    }
    
    
      @Test
    public void DeleteTest(){
       //Arrange
       Message message= new Response("","");
       AcademicTests ac= new AcademicTests( new MySqlDataSource());
       int testId=2;
       ac.Delete(testId, message);
      //Assert
       System.out.print(message.ToJson());
    }
    
    
    @Test
    public void ParseTest() throws ParseException{
        
        String d =ParseTimeFromDate("09:30:00:00");
        System.out.print(d);
    }
    
    
    public String ParseTimeFromDate(String time) throws ParseException{
        
       String d= "2011-04-23" + " "+ time;
       // String d= "2011-04-23 09:30:51:00";
      //  System.out.println(d);
       
        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
         SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm");
        // SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm:ss");
         Date date = parseFormat.parse(d);
         System.out.println(printFormat.format(date));
         String f=  printFormat.format(date);
         return f;
        
    
    }
}

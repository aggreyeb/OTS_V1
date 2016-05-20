/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels;

import OTS.DataModels.Academiccourse;
import OTS.DataModels.CourseAssignmentDescription;
import OTS.DataModels.CourseKnowledgeMapDescription;
import OTS.DataModels.Courseassignment;
import OTS.DataModels.CourseassignmentId;
import OTS.DataModels.Courseknowledgemap;
import OTS.DataModels.CourseknowledgemapId;
import OTS.DataModels.DataSource;
import OTS.DataModels.Studentcourseregistration;
import OTS.DataModels.StudentcourseregistrationId;
import OTS.Message;
import OTS.ObjectModels.QuestionManagement.TestHistoryItem;
import com.google.gson.Gson;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MEA
 */
public class Courses {

    DataSource dataSource;

    public Courses(DataSource dataSource) {
        this.dataSource = dataSource;
    }

      public void Delete(int CourseId, Message message){
    
          try {
            //this.dataSource.Open();
         //   this.dataSource.BeginTransaction();
          Academiccourse ac= (Academiccourse) this.dataSource.Find(Academiccourse.class, new Integer(CourseId));
          if(ac!=null){
            this.dataSource.Delete(ac);
          //  this.dataSource.Commit();
            Gson g = new Gson();
            message.ChangeContent("");
            message.ChangeStatus("ok");}
          
        } catch (Throwable ex) {
            //this.dataSource.Rollback();
          
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }
    }
    
    
    public void Save(CourseItem courseItem,Message message){
    
          try {
            //this.dataSource.Open();
          //  this.dataSource.BeginTransaction();
          Academiccourse ac= (Academiccourse) this.dataSource.Find(Academiccourse.class, new Integer(courseItem.CourseTypeId));
          if(ac==null){
            Academiccourse newCourse=new Academiccourse();
            newCourse.setNumber(courseItem.Number);
            newCourse.setName(courseItem.Name);
            this.dataSource.Save(newCourse);
          }
          else{
              ac.setNumber(courseItem.Number);
              ac.setName(courseItem.Name);
              this.dataSource.Update(ac);
          }
          //  this.dataSource.Commit();
            Gson g = new Gson();
            message.ChangeContent("");
            message.ChangeStatus("ok");
        } catch (Throwable ex) {
            //this.dataSource.Rollback();
          
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }
    }
    
    public void AssignTeacherCourse(int courseId, int TeacherId, Message message) {

        try {
           int IsCompleted=0;
            String sql = "insert into courseassignment(TeacherId,CourseId,AssignOn,IsCompleted) Values(" + TeacherId + "," + courseId + "," + "Now()," + IsCompleted + ")"; 
              
       // this.dataSource.Open();
          //  this.dataSource.BeginTransaction();
            this.dataSource.ExecuteNonQuery(sql);
           // this.dataSource.Commit();
            Gson g = new Gson();
            message.ChangeContent("");
            message.ChangeStatus("ok");
        } catch (Throwable ex) {
            //this.dataSource.Rollback();
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
            //this.dataSource.Close();
        }

    }

    public void UnAssignTeacherCourse(int courseId, int TeacherId, Message message) {

        try {
            String sql = "delete from courseassignment where  TeacherId=" + TeacherId + " and CourseId=" + courseId;

           // this.dataSource.Open();
           // this.dataSource.BeginTransaction();
            this.dataSource.ExecuteNonQuery(sql);
           // this.dataSource.Commit();

            message.ChangeContent("");
            message.ChangeStatus("ok");
        } catch (Throwable ex) {
           // this.dataSource.Rollback();
            message.ChangeContent("");
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
            //this.dataSource.Close();
        }

    }

    public void AddCourseKnowledgeMap(int CourseId, int knowledgeMapId, int userId, Message message) {

        try {
            Courseknowledgemap cnp = new Courseknowledgemap();
            cnp.setId(new CourseknowledgemapId(CourseId, knowledgeMapId));
            cnp.setAssignOn(new Date());
            cnp.setAssignBy(userId);
            cnp.setActionText("Selected");
            cnp.setCanEnableSelect(Boolean.FALSE);
           // this.dataSource.Open();
           // this.dataSource.BeginTransaction();
            this.dataSource.Save(cnp);
           // this.dataSource.Commit();
            Gson g = new Gson();
            message.ChangeContent("");
            message.ChangeStatus("ok");
        } catch (Throwable ex) {
           // this.dataSource.Rollback();
            message.ChangeContent("duplicate");
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }

    }

    public void DeleteCourseKnowledgeMap(int CourseId, int knowledgeMapId, int userId, Message message) {

        try {
            String sql = "delete from  courseknowledgemap where\n"
                    + " CourseId= " + CourseId + " and KnowledgeMapId= " + knowledgeMapId + " and AssignBy=" + userId;

           // this.dataSource.Open();
          //  this.dataSource.BeginTransaction();
            this.dataSource.ExecuteNonQuery(sql);
          //  this.dataSource.Commit();
            Gson g = new Gson();
            message.ChangeContent("");
            message.ChangeStatus("ok");
        } catch (Throwable ex) {
            //this.dataSource.Rollback();
            message.ChangeContent("");
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
          //  this.dataSource.Close();
        }

    }

    public void IsCourseKnowledgeMapSelected(int CourseId, int knowledgeMapId, int userId, Message message) {

        try {
            String sql = "Select Count(*) as count from  courseknowledgemap where\n"
                    + " CourseId= " + CourseId + " and KnowledgeMapId= " + knowledgeMapId + " and AssignBy=" + userId;

           // this.dataSource.Open();
            //  this.dataSource.BeginTransaction();
            List<BigInteger> list = new ArrayList();

            this.dataSource.ExecuteDataSet(sql, list);
            BigInteger count = list.get(0);

            //this.dataSource.Commit(); BigInteger  count= new BigInteger(list.get(0));
            Gson g = new Gson();
            if (count.intValue() == 0) {

                message.ChangeContent("no");
                message.ChangeStatus("ok");
            } else {
                message.ChangeContent("yes");
                message.ChangeStatus("ok");

            }

        } catch (Throwable ex) {
            // this.dataSource.Rollback();
            message.ChangeContent("yes");
            message.ChangeStatus("exception");
            message.UpdateError(ex.getMessage());
        } finally {
           // this.dataSource.Close();
        }

    }

    public void ListTeacherCourse(int userId, Message message) {
        try {
            String sql = "select ac.CourseTypeId as Id , ac.Number,ac.Name, ac.Description\n"
                    + " from academiccourse ac  inner join courseassignment a\n"
                    + " on ac.CourseTypeId=a.CourseId where a.TeacherId =" + userId;

            List<Courseassignment> courseAssignments = new ArrayList();

           // this.dataSource.Open();
            this.dataSource.ExecuteCustomDataSet(sql, courseAssignments, CourseAssignmentDescription.class);
            Gson g = new Gson();
            message.ChangeContent(g.toJson(courseAssignments));
            message.ChangeStatus("ok");
        } catch (Throwable ex) {
            message.ChangeContent("");
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
          //  this.dataSource.Close();
        }

    }

    public void ListTeacherUnAssignedCourses(int userId, Message message) {
        try {

            String sql = "select a.CourseTypeId as Id,a.Number,a.Name,a.Description from   \n"
                    + "              (\n"
                    + "                select * from academiccourse\n"
                    + "              ) as a where a.CourseTypeId not in (select CourseId  from courseassignment where TeacherId=" + userId + ")";

            List<Courseassignment> courseAssignments = new ArrayList();

           // this.dataSource.Open();
            this.dataSource.ExecuteCustomDataSet(sql, courseAssignments, CourseAssignmentDescription.class);
            Gson g = new Gson();
            message.ChangeContent(g.toJson(courseAssignments));
            message.ChangeStatus("ok");
        } catch (Throwable ex) {
            message.ChangeContent("");
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
            //this.dataSource.Close();
        }

    }

    public void ListCourseKnowledgeMap(int userId, int courseId, Message message) {
        try {
            String sql = "select a.CourseTypeId, km.KnowledgeMapId,km.Name,km.Description,cn.ActionText,cn.CanEnableSelect \n"
                    + " from academiccourse a inner join courseknowledgemap cn\n"
                    + " on a.CourseTypeId=cn.CourseId\n"
                    + " inner join  knowledgemap km on \n"
                    + " km.knowledgemapId=cn.KnowledgeMapId\n"
                    + " inner join user u on u.UserId=km.CreatedBy\n"
                    + " where u.UserId=" + userId + " and a.CourseTypeId=" + courseId;

            List<Courseknowledgemap> Courseknowledgemaps = new ArrayList();

          //  this.dataSource.Open();
            this.dataSource.ExecuteCustomDataSet(sql, Courseknowledgemaps, CourseKnowledgeMapDescription.class);
            Gson g = new Gson();
            message.ChangeContent(g.toJson(Courseknowledgemaps));
            message.ChangeStatus("ok");
        } catch (Throwable ex) {

            message.ChangeContent("");
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }

    }

    public void ListAllCourseKnowledgeMap(int userId, Message message) {
        try {
            String sql = " Select KnowledgeMapId,Name,Description from knowledgemap \n"
                    + " where CreatedBy=" + userId;

            List<Courseknowledgemap> Courseknowledgemaps = new ArrayList();

           // this.dataSource.Open();
            this.dataSource.ExecuteCustomDataSet(sql, Courseknowledgemaps, CourseKnowledgeMapDescription.class);
            Gson g = new Gson();
            message.ChangeContent(g.toJson(Courseknowledgemaps));
            message.ChangeStatus("ok");
        } catch (Throwable ex) {
            message.ChangeContent("");
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
         //   this.dataSource.Close();
        }

    }

     public void ListAvailableCourse(Message message) {
        try {
            String sql = "select CourseTypeId ,Number,Name from academiccourse";

            List<CourseItem> Courseitems = new ArrayList();

           // this.dataSource.Open();
            this.dataSource.ExecuteCustomDataSet(sql, Courseitems, CourseItem.class);
            Gson g = new Gson();
            message.ChangeContent(g.toJson(Courseitems));
            message.ChangeStatus("ok");
        } catch (Throwable ex) {
            message.ChangeContent("");
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
          //  this.dataSource.Close();
        }

    }
    
     public void ListStudentUnRegistertedCourses(int studentId,  Message message){
         
         try {
            String sql = "select c.CourseTypeId ,\n"
                    + "c.Number,\n"
                    + "c.Name,\n"
                    + "u.FirstName,\n"
                    + "u.LastName, Concat('Dr ',u.FirstName, ' ' ,u.LastName) as Professor from academiccourse c\n"
                    + "left join courseassignment a on c.CourseTypeId=a.CourseId\n"
                    + "inner join user u on u.UserId =a.TeacherId where u.UserTypeId=3;";

            //List all the courses that are assigned to teachers
            List<CourseItem> Courseitems = new ArrayList();

           // this.dataSource.Open();
            this.dataSource.ExecuteCustomDataSet(sql, Courseitems, CourseItem.class);
            
            //List student Registered courses
             sql = "select * from studentcourseregistration where StudentId=" + studentId ;
            List<StudentCourseItem> StudentCourseitems = new ArrayList();
            this.dataSource.ExecuteCustomDataSet(sql, StudentCourseitems, StudentCourseItem.class);
            
            Gson g = new Gson();
            List<CourseItem> filterList= new ArrayList();
            if(StudentCourseitems.size()>0){
                //Start filtering
                for(CourseItem t: Courseitems){
                    if(!HasStudentRegisteredForCourse(t.CourseTypeId,StudentCourseitems)){
                        filterList.add(t);
                    }
                }
                 message.ChangeContent(g.toJson(filterList)); 
            }
            else{
              
               message.ChangeContent(g.toJson(Courseitems)); 
            }
       
            message.ChangeStatus("ok");
        } catch (Throwable ex) {
            message.ChangeContent("");
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }

     }
     
     Boolean  HasStudentRegisteredForCourse(int courseid,  List<StudentCourseItem> studentCourseList){
          Boolean found=false;
         for(StudentCourseItem a:studentCourseList){
             if(a.CourseId==courseid){
                 found=true;
                 break;
             }
         }
         return found;
     }
    
    public void ListAllCourse(Message message) {
        try {
            String sql = "select c.CourseTypeId ,\n"
                    + "c.Number,\n"
                    + "c.Name,\n"
                    + "u.FirstName,\n"
                    + "u.LastName, Concat('Dr ',u.FirstName, ' ' ,u.LastName) as Professor from academiccourse c\n"
                    + "left join courseassignment a on c.CourseTypeId=a.CourseId\n"
                    + "inner join user u on u.UserId =a.TeacherId where u.UserTypeId=3;";

            //List all the courses that are assigned to teachers
            List<CourseItem> Courseitems = new ArrayList();

           // this.dataSource.Open();
            this.dataSource.ExecuteCustomDataSet(sql, Courseitems, CourseItem.class);
            
            
            Gson g = new Gson();
            message.ChangeContent(g.toJson(Courseitems));
            message.ChangeStatus("ok");
        } catch (Throwable ex) {
            message.ChangeContent("");
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }

    }

    public void RegisterCourse(int studentId, CourseItem[] courseItems, Message message) {

        try {

            //this.dataSource.Open();
           // this.dataSource.BeginTransaction();
            for (CourseItem a : courseItems) {
                   OTS.DataModels.User user=(OTS.DataModels.User)this.dataSource.Find(OTS.DataModels.User.class, new Integer(studentId));
                    Studentcourseregistration sc = new Studentcourseregistration();
                    sc.setDate(new Date());
                    Academiccourse course = (Academiccourse) this.dataSource.Find(Academiccourse.class, new Integer(a.CourseTypeId));
                    sc.setAcademiccourse(course);
                    sc.setUser(user);
                    this.dataSource.Save(sc);
               // }
            }
           // this.dataSource.Commit();
            Gson g = new Gson();
            message.ChangeContent("");
            message.ChangeStatus("ok");
        } catch (Throwable ex) {
          //  this.dataSource.Rollback();
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
            //this.dataSource.Close();
        }

    }

    public Boolean HasRegisterForCourse(int studentId, int courseId) {
        try {
            String sql = "select * from studentcourseregistration where StudentId=" + studentId + " and CourseId=" + courseId;

            List<CourseItem> Courseitems = new ArrayList();

            //this.dataSource.Open();
            this.dataSource.ExecuteCustomDataSet(sql, Courseitems, CourseItem.class);
            if (Courseitems.size() > 0) {
                return true;
            }
            return false;
        } catch (Throwable ex) {
            return false;
        } finally {
           // this.dataSource.Close();
        }

    }
    
    public void ListSudentActivatedTest(int StudentId, Message message){
        
          try {
           
             String sql = "select ac.CourseTypeId,\n"
                    + "       ac.Number,\n"
                    + "		 ac.Name as CourseName,\n"
                    + "		 t.TestId,\n"
                    + "		 t.Name as TestName,\n"
                    + "		 t.StartDate,t.StartTime,t.EndTime,t.IsActivated\n"
                    + " from studentcourseregistration sr \n"
                    + "inner join academiccourse ac on sr.CourseId=ac.CourseTypeId \n"
                    + "inner join teachercoursetest ct on ct.CourseId=ac.CourseTypeId\n"
                    + "inner join test t on t.TestId=ct.TestId where sr.StudentId=" + StudentId  + " and t.IsActivated=1";
         
            
            List<StudentRegistedCourseItem> StudentCourseTests = new ArrayList();

            //this.dataSource.Open();
            this.dataSource.ExecuteCustomDataSet(sql, StudentCourseTests, StudentRegistedCourseItem.class);
             
            for(StudentRegistedCourseItem a:StudentCourseTests){
                 if(IsTestTeken(StudentId,a.TestId)){
                      a.IsTestTeken=true;
                 }
                 else{
                     a.IsTestTeken=false;
                 }
             }
            
             for(StudentRegistedCourseItem a:StudentCourseTests){
               TestHistoryItem item=  RetreiveStudentTestScored(StudentId,a.TestId);
               if(item.TotalMark>0){
                   a.TestScore=String.valueOf(item.TotalMark);
               }
             }
            
            Gson g = new Gson();
            message.ChangeContent(g.toJson(StudentCourseTests));
            message.ChangeStatus("ok");
        } catch (Throwable ex) {
            message.ChangeContent("");
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }
         
    }
    
    public Boolean IsTestTeken( int studentId,int testId){
        Boolean testTaken=false;
          try {
           
            String sql="Select Count(*) as counter from studenttestanswersheet  where TestId= " + testId +  " and StudentId="  + studentId ;
            
            //this.dataSource.Open();
           
           List list= this.dataSource.Execute(sql);
           BigInteger big=(BigInteger) list.get(0);
             if(big.intValue()>0){
                 testTaken=true;
             }
          
        } catch (Throwable ex) {
           
        } finally {
           // this.dataSource.Close();
        }
        
        return testTaken;
    }
    
    
    public TestHistoryItem RetreiveStudentTestScored(int studentId,int testId){
           TestHistoryItem testHistory=new TestHistoryItem();
          try {
           
            String sql="Select * from studenttesthistory  where TestId= " + testId +  " and StudentId="  + studentId ;
            
           // this.dataSource.Open();
            List<TestHistoryItem> items=new ArrayList();
            this.dataSource.ExecuteCustomDataSet(sql, items, TestHistoryItem.class);
            if(items.size()>0){
              testHistory=   items.get(0);
              
            }
          
        } catch (Throwable ex) {
           
        } finally {
           // this.dataSource.Close();
        }
        
        return testHistory;
    }

    public void RegisteredCourseTest(int StudentId, Message message) {
        try {
           
            String sql="select sr.StudentCourseId, ac.CourseTypeId,\n" +
                        "       ac.Number,\n" +
                        "		 ac.Name as CourseName,\n" +
                        "		  CONCAT('Dr ', u.FirstName, ' ', u.LastName)   as Professor  	\n" +
                        " from studentcourseregistration sr \n" +
                        "inner join academiccourse ac on sr.CourseId=ac.CourseTypeId \n" +
                        "inner join courseassignment ca on ca.CourseId=ac.CourseTypeId\n" +
                        "inner join user u on u.UserId =ca.TeacherId\n" +
                        " where sr.StudentId=" + StudentId;
            
            List<StudentRegistedCourseItem> StudentCourseTests = new ArrayList();

            //this.dataSource.Open();
            this.dataSource.ExecuteCustomDataSet(sql, StudentCourseTests, StudentRegistedCourseItem.class);
            Gson g = new Gson();
            message.ChangeContent(g.toJson(StudentCourseTests));
            message.ChangeStatus("ok");
        } catch (Throwable ex) {
            message.ChangeContent("");
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }

    }

    
    public Boolean CanUnRegister(int studentCourseId){
         Boolean canRegister=true;
        
        /*
       
        int courseId;
        int studentId;
         Studentcourseregistration item=  (Studentcourseregistration)this.dataSource.Find(Studentcourseregistration.class, new Integer(studentCourseId));
         courseId= item.getAcademiccourse().getCourseTypeId();
          studentId=item.getUser().getUserId();
        
        String sql="select ac.CourseTypeId,\n" +
"                     ac.Number,\n" +
"                    ac.Name as CourseName,\n" +
"                    t.TestId,\n" +
"                    t.Name as TestName,\n" +
"                    t.StartDate,t.StartTime,t.EndTime,t.IsActivated\n" +
"                     from studentcourseregistration sr \n" +
"                     inner join academiccourse ac on sr.CourseId=ac.CourseTypeId\n" +
"                    inner join teachercoursetest ct on ct.CourseId=ac.CourseTypeId\n" +
"                    inner join test t on t.TestId=ct.TestId where sr.StudentId=" + studentId + " and t.IsActivated=1;";
        
                List<StudentRegistedCourseItem> StudentCourseTests = new ArrayList();
               this.dataSource.ExecuteCustomDataSet(sql, StudentCourseTests, StudentRegistedCourseItem.class);
                */
        return canRegister;
    }
    
     public  void UnRegisterCourse(int studentCourseId,Message message){
         
         try {
         
          Studentcourseregistration item=  (Studentcourseregistration)this.dataSource.Find(Studentcourseregistration.class, new Integer(studentCourseId));
           if(item!=null){
              this.dataSource.Delete(item);
           }
            Gson g = new Gson();
            message.ChangeStatus("ok");
        } catch (Throwable ex) {            //this.dataSource.Rollback();
            message.ChangeContent("");
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
            //this.dataSource.Close();
        }
        
     }
}

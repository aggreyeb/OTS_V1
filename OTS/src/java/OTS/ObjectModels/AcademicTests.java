/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels;

import OTS.DataModels.Academiccourse;
import OTS.DataModels.CourseAssignmentDescription;
import OTS.DataModels.Courseassignment;
import OTS.DataModels.DataSource;
import OTS.DataModels.Questionlineitem;
import OTS.DataModels.Teachercoursetest;
import OTS.DataModels.Test;
import OTS.Message;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author MEA
 */
public class AcademicTests {
    
    
     DataSource dataSource;
    public AcademicTests(DataSource dataSource){
        this.dataSource=dataSource;
    }
     public void ListTeacherCourse(int userId ,Message message){
     try{
        String sql= "select ac.CourseTypeId as Id , ac.Number,ac.Name, ac.Description\n" +
                    " from academiccourse ac  inner join courseassignment a\n" +
                    " on ac.CourseTypeId=a.CourseId where a.TeacherId =" + userId;
       
        List<Courseassignment> courseAssignments= new ArrayList();
     
        this.dataSource.Open();
        this.dataSource.ExecuteCustomDataSet(sql, courseAssignments,CourseAssignmentDescription.class);
        Gson g = new Gson();
        message.ChangeContent(g.toJson(courseAssignments));
        message.ChangeStatus("ok");
      }
      catch(Throwable ex){
        message.ChangeContent("");
        message.ChangeStatus("exception");
        message.UpdateError(ex.toString());
      }
      finally{
        this.dataSource.Close();
      }
    
    }
     
   public void ListCourseTest(int courseId,int teacherId,Message callBackMessage ){
       
       try{
        String sql= "Select t.TestId,\n" +
                        "       t.Name,\n" +
                        "		 t.TotalMark,\n" +
                        "		 t.NumberOfQuestion,\n" +
                        "		 t.StartDate,\n" +
                        "		 t.StartTime,\n" +
                        "		 t.EndTime,\n" +
                        "		 t.IsActivated\n" +
                        "		 from test t \n" +
                        "inner join teachercoursetest ct\n" +
                        "where t.TestId=ct.TestId  and ct.CourseId =" + courseId + " and ct.TeacherId=" +  teacherId ;

        List<AcademicTestDescription> tests= new ArrayList();
         // List<Test> tests= new ArrayList();
        
        this.dataSource.Open();
        this.dataSource.ExecuteCustomDataSet(sql, tests,AcademicTestDescription.class);
        
        for(AcademicTestDescription t:tests){
          Boolean result=  this.IsAllStudentTestMarked(t.TestId);
          if(result){
              t.IsAllMarked=true;
          }
        }
        
        Gson g = new Gson();
        callBackMessage.ChangeContent(g.toJson(tests));
        callBackMessage.ChangeStatus("ok");
      }
      catch(Throwable ex){
        callBackMessage.ChangeContent("");
        callBackMessage.ChangeStatus("exception");
        callBackMessage.UpdateError(ex.toString());
      }
      finally{
        this.dataSource.Close();
      }
    
   }
     
   public void CreateNew(int courseId, int teacherId,AcademicTestDescription desc,Message callBackMessage ){
        try{
         Test test= new Test();
        
         test.setIsActivated(Boolean.FALSE);
         test.setName(desc.Name);
         test.setNumberOfQuestion(desc.NumberOfQuestion);
         test.setStartDate(desc.StartDate);
         test.setStartTime(desc.StartTime);
         test.setEndTime(desc.EndTime);
         test.setTotalMark(desc.TotalMark);
         
        this.dataSource.Open();
        this.dataSource.BeginTransaction();
         Academiccourse academicCourse= (Academiccourse)this.dataSource.Find(Academiccourse.class,
                 new Integer(courseId));
          OTS.DataModels.User user= (OTS.DataModels.User)this.dataSource.Find(OTS.DataModels.User.class,new Integer(teacherId));
          
         Teachercoursetest courseTest= new Teachercoursetest();
         courseTest.setTest(test);
         courseTest.setCreatedOn(new Date());
         courseTest.setAcademiccourse(academicCourse);
         courseTest.setUser(user);
     
         
      
        this.dataSource.Save(test);
        this.dataSource.Save(courseTest);
        this.dataSource.Commit();
        Gson g = new Gson();
        callBackMessage.UpdateID(test.getTestId());
        callBackMessage.ChangeContent("");
        callBackMessage.ChangeStatus("ok");
      }
      catch(Throwable ex){
        this.dataSource.Rollback();
        callBackMessage.ChangeContent("");
        callBackMessage.ChangeStatus("exception");
        callBackMessage.UpdateError(ex.toString());
      }
      finally{
        this.dataSource.Close();
      }
    
   }
  
   public void Update(AcademicTestDescription desc,Message callBackMessage){
      
        try{
               this.dataSource.Open();
               this.dataSource.BeginTransaction();
              Test test= (Test)this.dataSource.Find(Test.class,new Integer(desc.TestId));
              if(test !=null){
               
                 test.setIsActivated(desc.IsActivated);
                 test.setName(desc.Name);
                 test.setNumberOfQuestion(desc.NumberOfQuestion);
                 test.setStartDate(desc.StartDate);
                 test.setStartTime(desc.StartTime);
                 test.setEndTime(desc.EndTime);
                 test.setTotalMark(desc.TotalMark);
                 this.dataSource.Update(test);
                 this.dataSource.Commit();
                 callBackMessage.UpdateID(test.getTestId());
                 callBackMessage.ChangeContent("");
                 callBackMessage.ChangeStatus("ok");
                 callBackMessage.UpdateError("");
              }
              else{
                 callBackMessage.ChangeContent("");
                 callBackMessage.ChangeStatus("fail");
                 callBackMessage.UpdateError("");   
              }
                 
           }
           catch(Throwable ex){
              this.dataSource.Rollback();
              callBackMessage.ChangeContent("");
              callBackMessage.ChangeStatus("exception");
              callBackMessage.UpdateError(ex.toString());
           }
           finally{
               this.dataSource.Close();
           }
           
   }
   
  
   
   public void Delete(int testId,Message callBackMessage){
      
        try{
            String sql="Delete from teachercoursetest where TestId=" + testId;
               this.dataSource.Open();
               this.dataSource.BeginTransaction();
              Test test= (Test)this.dataSource.Find(Test.class,new Integer(testId));
              if(test !=null){
                
                 this.dataSource.ExecuteNonQuery(sql);
                 this.dataSource.Delete(test);
                 
                 this.dataSource.Commit();
                 callBackMessage.ChangeContent("");
                 callBackMessage.ChangeStatus("ok");
                 callBackMessage.UpdateError("");
              }
              else{
                  callBackMessage.ChangeContent("");
                  callBackMessage.ChangeStatus("fail");
                  callBackMessage.UpdateError("");  
              }
                
           }
           catch(Throwable ex){
              this.dataSource.Rollback();
              callBackMessage.ChangeContent("");
              callBackMessage.ChangeStatus("exception");
              callBackMessage.UpdateError(ex.toString());
           }
           finally{
               this.dataSource.Close();
           }
           
   }
 
   
     public void ActivateTest( int testId,  Message callBackMessage ){
           try{
            
               this.dataSource.Open();
               this.dataSource.BeginTransaction();
              Test test= (Test)this.dataSource.Find(Test.class,new Integer(testId));
              if(test !=null){
                 test.setIsActivated(Boolean.TRUE);
                 this.dataSource.Update(test);
                 
                 this.dataSource.Commit();
                 callBackMessage.ChangeContent("");
                 callBackMessage.ChangeStatus("ok");
                 callBackMessage.UpdateError("");
              }
              else{
                  callBackMessage.ChangeContent("");
                  callBackMessage.ChangeStatus("fail");
                  callBackMessage.UpdateError("");  
              }
                
           }
           catch(Throwable ex){
              this.dataSource.Rollback();
              callBackMessage.ChangeContent("");
              callBackMessage.ChangeStatus("exception");
              callBackMessage.UpdateError(ex.toString());
           }
           finally{
               this.dataSource.Close();
           }
     }
     
     public void DeActivateTest( int testId,  Message callBackMessage ){
          try{
            
               this.dataSource.Open();
               this.dataSource.BeginTransaction();
              Test test= (Test)this.dataSource.Find(Test.class,new Integer(testId));
              if(test !=null){
                 test.setIsActivated(Boolean.FALSE);
                 this.dataSource.Update(test);
                 
                 this.dataSource.Commit();
                 callBackMessage.ChangeContent("");
                 callBackMessage.ChangeStatus("ok");
                 callBackMessage.UpdateError("");
              }
              else{
                  callBackMessage.ChangeContent("");
                  callBackMessage.ChangeStatus("fail");
                  callBackMessage.UpdateError("");  
              }
                
           }
           catch(Throwable ex){
              this.dataSource.Rollback();
              callBackMessage.ChangeContent("");
              callBackMessage.ChangeStatus("exception");
              callBackMessage.UpdateError(ex.toString());
           }
           finally{
               this.dataSource.Close();
           }
     }
   
   
     public void MarkTest(int testId,  Message callBackMessage){
         
     }
     
     
    public void ListTestQuestions(int userId , int courseId, int testId,  Message message){
     try{
       
        String sqlTeacherCourseTest="Select TestId from  teachercoursetest  where TeacherId=" + userId +  " and CourseId=" + courseId  + " and TestId=" + testId;
        
        this.dataSource.Open();
        List items=  this.dataSource.Execute(sqlTeacherCourseTest);
        
       List<Integer> ids=(List<Integer>)items;
        
        String sql= "Select q.QuestionId,\n" +
                        "  q.Test_id as TestId,\n" +
                        "  q.Text,\n" +
                        "  ct.Name as CognitiveType,\n" +
                        "  qt.Name as QuestionType,\n" +
                        "  qnt.Name as QuestionNatureType \n" +
                        " from   question q \n" +
                        "inner join questiontype qt on q.QuestionTypeId=qt.QuestionType \n" +
                        "inner join questionnaturetype qnt on q.QuestionNatureType_id=qnt.QuestionNatureType\n" +
                        "inner join cognitiveleveltype ct on q.CognitiveLevelType_id=ct.CognitiveLevel\n" +
                        "where q.Test_id=" + ids.get(0);
        
        List<TestQuestionItem> testQuestionItems= new ArrayList();
        this.dataSource.ExecuteCustomDataSet(sql, testQuestionItems,TestQuestionItem.class);
        Gson g = new Gson();
        message.ChangeContent(g.toJson(testQuestionItems));
        message.ChangeStatus("ok");
        
      }
      catch(Throwable ex){
        message.ChangeContent("");
        message.ChangeStatus("exception");
        message.UpdateError(ex.toString());
      }
      finally{
        this.dataSource.Close();
      }
    
    }
   
    
    public void ListTestQuestionLineItems(int questionId,  Message message){
     try{
       
        String sql="select * from questionlineitem a where  a.Question_id=" + questionId;
        this.dataSource.Open();
        List<TestQuestionLineItem> testQuestionLineItems= new ArrayList();
        this.dataSource.ExecuteCustomDataSet(sql, testQuestionLineItems,TestQuestionLineItem.class);
        Gson g = new Gson();
        message.ChangeContent(g.toJson(testQuestionLineItems));
        message.ChangeStatus("ok");
        
      }
      catch(Throwable ex){
        message.ChangeContent("");
        message.ChangeStatus("exception");
        message.UpdateError(ex.toString());
      }
      finally{
        this.dataSource.Close();
      }
    
    } 
    
    
     public void UpdateQuestionLineItem(TestQuestionLineItem[] items,  Message message){
     try{
         
        this.dataSource.Open();
        this.dataSource.BeginTransaction();
         for(int i=0;i<items.length;i++){
              Questionlineitem item= (Questionlineitem) this.dataSource.Find(Questionlineitem.class, new Integer(items[i].QuestionLineItemId));
             item.setIsCorrect(items[i].IsCorrect);
             this.dataSource.Save(item);
         }
        this.dataSource.Commit();;
        message.ChangeStatus("ok");
        
      }
      catch(Throwable ex){
        this.dataSource.Rollback();
        message.ChangeContent("");
        message.ChangeStatus("exception");
        message.UpdateError(ex.toString());
      }
      finally{
        this.dataSource.Close();
      }
    
    } 
    
   
    public void UpdateQuestionLineItem(int QustionLineItemId, Boolean IsCorrect,  Message message){
     try{
         
        this.dataSource.Open();
        this.dataSource.BeginTransaction();
         Questionlineitem item= (Questionlineitem) this.dataSource.Find(Questionlineitem.class, new Integer(QustionLineItemId));
        item.setIsCorrect(IsCorrect);
        this.dataSource.Save(item);
        this.dataSource.Commit();;
        Gson g = new Gson();
        TestQuestionLineItem lineItem= new TestQuestionLineItem();
        lineItem.QuestionLineItemId=item.getQuestionLineItemId();
        lineItem.IsCorrect=item.getIsCorrect();
        lineItem.Question_id=item.getQuestion().getQuestionId();
        lineItem.Text=item.getText();
        message.ChangeContent(g.toJson(lineItem));
        message.ChangeStatus("ok");
        
      }
      catch(Throwable ex){
        this.dataSource.Rollback();
        message.ChangeContent("");
        message.ChangeStatus("exception");
        message.UpdateError(ex.toString());
      }
      finally{
        this.dataSource.Close();
      }
    
    } 
    
    public Boolean IsAllStudentTestMarked(int testId){
        Boolean isMarked=false;
        
        try{
         
        this.dataSource.Open();
        this.dataSource.BeginTransaction();
         //Get List of student registered for a a particular test =a
         String sqlStudentRegisteredForTestCount="select count(*) as count\n" +
                    " from studentcourseregistration sr \n" +
                    "inner join academiccourse ac on sr.CourseId=ac.CourseTypeId \n" +
                    "inner join teachercoursetest ct on ct.CourseId=ac.CourseTypeId\n" +
                    "inner join test t on t.TestId=ct.TestId where  t.IsActivated=1 and t.TestId=" + testId;
        
          List<Integer> registerStudentCount= new ArrayList();
          this.dataSource.ExecuteDataSet(sqlStudentRegisteredForTestCount, registerStudentCount);
        //Get All the students whose test has been marked from Student Test History =b
        
           String sqlTestMarkedCount= "Select Count(*) as count from studenttesthistory where  TotalMark >0  and TestId= " + testId;
           List<Integer> MarkedTestCount= new ArrayList();
           this.dataSource.ExecuteDataSet(sqlTestMarkedCount, MarkedTestCount);
           this.dataSource.Commit();
        //If a.Size==b.Size  then all marked
        //else pending items to mark
           if(registerStudentCount.size()> 0 & MarkedTestCount.size()>0 ){
                if(Objects.equals(registerStudentCount.get(0), MarkedTestCount.get(0))){
              isMarked=true;
              }
           }
         
        
      }
      catch(Throwable ex){
        this.dataSource.Rollback();
       
      }
      finally{
        this.dataSource.Close();
      }
        return isMarked;
    }
  
    
}

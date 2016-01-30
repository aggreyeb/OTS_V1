/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels.QuestionManagement;

import OTS.DataModels.Cognitiveleveltype;
import OTS.DataModels.DataSource;
import OTS.Message;
import OTS.ObjectModels.LookupItem;
import OTS.ObjectModels.Response;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eb
 */
public class StudentTestReport {
    
     DataSource dataSource;
     Message message;

    public StudentTestReport(DataSource dataSource, Response message) {
        this.dataSource = dataSource;
        this.message = message;
    }

    
    public void ListTeacherTest(int teacherId){
         try{
        String sql= "select t.TestId as Id,t.Name from teachercoursetest ct inner "
                + "join test t on ct.TestId=t.TestId where ct.TeacherId=" + teacherId ;
       
        List<LookupItem> items= new ArrayList();
     
        this.dataSource.Open();
        this.dataSource.ExecuteCustomDataSet(sql, items,LookupItem.class);
        Gson g = new Gson();
        message.ChangeContent(g.toJson(items));
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
    
    public void ListStudentTest(int testId,Response messge){
        try{
        String sql= "select u.UserId,\n" +
"      CONCAT( u.FirstName,' ',u.LastName) as Name ,\n" +
"		th.StartDate,th.EndDate, Convert(((TIME_TO_SEC(th.EndDate) - TIME_TO_SEC(th.StartDate))/60),CHAR(50)) as TimeSpent,\n" +
"		th.TotalMark from studenttesthistory th inner join user u \n" +
"		on th.StudentId=u.UserId where th.TestId=" + testId ;
       
        List<StudentTestReportItem> items= new ArrayList();
     
        this.dataSource.Open();
        this.dataSource.ExecuteCustomDataSet(sql, items,StudentTestReportItem.class);
        Gson g = new Gson();
        message.ChangeContent(g.toJson(items));
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
}

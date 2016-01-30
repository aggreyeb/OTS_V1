/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.UnitTesting;

import OTS.DataModels.MySqlDataSource;
import OTS.Message;
import OTS.ObjectModels.AcademicTests;
import OTS.ObjectModels.Response;
import OTS.ObjectModels.TestQuestionLineItem;
import com.google.gson.Gson;
import org.junit.Test;

/**
 *
 * @author aggreeb
 */
public class ReviewTestItemTest {
    
    @Test
    public void ListTestQuestions(){
        MySqlDataSource db=  new MySqlDataSource();
        Message message= new Response("","");
        AcademicTests at= new AcademicTests(db);
        int userid=1;
        int courseId=1;
        int testId=1;
        at.ListTestQuestions(userid, courseId,testId, message);
        
         System.out.print(message.ToJson());
        
    }
    
   @Test
    public void ListTestQuestionLineItems(){
        MySqlDataSource db=  new MySqlDataSource();
        Message message= new Response("","");
        AcademicTests at= new AcademicTests(db);
        int testQuestion=5;
        at.ListTestQuestionLineItems(testQuestion, message);
        System.out.print(message.ToJson());
        
    }
    
     @Test
    public void UpdateQuestionLineItem(){
        MySqlDataSource db=  new MySqlDataSource();
        Message message= new Response("","");
        AcademicTests at= new AcademicTests(db);
        int questionLineItemId=9;
        Boolean isActive=false;
        at.UpdateQuestionLineItem(questionLineItemId,isActive,  message);
        System.out.print(message.ToJson());
        
    }
    
    
      @Test
    public void UpdateQuestionLineItemToTrueInBatch(){
       String json="[{\"QuestionLineItemId\":2071,\"Text\":\"Plant has Root\",\"Question_id\":635,\"IsCorrect\":true},\n" +
            " {\"QuestionLineItemId\":2072,\"Text\":\"Plant has Steam\",\"Question_id\":635,\"IsCorrect\":true},\n" +
            " {\"QuestionLineItemId\":2073,\"Text\":\"Plant has Leaf\",\"Question_id\":635,\"IsCorrect\":true}\n" +
            "]";
        Gson g= new Gson();
         TestQuestionLineItem[] items=  g.fromJson(json, TestQuestionLineItem[].class);
        
        MySqlDataSource db=  new MySqlDataSource();
        Message message= new Response("","");
        AcademicTests at= new AcademicTests(db);
      
        at.UpdateQuestionLineItem(items , message);
        System.out.print(message.ToJson());
        
    }
    
      @Test
    public void UpdateQuestionLineItemToFalseInBatch(){
       String json="[{\"QuestionLineItemId\":2071,\"Text\":\"Plant has Root\",\"Question_id\":635,\"IsCorrect\":false},\n" +
            " {\"QuestionLineItemId\":2072,\"Text\":\"Plant has Steam\",\"Question_id\":635,\"IsCorrect\":false},\n" +
            " {\"QuestionLineItemId\":2073,\"Text\":\"Plant has Leaf\",\"Question_id\":635,\"IsCorrect\":false}\n" +
            "]";
        Gson g= new Gson();
         TestQuestionLineItem[] items=  g.fromJson(json, TestQuestionLineItem[].class);
        
        MySqlDataSource db=  new MySqlDataSource();
        Message message= new Response("","");
        AcademicTests at= new AcademicTests(db);
      
        at.UpdateQuestionLineItem(items , message);
        System.out.print(message.ToJson());
        
    }
}

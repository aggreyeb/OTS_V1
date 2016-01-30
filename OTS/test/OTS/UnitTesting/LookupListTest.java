/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.UnitTesting;

import OTS.DataModels.MySqlDataSource;
import OTS.Message;
import OTS.ObjectModels.Courses;
import OTS.ObjectModels.LookupLists;
import OTS.ObjectModels.Response;
import org.junit.Test;

/**
 *
 * @author MEA
 */
public class LookupListTest {
    
    @Test
    public void ListCognitiveTypes(){
        Message message= new Response("","");
        LookupLists db= new LookupLists( new MySqlDataSource());
        
        db.ListCognitiveTypes(message);
        
         //Assert
       System.out.print(message.ToJson());
        
    }
    
    
    
     @Test
    public void ListQuestiontypeTypes(){
        Message message= new Response("","");
        LookupLists db= new LookupLists( new MySqlDataSource());
        
        db.ListQuestionTypes(message);
        
         //Assert
       System.out.print(message.ToJson());
        
    }
    
    
     @Test
    public void ListQuestionnatureTypes(){
        Message message= new Response("","");
        LookupLists db= new LookupLists( new MySqlDataSource());
        
        db.ListQuestionnatureTypes(message);
        
         //Assert
       System.out.print(message.ToJson());
        
    }
    
    
    
      @Test
    public void ListCourseTestKnowledgeMaps(){
        Message message= new Response("","");
        int userId=1;
        int courseId=1;
        LookupLists db= new LookupLists( new MySqlDataSource());
        
        db.ListCourseTestKnowledgeMaps(userId,courseId,message);
        
         //Assert
       System.out.print(message.ToJson());
        
    }
}

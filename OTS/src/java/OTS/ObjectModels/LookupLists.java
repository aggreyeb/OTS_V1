/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels;

import OTS.DataModels.Cognitiveleveltype;
import OTS.DataModels.DataSource;
import OTS.DataModels.Node;
import OTS.Message;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MEA
 */
public class LookupLists {
    
     DataSource dataSource;
     
     public LookupLists(DataSource dataSource){
        this.dataSource=dataSource;
    }
     
     
    public void ListCognitiveTypes(Message message){
     try{
        String sql= "select CognitiveLevel as Id,Name from cognitiveleveltype where cognitiveLevel";
       
        List<Cognitiveleveltype> cognitiveTypes= new ArrayList();
     
        //this.dataSource.Open();
        this.dataSource.ExecuteCustomDataSet(sql, cognitiveTypes,LookupItem.class);
        Gson g = new Gson();
        message.ChangeContent(g.toJson(cognitiveTypes));
        message.ChangeStatus("ok");
      }
      catch(Throwable ex){
        message.ChangeContent("");
        message.ChangeStatus("exception");
        message.UpdateError(ex.toString());
      }
      finally{
       // this.dataSource.Close();
      }
    
    }
    
    
    public void ListQuestionTypes(Message message){
     try{
        String sql= "select QuestionType as Id ,Name from questiontype where QuestionType";
       
        List<Cognitiveleveltype> cognitiveTypes= new ArrayList();
     
       // this.dataSource.Open();
        this.dataSource.ExecuteCustomDataSet(sql, cognitiveTypes,LookupItem.class);
        Gson g = new Gson();
        message.ChangeContent(g.toJson(cognitiveTypes));
        message.ChangeStatus("ok");
      }
      catch(Throwable ex){
        message.ChangeContent("");
        message.ChangeStatus("exception");
        message.UpdateError(ex.toString());
      }
      finally{
        //this.dataSource.Close();
      }
    
    }
    
    
   
     public void ListQuestionnatureTypes(Message message){
     try{
        String sql= "select QuestionNatureType as Id,Name from questionnaturetype";
       
        List<Cognitiveleveltype> cognitiveTypes= new ArrayList();
     
       // this.dataSource.Open();
        this.dataSource.ExecuteCustomDataSet(sql, cognitiveTypes,LookupItem.class);
        Gson g = new Gson();
        message.ChangeContent(g.toJson(cognitiveTypes));
        message.ChangeStatus("ok");
      }
      catch(Throwable ex){
        message.ChangeContent("");
        message.ChangeStatus("exception");
        message.UpdateError(ex.toString());
      }
      finally{
        //this.dataSource.Close();
      }
    
    }
     
     
     public void ListCourseTestKnowledgeMaps(int userId,int courseId,Message message) {
      try{
        String sql= "select c.CourseId,\n" +
                        "c.KnowledgeMapId,k.Name,k.Description,k.Concepts from courseknowledgemap c\n" +
                        "inner join knowledgemap k\n" +
                        "on c.KnowledgeMapId=k.KnowledgeMapId\n" +
                        "where c.CourseId=" + courseId + " and c.AssignBy=" + userId;
        
        List<String> items= new ArrayList();
        List<CourseTestKnowledgeMapDescription> knowledgemaps= new ArrayList();
       // this.dataSource.BeginTransaction();
       // this.dataSource.Open();
        this.dataSource.ExecuteCustomDataSet(sql, knowledgemaps,CourseTestKnowledgeMapDescription.class);
      //  this.dataSource.Commit();
        if(knowledgemaps.size()>0){
            for(CourseTestKnowledgeMapDescription k: knowledgemaps){
                Node node=new Node(k);
                items.add(node.ToJsonDiscription());
           }
        }
        Gson g = new Gson();
        message.ChangeContent(g.toJson(items));
        message.ChangeStatus("ok");
      }
      catch(Throwable ex){
       // this.dataSource.Rollback();
        message.ChangeContent("");
        message.ChangeStatus("exception");
        message.UpdateError(ex.toString());
      }
      finally{
        //this.dataSource.Close();
      }
       
    } 
     
     
     
}

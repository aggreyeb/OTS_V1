/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.DataModels.DataSource;
import OTS.DataModels.KnowledgeMapDescription;
import OTS.DataModels.Node;
import OTS.KnowledgeMapId;
import OTS.Message;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MEA
 */
public class KnowledgeMaps  {

    DataSource dataSource;
    public KnowledgeMaps(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
   public void ImportKnowledgeMapsList(int userId,Message message) {
      try{
        String sql= "Select * from knowledgemap where Createdby <>" + userId + " and IsPublic =1";
        List<String> items= new ArrayList();
        List<KnowledgeMapDescription> knowledgemaps= new ArrayList();
       // this.dataSource.BeginTransaction();
        this.dataSource.Open();
        this.dataSource.ExecuteCustomDataSet(sql, knowledgemaps,KnowledgeMapDescription.class);
      //  this.dataSource.Commit();
        if(knowledgemaps.size()>0){
            for(KnowledgeMapDescription k: knowledgemaps){
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
        this.dataSource.Close();
      }
       
    }
    
    
    public void List(int userId,Message message) {
      try{
        String sql= "Select * from knowledgemap where Createdby =" + userId;
        List<String> items= new ArrayList();
        List<KnowledgeMapDescription> knowledgemaps= new ArrayList();
       // this.dataSource.BeginTransaction();
        this.dataSource.Open();
        this.dataSource.ExecuteCustomDataSet(sql, knowledgemaps,KnowledgeMapDescription.class);
      //  this.dataSource.Commit();
        if(knowledgemaps.size()>0){
            for(KnowledgeMapDescription k: knowledgemaps){
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
        this.dataSource.Close();
      }
       
    }

    public void List(int userId,int CourseId,Message message){
          //not implemented yet
    }
    
    public void ListAll(Message message){
        try{
        String sql= "Select * from knowledgemap";
        List<String> items= new ArrayList();
        List<KnowledgeMapDescription> knowledgemaps= new ArrayList();
      //  this.dataSource.BeginTransaction();
        this.dataSource.Open();
        this.dataSource.ExecuteCustomDataSet(sql, knowledgemaps,KnowledgeMapDescription.class);
      //  this.dataSource.Commit();
        if(knowledgemaps.size()>0){
            for(KnowledgeMapDescription k: knowledgemaps){
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
        this.dataSource.Close();
        
        }
       
    }

    public void ListAllIgnoreConceptSchema(Message message){
    
     try{
        String sql= "select k.KnowledgeMapId,\n" +
                        "k.Name,\n" +
                        "k.Description,\n" +
                        "k.CreateOn as CreatedOn,\n" +
                        "k.IsPublic,\n" +
                        "u.FirstName,\n" +
                        "u.LastName,\n" +
                        "Concat(\"Dr \" ,u.FirstName , ' ' , u.LastName) as CreatedBy \n" +
                        "from  knowledgemap k inner join user u on k.CreatedBy=u.UserId;";
       
        List<KnowledgeMapItem> knowledgemaps= new ArrayList();
   
        this.dataSource.Open();
        this.dataSource.ExecuteCustomDataSet(sql, knowledgemaps,KnowledgeMapItem.class);
        Gson g = new Gson();
        message.ChangeContent(g.toJson(knowledgemaps));
        message.ChangeStatus("ok");
      }
      catch(Throwable ex){
       // this.dataSource.Rollback();
        message.ChangeContent("");
        message.ChangeStatus("exception");
        message.UpdateError(ex.toString());
      }
      finally{
        this.dataSource.Close();
        
        }
       
    
    }
   public Boolean HasKnowledgeMap(KnowledgeMapId knowledgeMapId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.Callback;
import OTS.ConceptSchemaDescription;
import OTS.ConceptTransaction;
import OTS.DataModels.ConceptSchema;
import OTS.DataModels.DataSource;
import OTS.DataModels.Knowledgemap;
import OTS.DataModels.Node;
import OTS.DataModels.NodeItem;
import OTS.DataModels.User;
import OTS.DeleteConceptNodeState;
import OTS.Identity;
import OTS.RelationType;
import OTS.RenameConceptNodeState;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MEA
 */
public class ConceptNodeTransaction implements ConceptTransaction {

    DataSource dataSource;
    Date currentDate;
   String queryString="select * from knowledgemap m inner join user as u" +
                                              " on m.CreatedBy=u.UserId where"
                                             + " KnowledgemapId=";

    public ConceptNodeTransaction(DataSource dataSource) {
        this.dataSource = dataSource;
        this.currentDate=new Date();
    }
    
    
    @Override
    public void CreateConceptNode(int userId, String name,String description,Callback callback) {
     
       try{
          //this.dataSource.Open();
         // this.dataSource.BeginTransaction();
         String identity=Identity.NewGiudIdentity().ID.toString();
          Knowledgemap km= new Knowledgemap();
          km.setName(name);
          km.setDescription(description);
          User u= (User)this.dataSource.Find(User.class,new Integer(userId));
          km.setUser(u);
          km.setIsPublic(Boolean.TRUE);
          km.setCreateOn(currentDate);
          this.dataSource.Save(km);
         // this.dataSource.Commit();
          Node node= new Node(km);
          callback.OnSucces(km.getKnowledgeMapId(), "ok",node.ToJsonDiscription());
       }
       catch(Throwable ex){
         // this.dataSource.Rollback();
          callback.OnFailure(0, "fail", ex.toString());
       }
       finally{
          //this.dataSource.Close();
       }
    }




    
    public void RenameParentConcept(RenameConceptNodeState item,Callback callback){
       try{
          Knowledgemap km=null;
          Node n=null;
          km=  this.Find(item.ParentId);
          km.setName(item.Name);
          km.setDescription(item.description);
          km.setLastUpdated(currentDate);
          //this.dataSource.Open();
         // this.dataSource.BeginTransaction();
          this.dataSource.Update(km);
          //this.dataSource.Commit();
           n= new Node(km);
            callback.OnSucces(km.getKnowledgeMapId(), "ok",n.ToJsonDiscription());
        }
        catch(Throwable ex){
           // this.dataSource.Rollback();
            callback.OnFailure(0, "fail", ex.toString());
        }
        finally{
         // this.dataSource.Close();
        }
    }
    
    
     public void RenameChildConcept (RenameConceptNodeState item,Callback callback){
        try{
        
           RelationType relationType=RelationType.UNKNOWN; 
           if(item.RelationType.equals("PartOf")){
              relationType=RelationType.PartOf;
           }
           if(item.RelationType.equals("TypeOf")){
              relationType=RelationType.TypeOf;
           }
           Knowledgemap km=null;
           Node n=null;
           km=  this.Find(item.ParentId);
           Node node=this.FindRootNode(item.ParentId);
           NodeItem  nodeItem=this.FindNodeItem(node, item.ConceptNodeIdentity);
           nodeItem.Name=item.Name;
           nodeItem.RelationType=relationType;
           String temp=node.NodesToXml();
           km.setConcepts(temp);
           km.setLastUpdated(new Date());
           // this.dataSource.Open();
           // this.dataSource.BeginTransaction(); 
            this.dataSource.Update(km);
            //this.dataSource.Commit();
           // this.dataSource.Close();
            n= new Node(km);
            callback.OnSucces(km.getKnowledgeMapId(), "ok",n.ToJsonDiscription());
        }
        catch(Throwable ex){
            //this.dataSource.Rollback();
            callback.OnFailure(0, "fail", ex.toString());
        }
        finally{
           // this.dataSource.Close();
        }
    
    }
    
     public boolean IsParentConcept(RenameConceptNodeState item){
        if(item.ConceptNodeIdentity==null || item.ConceptNodeIdentity.equals("")){
          return true;
        }
         return false;
     }
     
     
    @Override
    public void RenameSubConceptNode(RenameConceptNodeState item,Callback callback){
       
           if(this.IsParentConcept(item)){
              this.RenameParentConcept(item, callback);
           }
           else{
              this.RenameChildConcept(item, callback);
           }
    }
    
    @Override
    public void RenameConceptNode(RenameConceptNodeState item,Callback callback) {
       //obsulate to be deleted
        String sql=this.queryString + item.ParentId;
     
       try
       {
           Knowledgemap knowledgemap=null;
          // this.dataSource.Open();
         //  this.dataSource.BeginTransaction();  
           List<Object> entities=new ArrayList();
           entities.add(new Knowledgemap());
           entities.add(new User());
           Object[] knowledgemaps= new Object[1];
           this.dataSource.ExecuteDataSet(sql, entities, knowledgemaps);
            if(knowledgemaps.length>0){
                Object obj=knowledgemaps[0];
                 Object temp= ((Object[])obj)[0];
                  knowledgemap=(Knowledgemap)temp;
                  knowledgemap.setName(item.Name);
                  knowledgemap.setDescription(item.description); 
                  this.dataSource.Save(knowledgemap);
                 // this.dataSource.Commit();
                  Node n= new Node(knowledgemap);
                callback.OnSucces(knowledgemap.getKnowledgeMapId(), "ok",n.ToJsonDiscription());
                 
            }
            else{
               callback.OnFailure(0, "fail", "ConceptNode not found");
            }
       }
       catch(Throwable ex){
          //this.dataSource.Rollback();
          callback.OnFailure(0, "fail", ex.toString());
       }
       finally{
         // this.dataSource.Close();
       }
    }

      @Override
    public void AddConceptNode(int parentId, String conceptNodeIdentity, String name, RelationType relationType, Callback callback) {
       try{
         
           //Find the parent Node
           //Find the NodeItem by Identity
           //If NodeItem is not found : Create new and add to parent
           //else
           //Create new node item and add to the found node
           NodeItem newNodeitem;
           Node node= this.FindRootNode(parentId);
           NodeItem nodeItem=this.FindNodeItem(node, conceptNodeIdentity);
            String  newChildIdentity=Identity.NewGiudIdentity().ID.toString();
           if( nodeItem==null){
            
               newNodeitem= new NodeItem(parentId,relationType,name,newChildIdentity);
               node.AddNode(newNodeitem);
           }
           else{
               newNodeitem= new NodeItem(parentId,relationType,name,newChildIdentity);
               nodeItem.Add(newNodeitem);
           }
           Knowledgemap km=this.Find(parentId);
          // this.dataSource.Open();
         //  this.dataSource.BeginTransaction();
           km.setConcepts(node.NodesToXml());
           this.dataSource.Update(km);
          // this.dataSource.Commit();
          Node n= new Node(km);
           callback.OnSucces(km.getKnowledgeMapId(), "ok", n.ToJsonDiscription());
       }
      catch(Throwable ex){
         // this.dataSource.Rollback();
          callback.OnFailure(0, "fail", ex.toString());
       }
       finally{
        //  this.dataSource.Close();
       }
    }
    
    public void RemoveParentNode(DeleteConceptNodeState state, Callback callback){
      try{
          Knowledgemap km=this.Find(state.ParentId);
          //this.dataSource.Open();
          //this.dataSource.BeginTransaction();
          this.dataSource.Delete(km);
          Node n= new Node(km);
         // this.dataSource.Commit();
          callback.OnSucces(km.getKnowledgeMapId(), "ok", n.ToJsonDiscription());
      }
      catch(Throwable ex){
          // this.dataSource.Rollback();
          callback.OnFailure(0, "fail", ex.toString());
      }
      finally
      {
          //this.dataSource.Close();
      }
    
    }
    
    public void RemoveChildNode(DeleteConceptNodeState state, Callback callback){
          try{
          Knowledgemap km=null;
          NodeItem child=null;
          km=  this.Find(state.ParentId);
           Node node=this.FindRootNode(state.ParentId);
           child=this.FindNodeItem(node, state.ConceptNodeIdentity);
           
           String parentIdenitity=  child.ParentIdentity;
            if(parentIdenitity==null || parentIdenitity.isEmpty()){
               node.Remove(child);
            }
            else{
             //get the parent
              NodeItem parent=this.FindNodeItem(node, parentIdenitity);
              if(parent!=null){
                 parent.Remove(child);
              }
            }
         //  this.dataSource.Open();
          // this.dataSource.BeginTransaction();
            String temp=node.NodesToXml();
           km.setConcepts(temp);
           km.setLastUpdated(new Date());
           this.dataSource.Update(km);
           Node n= new Node(km);
          // this.dataSource.Commit();
           callback.OnSucces(km.getKnowledgeMapId(), "ok", n.ToJsonDiscription());
          }
          catch(Throwable ex){
             // this.dataSource.Rollback();
              callback.OnFailure(0, "fail", ex.toString());
          }
          finally{
            //  this.dataSource.Close();
          }
    }
    
    @Override
    public void RemoveConcpetNode(DeleteConceptNodeState state, Callback callback) {
        
          if(state.ConceptNodeIdentity==null){
            this.RemoveParentNode(state, callback);
          }else{
            this.RemoveChildNode(state, callback);
          }
    }

    public Knowledgemap Find(int parentConceptNodeId){
       
         Knowledgemap knowledgemap=null;
         List<Object> entities=new ArrayList();
           entities.add(new Knowledgemap());
           entities.add(new User());
         try{
        String sql=this.queryString + parentConceptNodeId;
          Object[] knowledgemaps= new Object[1];
         // this.dataSource.BeginTransaction();
            //this.dataSource.Open();
           this.dataSource.ExecuteDataSet(sql, entities, knowledgemaps);
            if(knowledgemaps.length>0){
                Object obj=knowledgemaps[0];
                 Object item= ((Object[])obj)[0];
                  knowledgemap=(Knowledgemap)item;
             //  this.dataSource.Commit();
                return knowledgemap; 
                
            }
         }
         catch(Throwable ex){
       
         
         }
         finally{
            //this.dataSource.Close();
         }
         return  knowledgemap;
    }
   
   
    
    public NodeItem FindNodeItem(Node parantNode,String Identity){
      
       return  parantNode.Find(Identity);
    }
    
    public Node FindRootNode(int id){
        Knowledgemap km=this.Find(id);
        Node node= new Node(km);
        return node;
    }

    
     public void CreateConceptNode(int userId, Knowledgemap kp,Callback callback) {
     
       try{
        //  this.dataSource.Open();
          //this.dataSource.BeginTransaction();
       //  String identity=Identity.NewGiudIdentity().ID.toString();
          Knowledgemap km= new Knowledgemap();
          km.setName(kp.getName());
          km.setDescription(kp.getDescription());
          km.setConcepts(kp.getConcepts());
          User u= (User)this.dataSource.Find(User.class,new Integer(userId));
          km.setUser(u);
          km.setIsPublic(Boolean.FALSE);
          km.setCreateOn(currentDate);
          km.setLastUpdated(currentDate);
          this.dataSource.Save(km);
         // this.dataSource.Commit();
          Node node= new Node(km);
          callback.OnSucces(km.getKnowledgeMapId(), "ok",node.ToJsonDiscription());
       }
       catch(Throwable ex){
         // this.dataSource.Rollback();
          callback.OnFailure(0, "fail", ex.toString());
       }
       finally{
          //this.dataSource.Close();
       }
    }
    
    @Override
    public void ImportKowledgeMap(int userId, List<String> knowledgeMaps, Callback callback) {
       
        try{
           
           User u= this.FindUser(userId);
           for(String s: knowledgeMaps)
           {
              Knowledgemap km=this.Find(Integer.parseInt(s));
              this.CreateConceptNode(u.getUserId(), km, callback);
           }
         
        }
        catch(Throwable ex){
           
             callback.OnFailure(0, "fail", ex.toString());
        }
        finally{
         
        }
    }
    
    
    @Override
    public void DuplicateConcpetNode(int userId, int conceptNodeId, 
            String name, String description, Callback callback) {
            Knowledgemap km = this.Find(conceptNodeId);
        try{
        //  this.dataSource.Open();
         // this.dataSource.BeginTransaction();
          Node node= new Node(km);
          Knowledgemap newKnowledgeMap= new Knowledgemap();
          newKnowledgeMap.setName(name);
         newKnowledgeMap.setDescription(description);
          User u= (User)this.dataSource.Find(User.class,new Integer(userId));
          newKnowledgeMap.setUser(u);
          newKnowledgeMap.setIsPublic(Boolean.TRUE);
          newKnowledgeMap.setCreateOn(currentDate);
          this.dataSource.Save(newKnowledgeMap);
          int newId= newKnowledgeMap.getKnowledgeMapId();
          node.Id=newId;
          node.Update(newId);
           if(node.HasChildren()){
            newKnowledgeMap.setConcepts(node.NodesToXml());
           }
          this.dataSource.Update(newKnowledgeMap);
         // this.dataSource.Commit();
         Node n= new Node(newKnowledgeMap);
          callback.OnSucces(newKnowledgeMap.getKnowledgeMapId(), "ok",n.ToJsonDiscription());
       }
       catch(Throwable ex){
          //this.dataSource.Rollback();
          callback.OnFailure(0, "fail", ex.toString());
       }
       finally{
           //this.dataSource.Close();
        }
        
    }

    @Override
    public void AddConceptSchema(ConceptSchemaDescription item,  Callback callback) {
       
          try{   
           Node node= this.FindRootNode(item.RootId);
           NodeItem nodeItem=this.FindNodeItem(node, item.ParentIdentity); 
             ConceptSchema cs=    nodeItem.FindConceptSchema(item.Id);
             if(cs==null){
                // Create new
                ConceptSchema  s= new ConceptSchema(item);
                 nodeItem.AddConceptSchema(s); 
             }
             else{ //update 
               cs.UpdateDescription(item);
               nodeItem.InsertConceptSchema(cs);
             }
           Knowledgemap km=this.Find(item.RootId);
          // this.dataSource.Open();
         //  this.dataSource.BeginTransaction();
           km.setConcepts(node.NodesToXml());
           this.dataSource.Update(km);
          // this.dataSource.Commit();
           Node n= new Node(km);
          callback.OnSucces(km.getKnowledgeMapId(), "ok", n.ToJsonDiscription());
         
       }
      catch(Throwable ex){
          //this.dataSource.Rollback();
        callback.OnFailure(0, "fail", ex.toString());
         
       }
       finally{
             // this.dataSource.Close();
          }
    }

    @Override
    public void DeleteConceptSchema(ConceptSchemaDescription item, Callback callback) {
        try{   
           Node node= this.FindRootNode(item.RootId);
           NodeItem nodeItem=this.FindNodeItem(node, item.ParentIdentity); 
           ConceptSchema cs=    nodeItem.FindConceptSchema(item.Id);
           cs.RemoveFrom(nodeItem);
           Knowledgemap km=this.Find(item.RootId);
          // this.dataSource.Open();
          // this.dataSource.BeginTransaction();
           km.setConcepts(node.NodesToXml());
           this.dataSource.Update(km);
          // this.dataSource.Commit();
           Node n= new Node(km);
          
          callback.OnSucces(km.getKnowledgeMapId(), "ok", n.ToJsonDiscription());
       }
      catch(Throwable ex){
         // this.dataSource.Rollback();
         callback.OnFailure(0, "fail", ex.toString());
         
       }
       finally{
         // this.dataSource.Close();
        }
    }

    @Override
    public void ListConceptSchema(int id, String parentIdentity, String identity,Callback callback) {
        try{   
           Node node= this.FindRootNode(id);
           NodeItem nodeItem=this.FindNodeItem(node, identity);
           
           ConceptSchema cs=    nodeItem.FindConceptSchema(identity);
           callback.OnSucces(id, "ok", nodeItem.ConceptSchemaToJson());
       }
      catch(Throwable ex){
         callback.OnFailure(0, "fail", ex.toString());
          
       }
    }

     private User FindUser(int userId){
         
           try{
              // this.dataSource.Open();
              User u= (User)this.dataSource.Find(User.class,new Integer(userId));
               return u; 
           }
           catch(Throwable ex){
               return null;
           }
           finally{
              // this.dataSource.Close();
           }
           
     }
  
}

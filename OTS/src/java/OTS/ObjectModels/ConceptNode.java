/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.Concept;
import OTS.ConceptSchemaDescription;
import OTS.ConceptTransaction;
import OTS.DeleteConceptNodeState;
import OTS.Message;
import OTS.RelationType;
import OTS.RenameConceptNodeState;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author MEA
 */
public class ConceptNode implements Concept {

    String name;
    int userId;
    int conceptNodeId;
    String description;
    Message response;
    
    public ConceptNode(int userId ,Message response) {
        this.response=response;
        this.userId=userId;
    }
     
     public ConceptNode(int userId ,int conceptNodeId,Response response) {
        this.response=response;
        this.userId=userId;
        this.conceptNodeId=conceptNodeId;
    }
     
    @Override
    public void Create(ConceptTransaction conceptTransaction, String name,String description) {
         TransactionCallback cb= new TransactionCallback();
        if ((conceptTransaction!=null ) && !name.isEmpty() && this.userId> 0 ){
              this.name=name;
              this.description=description;
               conceptTransaction.CreateConceptNode(this.userId,this.name, this.description,cb);
               cb.WriteDescription(response);
         }
         else{
            this.PrepareError("Invalid Concept name cannot be empty or userId is not valid");
         }
    }

    @Override
    public void Add(ConceptTransaction conceptTransaction ,int parentConceptNodeId, String  conceptNodeIdentity, String name,RelationType relationType) {
      if((conceptTransaction!=null ) && !name.isEmpty() && parentConceptNodeId>0  ){
           TransactionCallback cb= new TransactionCallback();
          conceptTransaction.AddConceptNode(parentConceptNodeId, conceptNodeIdentity, name,relationType, cb);
          cb.WriteDescription(response);
      }
      else{
            this.PrepareError("ConceptNode name cannot be empty");
      }  
    }

    @Override
    public void Remove(ConceptTransaction conceptTransaction, DeleteConceptNodeState state) {
           if(conceptTransaction!=null  && state  !=null  ){
                 TransactionCallback cb= new TransactionCallback();
             conceptTransaction.RemoveConcpetNode(state, cb);
             cb.WriteDescription(response);
         }
         else{
            this.PrepareError("Invalid Concept Node Information");
         }
    }

    private void PrepareError(String message){
            response.ChangeContent("-");
            response.ChangeStatus("fail");
            response.UpdateError(message);
            response.UpdateIdentity("");
            response.UpdateID(0);
    }

    @Override
    public void Rename(ConceptTransaction conceptTransaction, RenameConceptNodeState item) {
         if(conceptTransaction!=null  && item  !=null  ){
                 TransactionCallback cb= new TransactionCallback();
             conceptTransaction.RenameSubConceptNode(item, cb);
             cb.WriteDescription(response);
         }
         else{
            this.PrepareError("Invalid Concept Node Information");
         }
    }

    @Override
    public void Deplicate(ConceptTransaction conceptTransaction, int conceptNodeId,String name,String description) {
       if(conceptTransaction!=null  && conceptNodeId>0 ){
                 TransactionCallback cb= new TransactionCallback();
                 this.name=name;
                 this.description=description;
             conceptTransaction.DuplicateConcpetNode(this.userId, conceptNodeId, name, description, cb);
             cb.WriteDescription(response);
         }
         else{
            this.PrepareError("Invalid Concept Node Information");
         }
    }

    @Override
    public void AddConceptSchema(ConceptTransaction conceptTransaction, ConceptSchemaDescription desc) {
       if(conceptTransaction!=null  && desc !=null){
           TransactionCallback cb= new TransactionCallback();
             conceptTransaction.AddConceptSchema(desc, cb);
             cb.WriteDescription(response);
         }
         else{
            this.PrepareError("Invalid Concept Schema Information");
         }
    }

    @Override
    public void DeleteConceptSchema(ConceptTransaction conceptTransaction, ConceptSchemaDescription state) {
         if(conceptTransaction!=null  && state !=null){
           TransactionCallback cb= new TransactionCallback();
             conceptTransaction.DeleteConceptSchema(state, cb);
             cb.WriteDescription(response);
         }
         else{
            this.PrepareError("Invalid Concept Schema Information");
         }
    }

    @Override
    public void ListConceptSchema(ConceptTransaction conceptTransaction, int id, String parentIdentity, String identity) {
         if(conceptTransaction!=null  && id >0 &&  !parentIdentity.isEmpty() && !identity.isEmpty()){
           TransactionCallback cb= new TransactionCallback();
             conceptTransaction.ListConceptSchema(id,parentIdentity,identity,cb);
             cb.WriteDescription(response);
         }
         else{
            this.PrepareError("Invalid Concept Schema Information");
         }
    }

    @Override
    public void ImportKnowledgeMap(ConceptTransaction conceptTransaction, String knowledgeMapIds) {
       if(conceptTransaction!=null  && this.userId>0 && knowledgeMapIds.length()>0){
           TransactionCallback cb= new TransactionCallback();
             List<String> list= Arrays.asList(knowledgeMapIds.split(","));
             conceptTransaction.ImportKowledgeMap(userId, list, cb);
             cb.WriteDescription(response);
         }
         else{
            this.PrepareError(response.ToJson());
         }
    }

   

   
  
}

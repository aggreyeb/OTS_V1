/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS;

import java.util.List;

/**
 *
 * @author MEA
 */
public interface ConceptTransaction {
   public void CreateConceptNode(int userId,String name,String description,Callback callback);
   public  void RenameConceptNode(RenameConceptNodeState item,Callback callback);
   public void RenameSubConceptNode(RenameConceptNodeState item,Callback callback);
   public void AddConceptNode(int parentId,String  conceptNodeIdentity,String name,RelationType relationType,Callback callback);
   public void RemoveConcpetNode(DeleteConceptNodeState state,Callback callback);
   public void DuplicateConcpetNode(int userId,int conceptNodeId,String name,String description,Callback callback);
   public void AddConceptSchema(ConceptSchemaDescription item, Callback callback);
   public void DeleteConceptSchema(ConceptSchemaDescription item,  Callback callback);

    public void ListConceptSchema(int id, String parentIdentity, String identity,Callback callback);
    
      public void ImportKowledgeMap(int userId,List<String> knowledgeMaps,Callback callback);
   
}
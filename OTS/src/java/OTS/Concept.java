/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS;

/**
 *
 * @author MEA
 */
public interface Concept {
    
    public void Create(ConceptTransaction conceptTransaction,String name,String description);
    public void Rename(ConceptTransaction conceptTransaction,RenameConceptNodeState item);
    public void Add(ConceptTransaction conceptTransaction,int parentConceptNodeId, String  conceptNodeIdentity,String name,RelationType relationType);
    public void Remove(ConceptTransaction conceptTransaction, DeleteConceptNodeState state);
    public void Deplicate(ConceptTransaction conceptTransaction, int conceptNodeId,String name,String description);
    public void AddConceptSchema(ConceptTransaction conceptTransaction,ConceptSchemaDescription desc);
    public void DeleteConceptSchema(ConceptTransaction conceptTransaction,ConceptSchemaDescription state);
    public void ListConceptSchema(ConceptTransaction conceptTransaction,int id,String parentIdentity,String identity);
    public void ImportKnowledgeMap(ConceptTransaction conceptTransaction,String knowledgeMapIds);
}

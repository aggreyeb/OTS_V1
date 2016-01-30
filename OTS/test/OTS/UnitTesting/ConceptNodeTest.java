/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.UnitTesting;

import OTS.ConceptTransaction;
import OTS.DataModels.Knowledgemap;
import OTS.DataModels.MySqlDataSource;
import OTS.DataModels.Node;
import OTS.DataModels.NodeItem;
import OTS.DataModels.NodeList;
import OTS.DataModels.User;
import OTS.DeleteConceptNodeState;
import OTS.Identity;
import OTS.ObjectModels.ConceptNode;
import OTS.ObjectModels.ConceptNodeTransaction;
import OTS.ObjectModels.Response;
import OTS.RelationType;
import OTS.RenameConceptNodeState;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jettison.json.JSONArray;
import org.junit.Test;

/**
 *
 * @author MEA
 */
public class ConceptNodeTest {
    
     @Test
    public void CreateConceptNode(){
       Response response= new Response("","");
       ConceptNodeTransaction tx = new ConceptNodeTransaction(new MySqlDataSource());
       ConceptNode conceptNode= new ConceptNode(1,response);
       conceptNode.Create(tx, "Plant", "Plant Concept");
       System.out.print( response.ToJson());
    }
    
   @Test
    public void AddConceptNode(){
       int parentId=13;
       String conceptNodeIdentity=Identity.NewGiudIdentity().ID.toString();
       String name="Leaf";
       RelationType relationType=RelationType.PartOf;
       
       Response response= new Response("","");
       ConceptNodeTransaction tx = new ConceptNodeTransaction(new MySqlDataSource());
       ConceptNode conceptNode= new ConceptNode(1,response);
       conceptNode.Add(tx,parentId,conceptNodeIdentity,name,relationType);
       System.out.print( response.ToJson());
    }
    
    @Test
    public void CreateQueryTest(){}
    {
        String sqlQuery="select * from knowledgemap m inner join user as u" +
                                              " on m.CreatedBy=u.UserId";
       MySqlDataSource db=   new MySqlDataSource();
       db.BeginTransaction();
       Object[] concepts=new ArrayList().toArray();
       List<Object> entities= new ArrayList();
       entities.add(new Knowledgemap());
       entities.add(new User());
       db.ExecuteDataSet(sqlQuery,entities,concepts);
       db.Commit();
      System.out.print( concepts.length);

    }
    
    
     @Test
    public void DuplicateConceptNode(){
       int conceptNodeId=2;
       String name="NewCopy";
       String description="newCopyDescription";
       
       Response response= new Response("","");
       ConceptNodeTransaction tx = new ConceptNodeTransaction(new MySqlDataSource());
       ConceptNode conceptNode= new ConceptNode(1,response);
       
       conceptNode.Deplicate(tx, conceptNodeId, name, description);
       System.out.print( response.ToJson());
     
    }
    
    
    @Test
    public void RemoveConceptNode(){
       Response response= new Response("","");
       ConceptNodeTransaction tx = new ConceptNodeTransaction(new MySqlDataSource());
       ConceptNode conceptNode= new ConceptNode(1,response);
       DeleteConceptNodeState state= new DeleteConceptNodeState();
       state.ParentId=1;
      // state.ConceptNodeIdentity="015ae36b-7e6f-4002-b408-4c339e1a6d4c";
       state.UserId=1;
       conceptNode.Remove(tx, state);
       System.out.print( response.ToJson());
     
    }
    
    
    
     @Test
    public void RenameParentNodeConceptNode(){
       Response response= new Response("","");
       ConceptNodeTransaction tx = new ConceptNodeTransaction(new MySqlDataSource());
       ConceptNode conceptNode= new ConceptNode(1,response);
       RenameConceptNodeState state= new RenameConceptNodeState();
       state.ParentId=1;
       state.Name="Plant";
       state.description="Plant Description";
       state.UserId=1;
       conceptNode.Rename(tx, state);
       System.out.print( response.ToJson());
     
    }
   
     @Test
    public void RenameConceptNode(){
       Response response= new Response("","");
       ConceptNodeTransaction tx = new ConceptNodeTransaction(new MySqlDataSource());
       ConceptNode conceptNode= new ConceptNode(1,response);
       RenameConceptNodeState state= new RenameConceptNodeState();
       state.ParentId=1;
       state.Name="Margin";
       state.ConceptNodeIdentity="015ae36b-7e6f-4002-b408-4c339e1a6d4c";
       state.UserId=1;
       conceptNode.Rename(tx, state);
       System.out.print( response.ToJson());
     
    }
    
  
   
    
     @Test
    public void AddSubConceptNode(){
       int parentId=13;
       String conceptNodeIdentity="e1d9e934-719f-4688-a2d8-39037a21e3d6";
       String name="Margin";
       RelationType relationType=RelationType.PartOf;
       
       Response response= new Response("","");
       ConceptNodeTransaction tx = new ConceptNodeTransaction(new MySqlDataSource());
       ConceptNode conceptNode= new ConceptNode(1,response);
       conceptNode.Add(tx,parentId,conceptNodeIdentity,name,relationType);
       System.out.print( response.ToJson());
    }
    
    
    @Test
    public void FindConceptNode(){
        int conceptId=13;
       ConceptNodeTransaction tx = new ConceptNodeTransaction(new MySqlDataSource());
       Knowledgemap km=  tx.Find(conceptId);
      System.out.print(km.getName());
       
    }
    
   @Test
    public void FindConceptNodeAddChild(){
        int conceptId=15;
       String identity="4ba6610d-8095-429a-bd7b-e2756daed9be";
      //  String root="6731c2cf-1e9d-48d0-bf97-dc5ec1f12921";
        
        ConceptNodeTransaction tx = new ConceptNodeTransaction(new MySqlDataSource());
        Knowledgemap km=  tx.Find(conceptId);
        Node node = new Node(km);
        NodeItem result= node.Find(identity);
        
        String sproutIdentity=Identity.NewGiudIdentity().ID.toString();
        NodeItem newNodeItem= new NodeItem(node.Id,RelationType.PartOf,"Sprout",sproutIdentity);
        result.Add(newNodeItem);
        NodeItem sprout= node.Find(sproutIdentity);
        
        
        String sproutChildIdentity=Identity.NewGiudIdentity().ID.toString();
        NodeItem sprautnewNodeItem= new NodeItem(sprout.Identity.toString(),RelationType.PartOf,"SproutB",sproutChildIdentity);
        sprout.Add(sprautnewNodeItem);
        
         NodeItem sproutChild= node.Find(sproutChildIdentity);
        
        System.out.print(sproutChild.Name);
    }
    
     @Test
    public void FindKnowledgeMap(){
        int conceptId=13;
        ConceptNodeTransaction tx = new ConceptNodeTransaction(new MySqlDataSource());
        Knowledgemap km=  tx.Find(conceptId);
        Node node = new Node(km);
        
        System.out.print(node.ToJsonDiscription());
        
       
       

    }
      
}

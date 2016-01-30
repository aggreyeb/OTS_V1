/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.UnitTesting;

import OTS.ConceptSchemaDescription;
import OTS.DataModels.MySqlDataSource;
import OTS.Identity;
import OTS.ObjectModels.ConceptNode;
import OTS.ObjectModels.ConceptNodeTransaction;
import OTS.ObjectModels.Response;
import org.junit.Test;

/**
 *
 * @author MEA
 */
public class ConceptSchemaTest {
    
    @Test
    public void AddConceptSchema(){
       Response response= new Response("","");
       Identity identity=Identity.NewGiudIdentity();
       ConceptSchemaDescription desc= new ConceptSchemaDescription(identity);
       desc.RootId=58;
       desc.ParentIdentity="0e53b069-7453-4813-8289-26e22509d1a2";
       desc.RelationName="is"; 
       desc.ConceptName="vegetative organ";
       desc.ConceptAction="";
       desc.AttributeName="attributeName";
       desc.AttributeValue="attributeValue";
       ConceptNodeTransaction tx = new ConceptNodeTransaction(new MySqlDataSource());
       ConceptNode conceptNode= new ConceptNode(1,response);
       conceptNode.AddConceptSchema(tx, desc);
       System.out.print(response.ToJson());
      
    }
    
    
    @Test
    public void UpdateConceptSchema(){
       Response response= new Response("","");
       Identity identity=Identity.NewGiudIdentity();
       ConceptSchemaDescription desc= new ConceptSchemaDescription(identity);
       desc.Id="0e53b069-7453-4813-8289-26e22509d1a2";
       desc.RootId=58;
       desc.ParentIdentity="0e53b069-7453-4813-8289-26e22509d1a2";
       desc.RelationName="Can";
       desc.ConceptName="water";
       desc.ConceptAction="absorb";
       desc.AttributeName="attributeName";
       desc.AttributeValue="attributeValue";
       ConceptNodeTransaction tx = new ConceptNodeTransaction(new MySqlDataSource());
       ConceptNode conceptNode= new ConceptNode(1,response);
       conceptNode.AddConceptSchema(tx, desc);
       System.out.print(response.ToJson());
      
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels.TestAlgorithms;

import OTS.DataModels.ConceptSchema;
import OTS.DataModels.DataSource;
import OTS.DataModels.Node;
import OTS.DataModels.NodeItem;
import OTS.ObjectModels.ConceptSchemaItem;
import OTS.ObjectModels.TestGenerationInput;
import OTS.ObjectModels.TestItemGenerationOutput;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aggreeb
 */
public class DescribeTrueFalseIncorrectAlgorithm extends Algorithm{

    public DescribeTrueFalseIncorrectAlgorithm(DataSource dataSource, String name) {
        super(dataSource, name);
        this.Name=name;
    }

    
      @Override
    protected TestItemGenerationOutput BuildOuput(Node node,
        TestGenerationInput input){
        TestItemGenerationOutput output= new TestItemGenerationOutput();
      
        output.Text=node.Name;
        String lineItem ="";
        if(input.IncludeSubTrees){
             for(NodeItem item:node.ListChildren())
             {
                 for(ConceptSchema s:item.ListConceptSchemas()){
                     lineItem=this.Describe(node);
                     output.LineItems.add(lineItem);
                 }
            }
        }
        return output;
    }
  
    
      @Override
    protected TestItemGenerationOutput BuildOuput(NodeItem node,
        TestGenerationInput input){
        TestItemGenerationOutput output= new TestItemGenerationOutput();
        if(this.IsParentNode(input))
            return output;
        output.Text=node.Name;
        
        String lineItem ="";
        List<NodeItem> nodeItems=  this.ExcludeNode(node);
        
         TestItemGenerationOutput correctOutput= new TestItemGenerationOutput();
        try{
         //Incorrect   
        if(input.IncludeSubTrees){
         for(NodeItem n:nodeItems){
            for(ConceptSchema s:n.ListConceptSchemas()){
               ConceptSchemaItem item=   s.Description();
               lineItem=this.Describe(node,item); 
               if(!output.LineItems.contains(lineItem) && ! correctOutput.LineItems.contains(lineItem))
                  output.LineItems.add(lineItem);
             }
           }
          }
        
          
        }
        catch(Throwable ex){
            
        }
        return output;
    }
   
   
    
    protected String Describe(NodeItem nodeItem, ConceptSchemaItem conceptSchemaItem){
         String s="";
     
         //inheritance
        if(conceptSchemaItem.relationName.equals("is")){
            s=nodeItem.Name + " " +  conceptSchemaItem.relationName + " " + "not" +  " " +  conceptSchemaItem.conceptName;
        }
        else{ 
           s= nodeItem.Name + " " + conceptSchemaItem.relationName + " " + conceptSchemaItem.conceptAction + " " + conceptSchemaItem.conceptName;
        }
        return s;
    }
    
     protected String Describe(Node node){
        return node.Name;
    }
    
    
    
    protected List<NodeItem> ExcludeNode(NodeItem nodeItem){
         List<NodeItem> list= new ArrayList();
        for(NodeItem item :this.ParentNode.ListChildren() ){
            if(!item.equals(nodeItem)){
                list.add(item);
            }
        }
      return list;
    }
}

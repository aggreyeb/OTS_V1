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
import java.util.Collection;
import java.util.List;

/**
 *
 * @author aggreeb
 */
public class DescribeTrueFalseNagativeAlgorithm extends Algorithm{

    public DescribeTrueFalseNagativeAlgorithm(DataSource dataSource, String name) {
        super(dataSource, name);
        this.Name=name;
    }

    
      @Override
    protected TestItemGenerationOutput BuildOuput(Node node,
        TestGenerationInput input){
        TestItemGenerationOutput output= new TestItemGenerationOutput();
    
        String lineItem ="";
        output.Text=node.Name;
        if(input.IncludeSubTrees){
            /*
            for(NodeItem item:node.ListChildren())
             {
                 for(ConceptSchema s:item.ListConceptSchemas()){
                     //lineItem=this.Describe(node);
                     List<String>  lineItems=this.Describe(node);
                     output.LineItems.addAll(lineItems);
                    
                 }
            }*/
            List<String>  lineItems=this.Describe(node);
            output.LineItems.addAll(lineItems);
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
        try{
        if(input.IncludeSubTrees){
           for(ConceptSchema s:node.ListConceptSchemas()){
                ConceptSchemaItem item=   s.Description();
               lineItem=this.Describe(node,item);              
               output.LineItems.add(lineItem);
           }
          }
        }
        catch(Throwable ex){
            
        }
        return output;
    }
    
     protected String Describe(NodeItem nodeItem, ConceptSchemaItem conceptSchemaItem){
         String s="";
       
          if(!conceptSchemaItem.relationName.equals("") && !conceptSchemaItem.conceptName.equals("") && conceptSchemaItem.conceptAction.equals("") && !conceptSchemaItem.attributeName.equals("") && !conceptSchemaItem.attributeValue.equals("")){
           s="The " + conceptSchemaItem.attributeName.toLowerCase() + " " + "of " + nodeItem.Name + " is not " + conceptSchemaItem.attributeValue.toLowerCase();
         }
          else{
               s=nodeItem.Name + " " +  conceptSchemaItem.relationName + " not " + conceptSchemaItem.conceptAction + " " + conceptSchemaItem.conceptName;
          }
        return s;
    }
    
     
     
     protected List<String> Describe(Node node){
        List<String> items= new ArrayList();
        /*
       for(NodeItem n: node.ListChildren()){
           String text=node.Name + " "+  "does not have" + " " + n.Name.toLowerCase() ;
           items.add(text);
       }*/
        return items;
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

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
public class DescribeTrueFalseCorrectAlgorithm extends Algorithm{

    public DescribeTrueFalseCorrectAlgorithm(DataSource dataSource, String name) {
        super(dataSource, name);
        this.Name=name;
    }

    
      @Override
    protected TestItemGenerationOutput BuildOuput(Node node,
        TestGenerationInput input){
        TestItemGenerationOutput output= new TestItemGenerationOutput();
        /*
        if(input.ItemType==2)//Multiple choice -single answer
        {
          output.Text="Which of the statement about " + node.Name.toLowerCase() + " " + "is true";
        }
        if(input.ItemType==3)//Multiple choice -Multiple answer
        {
           output.Text="Which of the statement about " + node.Name.toLowerCase() + " " + "are true"; 
        }

      
        String lineItem ="";
        List<String>   lineItems= new ArrayList();
        if(input.IncludeSubTrees){
             for(NodeItem item:node.ListChildren())
             {
                 for(ConceptSchema s:item.ListConceptSchemas()){
                   lineItems=this.Describe(node);
                   this.AddTestLineItem(lineItems, output);
                     //output.LineItems.add(lineItem);
                 }
            }
        }
        */
        return output;
    }
  
    protected void AddTestLineItem(List<String>  lineitems,TestItemGenerationOutput output){
        for(String s :lineitems){
            output.LineItems.add(s);
        }
    }
    
    
    
      @Override
    protected TestItemGenerationOutput BuildOuput(NodeItem node,
        TestGenerationInput input){
        TestItemGenerationOutput output= new TestItemGenerationOutput();
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
         
       if(conceptSchemaItem.relationName!="" && conceptSchemaItem.conceptName !="" && conceptSchemaItem.conceptAction.equals("")){
          s=nodeItem.Name + " " + conceptSchemaItem.relationName + " " + conceptSchemaItem.conceptName;
       }
     
        if(!conceptSchemaItem.relationName.equals("") && !conceptSchemaItem.conceptName.equals("") && conceptSchemaItem.conceptAction.equals("") && !conceptSchemaItem.attributeName.equals("") && !conceptSchemaItem.attributeValue.equals("")){
           s="The " + conceptSchemaItem.attributeName.toLowerCase() + " " + "of " + nodeItem.Name + "'s" +  " is " + conceptSchemaItem.attributeValue.toLowerCase();
        }
        else{
              // s=nodeItem.Name + " " + conceptSchemaItem.relationName + " " +  " " + conceptSchemaItem.conceptAction + " " +  conceptSchemaItem.conceptName;
            if(conceptSchemaItem.relationName.equals("is")){
                 s=nodeItem.Name + " " + conceptSchemaItem.relationName + " " +     conceptSchemaItem.conceptName;   
            }
            else{
                s=nodeItem.Name + " " + conceptSchemaItem.relationName + " " +  " " + conceptSchemaItem.conceptAction + " " +  conceptSchemaItem.conceptName; 
            }
             
        }    
        return s;
    }
    
     protected List<String> Describe(Node node){
         List<String> Items=new ArrayList();
         for(NodeItem n:  node.ListChildren()){
             
            for(ConceptSchema s:n.ListConceptSchemas()){
             ConceptSchemaItem item=   s.Description();
               String str =this.Describe(n,item);              
               Items.add(str);
           }
         }
         return Items;
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

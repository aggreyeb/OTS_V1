
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
public class SummarizeMultipleChoiceAlgorithm extends Algorithm{

    public SummarizeMultipleChoiceAlgorithm(DataSource dataSource, String name) {
        super(dataSource, name);
        this.Name=name;
    }

    @Override
    protected TestItemGenerationOutput BuildOuput(NodeItem node, TestGenerationInput input) {
      
         TestItemGenerationOutput output = new TestItemGenerationOutput();
         output.Text= output.Text="Which of the following statements about " + node.Name.toLowerCase() + " " + " are true?";
        
        for(ConceptSchema s:node.ListConceptSchemas()){
             ConceptSchemaItem item=   s.Description();
             List<String> items=this.Summarize(node,item);              
              // output.LineItems.add(lineItem);
                this.AddNodeLineItems(items, output);
           }
        return output;
    }

    @Override
    protected TestItemGenerationOutput BuildOuput(Node node, TestGenerationInput input) {
        TestItemGenerationOutput output = new TestItemGenerationOutput();
        output.Text="Which of the following statements about " + node.Name.toLowerCase() + " " + " are true?";
        for(NodeItem n : node.ListChildren()){
         for(ConceptSchema s:n.ListConceptSchemas()){
             ConceptSchemaItem item=   s.Description();
             List<String> items=this.Summarize(n,item);              
              // output.LineItems.add(lineItem);
                this.AddNodeLineItems(items, output);
           }
         
        }
        
       return output;
    }
    
    
    protected void AddNodeLineItems(List<String> items, TestItemGenerationOutput output){
        for(String s: items){
            if(output.LineItems.contains(s)) continue;
            output.LineItems.add(s);
        }
    }
    
    protected  List<String> Summarize(NodeItem nodeItem,ConceptSchemaItem conceptSchemaItem){
       List<String> items= new ArrayList();
      items.add(this.CorrectSummary(nodeItem, conceptSchemaItem));
       items.add(this.NegativeSummary(nodeItem, conceptSchemaItem));
       items.addAll(this.IncorrectSummary(nodeItem, conceptSchemaItem));
       items.addAll(this.NegativeIncorrectSummary(nodeItem, conceptSchemaItem));
        
        return items;
    }
    
    

    protected String CorrectSummary(NodeItem nodeitem,ConceptSchemaItem conceptSchemaItem){
        String lineItem="";
         if(conceptSchemaItem.conceptAction.equals("")){
            lineItem=nodeitem.Name + " " + conceptSchemaItem.relationName + " " + conceptSchemaItem.conceptName;
         }
         
          if(!conceptSchemaItem.conceptAction.equals("")){
            lineItem=nodeitem.Name + " "  + conceptSchemaItem.relationName + " "  + conceptSchemaItem.conceptAction + " " +   conceptSchemaItem.conceptName;
         }
        return lineItem;
    }
    
    
     protected String NegativeSummary(NodeItem nodeitem,ConceptSchemaItem conceptSchemaItem){
        String lineItem="";
         if(conceptSchemaItem.conceptAction.equals("")){
            lineItem=nodeitem.Name + " " + conceptSchemaItem.relationName + " not " + conceptSchemaItem.conceptName;
         }
         
          if(!conceptSchemaItem.conceptAction.equals("")){
            lineItem=nodeitem.Name + " "  + conceptSchemaItem.relationName + " not "  + conceptSchemaItem.conceptAction + "  " +   conceptSchemaItem.conceptName;
         }
        return lineItem;
    }
    
    
     
     protected List<String> IncorrectSummary(NodeItem nodeitem,ConceptSchemaItem conceptSchemaItem){
        List<String> lineItems=new ArrayList();
        List<NodeItem> items= new ArrayList();
        for(NodeItem n: this.ParentNode.ListChildren()){
               if(n.Identity==nodeitem.Identity) continue;
               items.add(n);
        }
       
      
        String lineItem="";
    
          for(NodeItem p : items){
              for(ConceptSchema  cs:p.ListConceptSchemas()){
             ConceptSchemaItem   item=    cs.Description();
             if(item.conceptAction.equals("")){
                lineItem=nodeitem.Name + " " + item.relationName + " " + item.conceptName;
             }
         
            if(!conceptSchemaItem.conceptAction.equals("")){
              lineItem=nodeitem.Name + " "  + item.relationName + " "  + item.conceptAction + " " +   item.conceptName;
               }
            }
           if(!lineItems.contains(lineItem));
               lineItems.add(lineItem);
        
          }
        return lineItems;
    }
     
     protected List<String> NegativeIncorrectSummary(NodeItem nodeitem,ConceptSchemaItem conceptSchemaItem){
       List<String> lineItems=new ArrayList();
        List<NodeItem> items= new ArrayList();
        for(NodeItem n: this.ParentNode.ListChildren()){
               if(n.Identity==nodeitem.Identity) continue;
               items.add(n);
        }
       
      
        String lineItem="";
    
          for(NodeItem p : items){
              for(ConceptSchema  cs:p.ListConceptSchemas()){
             ConceptSchemaItem   item=    cs.Description();
             if(item.conceptAction.equals("")){
                lineItem=nodeitem.Name + " " + item.relationName + " not " + item.conceptName;
             }
         
            if(!conceptSchemaItem.conceptAction.equals("")){
              lineItem=nodeitem.Name + " "  + item.relationName + " not "  + item.conceptAction + "  " +   item.conceptName;
               }
            }
           if(!lineItems.contains(lineItem));
               lineItems.add(lineItem);
        
          }
        return lineItems;
    }
}

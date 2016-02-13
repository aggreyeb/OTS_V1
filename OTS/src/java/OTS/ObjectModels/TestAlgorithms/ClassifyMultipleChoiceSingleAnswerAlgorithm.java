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
import OTS.ObjectModels.TestGenerationInput;
import OTS.ObjectModels.TestItemGenerationOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Eb
 */
public class ClassifyMultipleChoiceSingleAnswerAlgorithm extends Algorithm{
    String invalidText="Concept schema is not valid. Test item generated will not be saved";
    Boolean HasErrors=false;
    public ClassifyMultipleChoiceSingleAnswerAlgorithm(DataSource dataSource, String name) {
        super(dataSource, name);
        this.Name=name;
    }
    @Override
    protected TestItemGenerationOutput BuildOuput(NodeItem node, TestGenerationInput input) {
         List<TestItemGenerationOutput> questions=new ArrayList();
         TestItemGenerationOutput output = new TestItemGenerationOutput();
         
          output.Text=this.ConstructQuestion(node);
          if(!this.HasErrors){
              
           List<String> answers=this.ListAnswers(node);
           for(String p:answers){
                output.LineItems.add(p);
           }
          output.LineItems.add("None of the above");
         }
         else{
             output.HasErrors=true;
             output.Text=invalidText;
             input.InvalidOutput=true;
             input.InvalidOutputText=invalidText;
         }
          this.HasErrors=false;
           return output;
    }

    @Override
    protected TestItemGenerationOutput BuildOuput(Node node, TestGenerationInput input) {
        TestItemGenerationOutput output = new TestItemGenerationOutput();
        input.ItemType=1; //Force True/False 
        StringBuilder  sb= new StringBuilder();
        sb.append("A student observed that an object has ");
        String children="";
        int count=node.ListChildren().size();
        int counter=0;
        Collection<NodeItem>  nodeItems= node.ListChildren();
        for(NodeItem a:nodeItems){
             if(counter==count-1){
                 children+= "and " +  a.Name.toLowerCase() ;
             }
             else{
                children+=a.Name.toLowerCase() + ","; 
             }
            
            counter+=1;
        }
        
        if(children.endsWith(",")){
            children=children.substring(0,children.length()-1);
        }
        
        if(children.equals("")){
            children="[Parent Node has no child nodes !!  Invalid sentence]";
            this.HasErrors=true;
            input.InvalidOutput=true;
            input.InvalidOutputText=invalidText;
        }
        
        sb.append(children);
        sb.append(" . ");
        sb.append("The object is most likely to be " + node.Name.toLowerCase());
        sb.append(".");
       // output.Text=sb.toString();
        output.LineItems.add("True  or False");
        output.Text=sb.toString();
        if(this.HasErrors){
          
            output.Text=invalidText;
            input.InvalidOutput=true;
            input.InvalidOutputText=invalidText;
        }
        this.HasErrors=false;
        return output;
    }
    
    
      protected String ConstructQuestion(NodeItem nodeItem){
       
       List<ConceptSchema> ConceptSchemas=   nodeItem.ListConceptSchemas();
        StringBuilder  sb= new StringBuilder();
        sb.append("An object ");
        String text=this.ContactIsRalationName(ConceptSchemas);
        if(!text.equals("Invalid concept schema")){
          sb.append(text);
         }
        else{
           this.HasErrors=true; 
        }
        sb.append(" which " );
        text=this.ConcatCanRalationName(ConceptSchemas);
        if(!text.equals("Invalid concept schema")){
          sb.append(text);
         }
        else{
           this.HasErrors=true; 
        }
        sb.append(". ");
        sb.append("What is likely to be the object?");
        if(!this.HasErrors){
            return sb.toString();
        }
        else{
            return invalidText;
        }
       
    }
      
     
      
     protected List<String> ListAnswers(NodeItem  nodeItem){
          List<NodeItem> Items=this.ExcludeNode(nodeItem);
          List<String> answers= new ArrayList();
              for(NodeItem n: Items){
              answers.add(n.Name);
            }
           answers.add(nodeItem.Name);
          return answers;
    }
     
    
     
     protected  String ContactIsRalationName(List<ConceptSchema> conceptSchemas){
         List<ConceptSchema> items= new ArrayList();
         for(ConceptSchema s:conceptSchemas){
             if(s.Description().relationName.toLowerCase().equals("is")){
                 items.add(s);
             }
         }
         
          StringBuilder  sb= new StringBuilder();
         if(items.size()>0){
         for(ConceptSchema s:items){
             if(s.Description().relationName.equals("")){
                 s.Description().relationName="[RelationName !! is empty **** Invalid sentence]";
                 this.HasErrors=true;
             }
              if(s.Description().conceptName.equals("") ){
                s.Description().conceptName ="[ConceptName !! is empty **** Invalid sentence]";
                 this.HasErrors=true;
             }
             sb.append(s.Description().relationName + " a " + s.Description().conceptName  + ",");
         }
         }
         else{
             sb.append("Invalid concept schema");
         }
         if(sb.toString().endsWith(",")){
                return sb.toString().substring(0,sb.toString().length()-1);
         }
         return sb.toString();
     }
     
     protected  String ConcatCanRalationName(List<ConceptSchema> conceptSchemas){
         List<ConceptSchema> items= new ArrayList();
         for(ConceptSchema s:conceptSchemas){
             if(s.Description().relationName.toLowerCase().equals("can")){
                 items.add(s);
             }
         }
          StringBuilder  sb= new StringBuilder();
          if(items.size()>0){
          sb.append(" can ");
         for(ConceptSchema s:items){
             if(s.Description().conceptAction.equals("") ){
                s.Description().conceptAction ="[ConceptAction !! is empty **** Invalid sentence]";
             }
              if(s.Description().conceptName.equals("") ){
                s.Description().conceptName ="[ConceptName !! is empty **** Invalid sentence]";
             }
             sb.append(s.Description().conceptAction + " " + s.Description().conceptName +",");
         }
          }
          else{
             sb.append("Invalid concept schema");
          }
         if(sb.toString().endsWith(",")){
                return sb.toString().substring(0,sb.toString().length()-1);
         }
         return sb.toString();
         
     }
     
     
    
}

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

    public ClassifyMultipleChoiceSingleAnswerAlgorithm(DataSource dataSource, String name) {
        super(dataSource, name);
        this.Name=name;
    }
    @Override
    protected TestItemGenerationOutput BuildOuput(NodeItem node, TestGenerationInput input) {
         List<TestItemGenerationOutput> questions=new ArrayList();
         TestItemGenerationOutput output = new TestItemGenerationOutput();
          output.Text=this.ConstructQuestion(node);
           List<String> answers=this.ListAnswers(node);
           for(String p:answers){
                output.LineItems.add(p);
           }
          output.LineItems.add("None of the above");
        
           return output;
    }

    @Override
    protected TestItemGenerationOutput BuildOuput(Node node, TestGenerationInput input) {
        TestItemGenerationOutput output = new TestItemGenerationOutput();
        input.ItemType=1; //Force True/False 
        StringBuilder  sb= new StringBuilder();
        sb.append("A student observed that a living thing has ");
        String children="";
        int count=node.ListChildren().size();
        int counter=0;
        Collection<NodeItem>  nodeItems= node.ListChildren();
        for(NodeItem a:nodeItems){
             if(counter==count-1){
                 children+= " and " +  a.Name ;
             }
             else{
                children+=a.Name + ","; 
             }
            
            counter+=1;
        }
        
        if(children.endsWith(",")){
            children=children.substring(0,children.length()-1);
        }
        
        if(children.equals("")){
            children="[Parent Node has no child nodes !!  Invalid sentence]";
        }
        
        sb.append(children);
        sb.append(" . ");
        sb.append("The living thing is most likely to be " + node.Name);
       // output.Text=sb.toString();
        output.LineItems.add(sb.toString());
      
        return output;
    }
    
    
      protected String ConstructQuestion(NodeItem nodeItem){
      
       List<ConceptSchema> ConceptSchemas=   nodeItem.ListConceptSchemas();
        StringBuilder  sb= new StringBuilder();
        sb.append("A living thing ");
        sb.append(this.ContactIsRalationName(ConceptSchemas));
        sb.append(" which " );
        sb.append(this.ConcatCanRalationName(ConceptSchemas));
        sb.append(". ");
        sb.append("The living thing is likely to be");
       return sb.toString();
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
       
         for(ConceptSchema s:items){
             if(s.Description().relationName.equals("")){
                 s.Description().relationName="[RelationName !! is empty **** Invalid sentence]";
             }
              if(s.Description().conceptName.equals("") ){
                s.Description().conceptName ="[ConceptName !! is empty **** Invalid sentence]";
             }
             sb.append(s.Description().relationName + " a " + s.Description().conceptName  + ",");
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
         if(sb.toString().endsWith(",")){
                return sb.toString().substring(0,sb.toString().length()-1);
         }
         return sb.toString();
         
     }
     
     
    
}

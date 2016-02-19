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
    String invalidText="<p style=\"color:Red;\">Concept schema is not valid.Please complete all the concept schema and try again. Test item generated will not be saved</p>";
    Boolean HasErrors=false;
    public ClassifyMultipleChoiceSingleAnswerAlgorithm(DataSource dataSource, String name) {
        super(dataSource, name);
        this.Name=name;
    }
    @Override
    protected TestItemGenerationOutput BuildOuput(NodeItem node, TestGenerationInput input) {
         List<TestItemGenerationOutput> questions=new ArrayList();
         TestItemGenerationOutput output = new TestItemGenerationOutput();
         
         // output.Text=this.ConstructQuestion(node);
           output.Text=this.BuildTestQuestion(node);
          if(!this.HasErrors &  output.Text.length()>0){
              
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
    
    protected String BuildTestQuestion(NodeItem nodeItem){
        List<ConceptSchema> ConceptSchemas=   nodeItem.ListConceptSchemas();
        String questionText="";
    if(ConceptSchemas.size()>0)
    {
            
        StringBuilder  sb= new StringBuilder();
        List<ConceptSchema> isRelationList=new ArrayList();
        List<ConceptSchema> hasRelationList=new ArrayList();
        List<ConceptSchema> canRelationList=new ArrayList();
        List<ConceptSchema> unknownRelationList=new ArrayList();
         for(ConceptSchema s:ConceptSchemas){
            if(s.Description().relationName.equals("is")){
                isRelationList.add(s);
            }
            else if(s.Description().relationName.equals("has")){
                   hasRelationList.add(s);
            }
            else if(s.Description().relationName.equals("can")){
           
               canRelationList.add(s);
            }
            else{
                unknownRelationList.add(s);
            }
        }
        
        if(isRelationList.size()>0 & hasRelationList.size()>0 & canRelationList.size()>0){
            
         String isText=this.BuildIsRelationText(isRelationList);
         String hasText=this.BuildHasRelationText(hasRelationList);
         String canText=this.BuildCanRelationText(canRelationList);
         
         sb.append("An object ");
         sb.append(isText);
         sb.append(" which ");
         sb.append(hasText);
         sb.append(" and ");
         sb.append(canText);
         sb.append(".");
         sb.append("What is likely to be the object?");
         questionText =sb.toString();
          return questionText;
             
        }
        else if(isRelationList.size()>0 & canRelationList.size()>0 ){
          
         String isText=this.BuildIsRelationText(isRelationList);
        // String hasText=this.BuildHasRelationText(hasRelationList);
         String canText=this.BuildCanRelationText(canRelationList);
         
         sb.append("An object ");
         sb.append(isText);
         if(!canText.equals("")){
             sb.append(" and ");
              sb.append(" ");
             sb.append(canText);
         }
            sb.append(".");
            sb.append("What is likely to be the object?");
            questionText=sb.toString();
            return questionText;
         }
        else if(isRelationList.size()==0 &  canRelationList.size()==0 & hasRelationList.size()>0 ){
           
            sb= new StringBuilder();
            questionText =BuildHasRelationText(hasRelationList);
            sb.append("An object ");
            sb.append(questionText);
            sb.append(".");
            sb.append("What is likely to be the object?");
          questionText=sb.toString();
        }
        else{
            //defualt
            this.HasErrors=true;
        }
         
     }
     else{
             questionText="";
             this.HasErrors=true;
      }//End Concept Schema >0
        return questionText; 
    }
    
    protected String BuildIsRelationText(List<ConceptSchema> conceptSchemas){
         //Concat is Relation
        StringBuilder isConcat=new  StringBuilder();
        String isConcatText="";
         int isCounter=0;
          for(ConceptSchema s:conceptSchemas){
               
             if(s.Description().relationName.equals("")){
               
                 this.HasErrors=true;
             }
              if(s.Description().conceptName.equals("") ){
               
                 this.HasErrors=true;
             }
             if(isCounter>0){
                 isConcat.append(s.Description().conceptName  + ",") ;
             }
             else{
              isConcat.append(s.Description().relationName + " a " + s.Description().conceptName  + ",");
             }
           
             isCounter+=1;
         }  
         if(isConcat.toString().endsWith(",")){
                isConcatText= isConcat.toString().substring(0,isConcat.toString().length()-1);
         }
         else{
               isConcatText= isConcat.toString();
         }
       
         return  isConcatText;
    }
    
    protected String BuildHasRelationText(List<ConceptSchema> conceptSchemas){
         StringBuilder hasConcat=new  StringBuilder();
         String isConcatText="";
          int hasCounter=0;
          for(ConceptSchema a:conceptSchemas){
              if(a.Description().attributeValue.equals("")){
                  this.HasErrors=true;
              }
              if(a.Description().attributeValue.equals(""))
              {
                   this.HasErrors=true;
              }
              if(hasCounter>0){
                  if(hasCounter==conceptSchemas.size()-1){
                     
                      hasConcat.append(" and " +  a.Description().attributeValue.toLowerCase() + "  " + a.Description().attributeName.toLowerCase()  ); 
                  }
                  else{
                    hasConcat.append(a.Description().attributeValue.toLowerCase() + " " + a.Description().attributeName.toLowerCase() + ","); 
                  }
                  
              
              }
              else{
                hasConcat.append(a.Description().relationName + " " + a.Description().attributeValue + " " + a.Description().attributeName + ",");  

              }
                hasCounter+=1;          
          }
           if(hasConcat.toString().endsWith(",")){
                isConcatText= hasConcat.toString().substring(0,hasConcat.toString().length()-1);
           }
           else{
                isConcatText= hasConcat.toString();
           }
        
         return  isConcatText;
    }
    
       protected String BuildCanRelationText(List<ConceptSchema> conceptSchemas){
         StringBuilder hasConcat=new  StringBuilder();
         String canConcatText="";
          int canCounter=0;
          for(ConceptSchema a:conceptSchemas){
              if(a.Description().relationName.equals("")){
                  this.HasErrors=true;
              }
              if(a.Description().conceptName.equals(""))
              {
                   this.HasErrors=true;
              }
              if(a.Description().conceptAction.equals(""))
              {
                   this.HasErrors=true;
              }
              if(canCounter>0){
                    if(canCounter==conceptSchemas.size()-1){
                      hasConcat.append(" and " + a.Description().conceptAction + " " + a.Description().conceptName ); 
                    } 
                    else{
                        hasConcat.append(a.Description().conceptAction + " " + a.Description().conceptName + " " ); 
                    }
              }
              else{
                hasConcat.append(a.Description().relationName + " " + a.Description().conceptAction+ " " + a.Description().conceptName );  

              }
              canCounter+=1;            
          }
           if(hasConcat.toString().endsWith(",")){
                canConcatText= hasConcat.toString().substring(0,hasConcat.toString().length()-1);
         }
         canConcatText= hasConcat.toString();
         return  canConcatText;
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

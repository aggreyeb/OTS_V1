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
import java.util.List;

/**
 *
 * @author aggreeb
 */
public class ClassifyMultipleChoiceAlgorithm extends Algorithm{
  String invalidText="Concept schema is not valid. Test item generated will not be saved";
    public ClassifyMultipleChoiceAlgorithm(DataSource dataSource, String name) {
        super(dataSource, name);
        this.Name=name;
    }

    @Override
    protected TestItemGenerationOutput BuildOuput(NodeItem node, TestGenerationInput input) {
      
         
          List<TestItemGenerationOutput> questions=new ArrayList();
         TestItemGenerationOutput output = new TestItemGenerationOutput();
          String  text= this.ConstructQuestion(node);
          if(text.trim().length()>0){
          output.Text=this.ConstructQuestion(node);
           List<String> answers=this.ListAnswers(node);
           for(String p:answers){
                output.LineItems.add(p);
           }
          output.LineItems.add("None of the above");
          }
          else{
              input.InvalidOutput=true;
              input.InvalidOutputText=invalidText;
          }
           return output;
    }

    protected Boolean ContainsOutput(TestItemGenerationOutput item, List<String> answers){
       Boolean found=false;
       for(String a:answers){
           if(item.Text.trim().equals(a)){
               found=true;
               break;
           }
       }
       return found;
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
    
    
    @Override
    protected TestItemGenerationOutput BuildOuput(Node node, TestGenerationInput input) {
        TestItemGenerationOutput output = new TestItemGenerationOutput();
       output.Text=node.Name;
       input.InvalidOutput=true;
       input.InvalidOutputText="This item generation is not applicable for the selected options";
     
       return output;
    }
    
   
    protected String ConstructQuestion(NodeItem nodeItem){
     String text="";
        List<ConceptSchema> ConceptSchemas=   nodeItem.ListConceptSchemas();
        for(ConceptSchema s:ConceptSchemas){
            if(s.Description().relationName.equals("is")){
               // text="Which of the following"  + " " + s.Description().relationName + " " + s.Description().conceptName;  
                text="Which of the following are"  +  " " + s.Description().conceptName;  
                break;
            }
        }
       return text;
    }
    
    
    protected void AddNodeLineItems(List<String> items, TestItemGenerationOutput output){
        for(String s: items){
            if(output.LineItems.contains(s)) continue;
            output.LineItems.add(s);
        }
    }
    
 
}

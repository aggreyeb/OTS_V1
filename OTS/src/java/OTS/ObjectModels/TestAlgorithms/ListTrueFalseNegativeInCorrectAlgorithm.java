/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels.TestAlgorithms;

import OTS.DataModels.DataSource;
import OTS.DataModels.Node;
import OTS.DataModels.NodeItem;
import OTS.ObjectModels.ConceptRelationMap;
import OTS.ObjectModels.TestGenerationInput;
import OTS.ObjectModels.TestItemGenerationOutput;
import OTS.RelationType;
import java.util.List;

/**
 *
 * @author MEA
 */
public class ListTrueFalseNegativeInCorrectAlgorithm extends TrueFalseAlgorithm{

    public ListTrueFalseNegativeInCorrectAlgorithm(DataSource dataSource, String name, ConceptRelationMap conceptRelationMap) {
        super(dataSource, name, conceptRelationMap);
         this.Name=name;
    }
   
    
     @Override
    protected TestItemGenerationOutput BuildOuput(NodeItem node,
        TestGenerationInput input){
        TestItemGenerationOutput output= new TestItemGenerationOutput();
        output.Text=node.Name;
        String lineItem ="";
         List<NodeItem> list=this.ExcludeNode(node);
         List<NodeItem> listChildren=this.NodeChildren(list);
        if(input.IncludeSubTrees){
             for(NodeItem item:listChildren)
             {
                if(item.RelationType==RelationType.PartOf){
                  if(!node.Name.equals(item.Name)){
                       lineItem =node.Name + " " +  conceptRelationMap.PartOfMap + " " + item.Name.toLowerCase();
                        output.LineItems.add(lineItem);
                   }
                }
                else{
                     if(!node.Name.equals(item.Name)){
                        lineItem=item.Name + " " + conceptRelationMap.TypeOfMap + " " + node.Name.toLowerCase();
                        output.LineItems.add(lineItem);
                     }
                  
                }
            }
        }
        return output;
    }
   
  @Override
    protected TestItemGenerationOutput BuildOuput(Node node,
        TestGenerationInput input){
        TestItemGenerationOutput output= new TestItemGenerationOutput();
        output.Text=node.Name;
        String lineItem ="";
        List<NodeItem> list=this.ExcludeNode(node);
        List<NodeItem> listChildren=this.NodeChildren(list);
        if(input.IncludeSubTrees){
             for(NodeItem item:listChildren)
             {
                if(item.RelationType==RelationType.PartOf){
                   
                    lineItem =node.Name + " " + conceptRelationMap.PartOfMap + " " +  item.Name;
                     output.LineItems.add(lineItem);
                }
                else{
                   lineItem=item.Name + " " + conceptRelationMap.TypeOfMap  + node.Name;
                   output.LineItems.add(lineItem);
                }
            }
        }
        return output;
    }
}

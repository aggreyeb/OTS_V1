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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aggreeb
 */
public abstract class TrueFalseAlgorithm  extends Algorithm{
  protected ConceptRelationMap conceptRelationMap;

    public TrueFalseAlgorithm(DataSource dataSource, String name,ConceptRelationMap conceptRelationMap) {
        super(dataSource, name);
        this.conceptRelationMap=conceptRelationMap;
    }
   
    
  @Override
    protected TestItemGenerationOutput BuildOuput(NodeItem node,
        TestGenerationInput input){
        TestItemGenerationOutput output= new TestItemGenerationOutput();
        output.Text=node.Name;
        String lineItem ="";
        try{
        if(input.IncludeSubTrees){
            
            for(NodeItem item:node.ListItems())
             {
                if(item.RelationType==RelationType.PartOf){
                     lineItem =node.Name + " " +  conceptRelationMap.PartOfMap + " " + item.Name.toLowerCase();
                     if(!output.LineItems.contains(lineItem))
                     output.LineItems.add(lineItem);
                }
                else{
                   lineItem=item.Name + " " + conceptRelationMap.TypeOfMap + " " + node.Name.toLowerCase();
                  if(!output.LineItems.contains(lineItem))
                    output.LineItems.add(lineItem);
                }
            }
        }
        }
        catch(Throwable ex){
            
        }
        return output;
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
                if(item.RelationType==RelationType.PartOf){
                   
                    lineItem =node.Name + " " + conceptRelationMap.PartOfMap + " " +  item.Name.toLowerCase();
                     if(!output.LineItems.contains(lineItem))
                       output.LineItems.add(lineItem);
                }
                else{
                   lineItem=item.Name + " " + conceptRelationMap.TypeOfMap  + node.Name.toLowerCase();
                  if(!output.LineItems.contains(lineItem))
                   output.LineItems.add(lineItem);
                }
            }
        }
        return output;
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
    
    /*
     protected List<NodeItem> ExcludeNode(Node node){
         List<NodeItem> list= new ArrayList();
        for(NodeItem item :this.ParentNode.ListChildren() ){
            if(item.RootId!=node.Id){
                list.add(item);
            }
        }
      return list;
    }
     */
    protected List<NodeItem> NodeChildren(List<NodeItem> nodes){
        List<NodeItem>  list= new ArrayList();
        for(NodeItem  item:nodes){
             if(item==null || item.ListItems()==null) continue;
            for(NodeItem n:item.ListItems()){
                 list.add(n);
             }
        }
        return list;
    }
}

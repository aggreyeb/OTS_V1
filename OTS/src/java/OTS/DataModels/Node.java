/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.DataModels;

import OTS.ISerializable;
import OTS.ObjectModels.CourseTestKnowledgeMapDescription;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author MEA
 */
@XStreamAlias("data")
public class Node implements ISerializable {
   @XStreamAlias("id")
   public int Id;
    @XStreamAlias("identity")
   public String Identity="-";
    @XStreamAlias("label")
   public String Name;
   @XStreamAlias("description")
   public String Description;
   

   private NodeItemList nodes;
   
    public Node(Knowledgemap km) {
        this.Id=km.getKnowledgeMapId();
        this.Name=km.getName();
        this.nodes= new NodeItemList();
        this.ChangeDescription(km.getDescription());
        String concepts=km.getConcepts();
        if(concepts !=null && concepts.trim().length()>0 && !concepts.equals("<children/>"))
        {
             if(this.nodes==null)
              this.nodes=new NodeItemList();  
          this.LoadItems(km.getConcepts());
        }
        else{
             this.nodes=new NodeItemList();  
        }    
    }
   
    public Node(KnowledgeMapDescription desc){
         this.Id=desc.KnowledgeMapId;
         this.Name=desc.Name;
         this.ChangeDescription(desc.Description);
          String concepts=desc.Concepts;
        if(concepts !=null && concepts.trim().length()>0 && !concepts.equals("<children/>"))
        {
             if(this.nodes==null)
              this.nodes=new NodeItemList();  
             this.LoadItems(desc.Concepts);
        }
        else{
               this.nodes=new NodeItemList();  
        }
     }
    
    
    public Node(CourseTestKnowledgeMapDescription desc){
         this.Id=desc.KnowledgeMapId;
         this.Name=desc.Name;
         this.ChangeDescription(desc.Description);
          String concepts=desc.Concepts;
        if(concepts !=null && concepts.trim().length()>0 && !concepts.equals("<children/>"))
        {
             if(this.nodes==null)
              this.nodes=new NodeItemList();  
             this.LoadItems(desc.Concepts);
        }
        else{
               this.nodes=new NodeItemList();  
        }
     }
  
    public void ChangeDescription(String description){
       this.Description=description;
    }
   
   public void AddNode(NodeItem node){
          node.UpdateRootId(Id);
          node.UpdatePatrentId(Id);
         this.nodes.Add(node);
   }
  
   public void Remove(NodeItem item){
       
         this.nodes.Remove(item);
   }

   public NodeItem Find(String identity){
      
          NodeItem item=  this.nodes.Find(identity);
          return item;
   
   }
   
   public Boolean HasChildren(){
      return this.nodes.Count() >0;
   }
   
   public  Collection<NodeItem> ListChildren(){
       Collection<NodeItem> items= new ArrayList();
        this.nodes.ListNodeItems(items);
        return items;
   }
   
   public void Update(int newId){
     
     this.nodes.Update(newId);
   }
 
   
  
    @Override
    public String ToJson() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.setMode(XStream.NO_REFERENCES);
      //  xStream.alias("data", Node.class);
        xStream.processAnnotations(Node.class);
        String json = xStream.toXML(this);
         return json;
    }

    @Override
    public String ToXml() {
        XStream xStream = new XStream(new DomDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias("data", Node.class);
        xStream.processAnnotations(Node.class);
        String xml = xStream.toXML(this);
         return xml;
    }
    
    private static Node FromXml(String xmlString){
      
        XStream xStream = new XStream(new DomDriver());
        xStream.setMode(XStream.NO_REFERENCES);
      //  xStream.alias("data", Node.class);
        xStream.processAnnotations(Node.class);
         Node  node = (Node)xStream.fromXML(xmlString);
          
         return node;
    }
    
     private Node FromJson(String jsonString){
      
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        //xStream.alias("data", Node.class);
        xStream.processAnnotations(Node.class);
        Node  node=(Node) xStream.fromXML(jsonString);
       
         return node;
    }

    public void ClearNodes() {
      this.nodes.Clear();
    }

    private void LoadItems(String concepts) {
         XStream xStream = new XStream(new DomDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias("child", NodeItem.class);
        xStream.processAnnotations(NodeItem.class);
        xStream.alias("children", NodeItemList.class);
        xStream.addImplicitCollection(NodeItemList.class, "list");
        NodeItemList nodeItemList = (NodeItemList)xStream.fromXML(concepts);
        this.nodes.AddRange(nodeItemList);
    }
    
    public String NodesToXml(){
      
        XStream xStream = new XStream(new DomDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias("child", NodeItem.class);
        xStream.processAnnotations(NodeItem.class);
        xStream.alias("children", NodeItemList.class);
        xStream.addImplicitCollection(NodeItemList.class, "list");
        xStream.omitField(Node.class, "nodes");
        String xml = xStream.toXML(this.nodes);
        return xml;
    
    }
    
    
     public String NodesToJson(){
      
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias("child", NodeItem.class);
        xStream.processAnnotations(NodeItem.class);
        xStream.alias("children", NodeItemList.class);
        xStream.addImplicitCollection(NodeItemList.class, "list");
      
      
        String xml = xStream.toXML(this.nodes);
        return xml;
    
    }
    public String ToJsonDiscription() {
    
       String json="";
        NodeDescription d= new NodeDescription();
         d.Id=this.Id;
         d.label=this.Name;
         d.Identity=this.Identity;
         d.Description=this.Description;
         d.Selected=false;
      
         List<NodeItem> list = new ArrayList();
         this.nodes.ListNodeItems(list);
         d.Add(list);
       
         Gson g= new Gson();
        return   g.toJson(d);
    }
    
   
}

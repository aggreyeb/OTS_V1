/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.DataModels;

import OTS.RelationType;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MEA
 */

 @XStreamAlias("child")
public class NodeItem {
   @XStreamAlias("id")
   @SerializedName("id")
   private int Id=0;
   @SerializedName("rootid")
   @XStreamAlias("rootid")
   public int RootId;
   @SerializedName("identity")
   @XStreamAlias("identity")
   public String Identity;
   @SerializedName("parentid")
   @XStreamAlias("parentid")
   public int  ParentId;
   @SerializedName("parentidentity")
   @XStreamAlias("parentidentity")
   public String ParentIdentity;
   @XStreamAlias("label")
   @SerializedName("label")
   public String Name;
   @SerializedName("children")
   @XStreamImplicit(itemFieldName="children")
   private List<NodeItem> children ;
   @SerializedName("relationtype")
   @XStreamAlias("relationtype")
   public RelationType RelationType;
   
   @XStreamAlias("conceptschemas")
   @SerializedName("conceptschemas")
   private List<ConceptSchema> conceptSchemas;
  
    Boolean HasChildren(){
      if(this.children==null) return false;
      return this.children.size()>0;
    }

     public NodeItem(int parentId,RelationType relationType, String name, String identity ) {
        this.ParentId=parentId;
        this.RelationType=relationType;
        this.Name=name;
        this.Identity=identity;
        this.children=new ArrayList();
        this.Id= new Object().hashCode();
        conceptSchemas= new ArrayList();
    }
    public NodeItem(String parentIdentity,RelationType relationType, String name, String identity ) {
        this.ParentIdentity=parentIdentity;
        this.RelationType=relationType;
        this.Name=name;
        this.Identity=identity;
        this.children=new ArrayList();
        this.Id= new Object().hashCode();
         conceptSchemas= new ArrayList();
    }
   
    public List<ConceptSchema> ListConceptSchemas(){
        return conceptSchemas;
    }
    
    
    public List<NodeItem> ListItems(){
        return this.children;
    }
    
    
   public void Add(NodeItem item){
           if(this.children==null)
               this.children=new ArrayList();
           item.UpdateRootId(RootId);
           item.UpdatePatrentId(this.Id);
           item.UpdateParentIdentity(Identity);
           this.children.add(item);
   }
    
   public void Remove(NodeItem item){
        if(this.children!=null)
       this.children.remove(item);
   
   }
   
   public void Clear(){
       if(this.children!=null)
       this.children.clear();
   }

   public void UpdatePatrentId(int parentId){
      this.ParentId=parentId;
      if(this.HasChildren()){
         for(NodeItem a: this.children){
             a.UpdatePatrentId(this.ParentId);
         }
      }
   }
   public void UpdateRootId(int rootId){
     this.RootId=rootId;
      if(this.HasChildren()){
         for(NodeItem a: this.children){
             a.UpdateRootId(rootId);
         }
      }
    
   }
   
   public void UpdateParentIdentity(String identity){
      this.ParentIdentity=identity;
   }
   public  NodeItem Find(String identity) {
         if(this.children==null) return null;
        return this.FindNode(this.children,identity);
    }
   
  
   
   public NodeItem FindNode(List<NodeItem> nodes, String identity) {
	NodeItem found = null;
	for (NodeItem node : nodes) {
		if (node.Identity.equals(identity)) { return node; }
	        if(node.HasChildren()) 
                {
                found = FindNode(node.children, identity);
		if (found!=null) { break; }
                }
	}
	return found;
    }

  public void AddConceptSchema(ConceptSchema conceptSchema){
      this.conceptSchemas.add(conceptSchema);
  }
  
  public void RemoveConceptSchema(ConceptSchema conceptSchema){
     this.conceptSchemas.remove(conceptSchema);
  }
  
  public void InsertConceptSchema(ConceptSchema conceptSchema){
      int index= this.conceptSchemas.indexOf(conceptSchema);
      if (index<0) return;
      this.conceptSchemas.remove(index);
      this.conceptSchemas.add(index, conceptSchema);
  }
  
  public ConceptSchema FindConceptSchema(String id){
       ConceptSchema found=null;
      for(ConceptSchema s:this.conceptSchemas){
           if(s.HasId(id)){
              found =s;
             break;
           }
       }
      return found;
   }
  
   public String ConceptSchemaToJson(){
       Gson g= new Gson();
       return g.toJson(this.conceptSchemas);
   }
}

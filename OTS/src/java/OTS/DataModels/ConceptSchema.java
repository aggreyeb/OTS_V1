/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.DataModels;

import OTS.ConceptSchemaDescription;
import OTS.ObjectModels.ConceptSchemaItem;
import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author MEA
 */
@XStreamAlias("conceptschema")
public class ConceptSchema {
    @XStreamAlias("id")
    @SerializedName("id")
    private String Id;
    @XStreamAlias("rootid")
    @SerializedName("rootid")
    private int rootId;
    @XStreamAlias("parentidentity")
    @SerializedName("parentidentity")
    private String parentIdentity;
    @XStreamAlias("relationname")
    @SerializedName("relationname")
    private String relationName="-";
    @XStreamAlias("conceptname")
    @SerializedName("conceptname")
    private String conceptName="-";
    @XStreamAlias("conceptaction")
    @SerializedName("conceptaction") 
    private String conceptAction="-";
    @XStreamAlias("attributename")
    @SerializedName("attributename") 
    private String attributeName="-";
    @XStreamAlias("attributevalue")
    @SerializedName("attributevalue") 
    private String attributeValue ="-";

    public ConceptSchema(ConceptSchemaDescription desc) {
        this.Id=desc.Id; 
        this.rootId = desc.RootId;
        this.parentIdentity = desc.ParentIdentity;
        this.relationName = desc.RelationName;
        this.attributeValue=desc.AttributeValue;
        this.conceptAction=desc.ConceptAction;
        this.attributeName=desc.AttributeName;
        this.conceptName=desc.ConceptName;
    }

   public void UpdateDescription(ConceptSchemaDescription desc){
        this.relationName = desc.RelationName;
        this.attributeValue=desc.AttributeValue;
        this.conceptAction=desc.ConceptAction;
        this.attributeName=desc.AttributeName;
        this.conceptName=desc.ConceptName;
   }
  
   
   public void AddTo(NodeItem item){
       item.AddConceptSchema(this);
   }
   
   public void RemoveFrom(NodeItem item){
      item.RemoveConceptSchema(this);
   }
   
   public void Update(NodeItem item){
         item.InsertConceptSchema(this);
   }

    public boolean HasId(String id) {
         if(this.Id==null){
            this.Id="";
         }
        return this.Id.equals(id);
    }
    
    public ConceptSchemaItem Description(){
        ConceptSchemaItem item = new ConceptSchemaItem();
        item.attributeName=this.attributeName;
        item.attributeValue=this.attributeValue;
        item.conceptAction=this.conceptAction;
        item.conceptName=this.conceptName;
        item.relationName=this.relationName;
        return item;
    }
    
  
   
}

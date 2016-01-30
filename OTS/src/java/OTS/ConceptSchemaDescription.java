/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS;

import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author MEA
 */
public class ConceptSchemaDescription {
    @XStreamAlias("id")
    @SerializedName("id")
    public String Id;
    @XStreamAlias("rootid")
    @SerializedName("rootid")
    public int RootId;
    @XStreamAlias("parentidentity")
    @SerializedName("parentidentity")
    public String ParentIdentity;
    @XStreamAlias("relationname")
    @SerializedName("relationname")
    public String RelationName="-";
    @XStreamAlias("conceptname")
    @SerializedName("conceptname")
    public String ConceptName="-";
    @XStreamAlias("conceptaction")
    @SerializedName("conceptaction") 
    public String ConceptAction="-";
    @XStreamAlias("attributename")
    @SerializedName("attributename") 
    public String AttributeName="-";
    @XStreamAlias("attributevalue")
    @SerializedName("attributevalue") 
    public String AttributeValue="-";

    public ConceptSchemaDescription(Identity identity) {
     
        this.Id=identity.ID.toString();
        if(this.Id ==null || this.Id.equals("")){
          this.Id=Identity.NewGiudIdentity().ID.toString();
        }
    }
    
     public void UpdateIdentity(Identity identity){
        this.Id=identity.ID.toString();
     }
}

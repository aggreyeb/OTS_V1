/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.ISerializable;
import OTS.Message;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author MEA
 */
public class KnowledgeMapResponse   implements ISerializable {
    @SerializedName("status")
    private int id;
    @SerializedName("status")
    private String status;
    @SerializedName("error")
    private String error;
    @SerializedName("knowledgemaplist")
    private String knowledgeMapList;
    @SerializedName("conceptschemalist")
    private String conceptSchemaList;
    @SerializedName("affectedconceptschema")
    private String affectedConceptSchema;
    @SerializedName("affectedKnowledgeMap") 
    private String affectedKnowledgeMap;

    public void UpdateId(int id){
        this.id=id;
    }
    public void UpdateStatus(String status){
      this.status=status;
    }
    
    public void UpdateError(String error){
        this.error=error;
    }
    public void AddKnowledgeMapList(String list){
          this.knowledgeMapList=list;
    }
    
    public void AddConceptSchemaList(String list){
          this.conceptSchemaList=list;
    }
    
    public void AddAffectedConceptSchema(String item){
         affectedConceptSchema=item;
    }
    
    public  void AddAffectedKnowledgeMap(String item){
         this.affectedKnowledgeMap=item;
    }
    @Override
    public String ToJson() {
        Gson g = new Gson();
        return  g.toJson(this);
    }

    @Override
    public String ToXml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}

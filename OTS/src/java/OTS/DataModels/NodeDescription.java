/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.DataModels;

import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author MEA
 */

public class NodeDescription {
    @SerializedName("id")
     public int Id;
    @SerializedName("label")
     public String label;
    @SerializedName("description")
     public String Description;
    @SerializedName("identity")
    public String Identity;
    @SerializedName("selected")
    public Boolean  Selected;
    
    @SerializedName("children")
    List<NodeItem> children;
    public  NodeDescription(){
      children=new ArrayList();
    }
    
    public void Add(Collection<NodeItem> item){
       children.addAll(item);
    }
    
    
   
}

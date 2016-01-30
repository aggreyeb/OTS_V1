/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.DataModels;

import OTS.ISerializable;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MEA
 */
@XStreamAlias("data")
public class NodeList implements ISerializable{
    
    List<Node> list;
    
    public NodeList(){
      list=new ArrayList<Node>();
    }
    public void Add(Node node){
        this.Add(node);
    }
    
    public void AddRange(List<Node> nodes){
       this.list.addAll(nodes);
    }
    
    public void Remove(Node node){
        this.Remove(node);
    }
    
    public Boolean HasItems(){
       return this.list.size()>0;
    }

    @Override
    public String ToJson() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.setMode(XStream.NO_REFERENCES);
      //  xStream.alias("child", NodeItem.class);
        xStream.processAnnotations(NodeItem.class);
        xStream.alias("children", NodeList.class);
        xStream.addImplicitCollection(NodeItemList.class, "list");
        String xml = xStream.toXML(this);
        return xml;
    }

    @Override
    public String ToXml() {
       XStream xStream = new XStream(new DomDriver());
        xStream.setMode(XStream.NO_REFERENCES);
       // xStream.alias("child", NodeItem.class);
        xStream.processAnnotations(NodeItem.class);
        xStream.alias("children", NodeList.class);
        xStream.addImplicitCollection(NodeItemList.class, "list");
        String xml = xStream.toXML(this);
        return xml;
    }
}

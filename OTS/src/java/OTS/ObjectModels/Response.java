/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.Message;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 *
 * @author MEA
 */
@XStreamAlias("response")
public class Response extends Message  {
    @XStreamAlias("id")
    private int id;
    @XStreamAlias("identity")
    private String identity;
    @XStreamAlias("status")
    private String status="-";
    @XStreamAlias("error")
    private String error="-";
    @XStreamAlias("content")
    private String content ="-";
    
   

    public Response( String status, String content) {
      
        this.status = status;
        this.content = content;
    }


    @Override
    public void UpdateError(String error) {
      this.error=error;
    }

    @Override
    public void ChangeStatus(String status) {
       this.status=status;
    }
    
    @Override
    public void ChangeContent(String content) {
       
       this.content=content;
    }

    @Override
    public String ToJson() {
     
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias("response", Response.class);
        String json = xStream.toXML(this);
         return json;
    }

    @Override
    public String ToXml() {
     
        XStream xStream = new XStream(new DomDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias("response", Response.class);
        String xml = xStream.toXML(this);
         return xml;
    }

  
    @Override
    public String toString(){
       return String.format("status=%1$s,content=%2$s,error=%3$s,id=%4$s",this.status,this.content,this.error,this.identity);
    }

   

    @Override
    public void UpdateID(int id) {
        this.id=id;
    }

    @Override
    public void UpdateIdentity(String identity) {
       this.identity=identity;
    }
   
}

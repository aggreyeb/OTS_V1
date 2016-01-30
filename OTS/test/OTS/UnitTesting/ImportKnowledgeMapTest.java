/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.UnitTesting;
import OTS.DataModels.MySqlDataSource;
import OTS.ObjectModels.ConceptNode;
import OTS.ObjectModels.ConceptNodeTransaction;
import OTS.ObjectModels.Response;
import OTS.ObjectModels.TransactionCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author MEA
 */
public class ImportKnowledgeMapTest {
    
    @Test
    public void SerializeArrayList(){
        
         String data="1,2,3,4";
         List<String> list= Arrays.asList(data.split(","));
         Assert.assertEquals(4,list.size());
    }

  @Test
  public void ImportKnowledgeMaps(){
       String data="85,86";
       List<String> list= Arrays.asList(data.split(","));
       int userId=1;
        Response response= new Response("","");
       ConceptNodeTransaction tx = new ConceptNodeTransaction(new MySqlDataSource());
       TransactionCallback cb= new TransactionCallback();
      tx.ImportKowledgeMap(userId, list, cb);
      cb.WriteDescription(response);
       System.out.print( response.ToJson());
  
  }
  
  
}

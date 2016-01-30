/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.UnitTesting;

import OTS.DataModels.MySqlDataSource;
import OTS.Message;
import OTS.ObjectModels.KnowledgeMaps;
import OTS.ObjectModels.Response;
import org.junit.Test;

/**
 *
 * @author MEA
 */
public class KnowledgeMapsTest {
    
    @Test
    public void ListKnowledgeMapByUserId(){
        Message message = new  Response("","");
        int userId=1;
        KnowledgeMaps kms= new KnowledgeMaps(new MySqlDataSource());
        kms.List(userId, message);
        System.out.print(message.ToJson());
        
    }
    
    @Test
    public void ImportKnowledgeMapListTest(){
    
        Message message = new  Response("","");
        int userId=1;
        KnowledgeMaps kms= new KnowledgeMaps(new MySqlDataSource());
        kms.ImportKnowledgeMapsList(userId, message);
        System.out.print(message.ToJson());
        
    }
    
    
     @Test
    public void ListAllKnowledgeMapList(){
    
        Message message = new  Response("","");
        KnowledgeMaps kms= new KnowledgeMaps(new MySqlDataSource());
        kms.ListAllIgnoreConceptSchema( message);
        System.out.print(message.ToJson());
        
    }
}

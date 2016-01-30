/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.UnitTesting;

import OTS.DataModels.MySqlDataSource;
import OTS.ObjectModels.QuestionManagement.Questions;
import OTS.ObjectModels.QuestionManagement.TestSheetItem;
import OTS.ObjectModels.Response;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Eb
 */

public class TestSheetTest {
    
    @Test
    public void ListTestSheetByTestId(){
        
         MySqlDataSource db=  new MySqlDataSource();
         Response message= new Response("","");
         Questions q= new Questions(db,message);
         int testid=1;
         List<TestSheetItem> items= q.ListTestSheet(testid);
         System.out.print(message.ToJson());
    }
}

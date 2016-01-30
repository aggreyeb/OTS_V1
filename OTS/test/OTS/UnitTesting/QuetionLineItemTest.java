/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.UnitTesting;

import OTS.ObjectModels.TestQuestionLineItem;
import com.google.gson.Gson;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author aggreeb
 */
public class QuetionLineItemTest {
    @Test
    public void DeserializeJsonArray(){
       String json="[{\"QuestionLineItemId\":2071,\"Text\":\"Plant has Root\",\"Question_id\":635,\"IsCorrect\":true},\n" +
            " {\"QuestionLineItemId\":2072,\"Text\":\"Plant has Steam\",\"Question_id\":635,\"IsCorrect\":true},\n" +
            " {\"QuestionLineItemId\":2073,\"Text\":\"Plant has Leaf\",\"Question_id\":635,\"IsCorrect\":true}\n" +
            "]";
        Gson g= new Gson();
         TestQuestionLineItem[] items=  g.fromJson(json, TestQuestionLineItem[].class);
            System.out.print(items.length);
    }
}

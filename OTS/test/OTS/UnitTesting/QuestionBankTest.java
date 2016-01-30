/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.UnitTesting;

import OTS.DataModels.MySqlDataSource;
import OTS.Message;
import OTS.ObjectModels.QuestionBankItem;
import OTS.ObjectModels.QuestionBanks;
import OTS.ObjectModels.QuestionManagement.QuestionItem;
import OTS.ObjectModels.QuestionManagement.Questions;
import OTS.ObjectModels.Response;
import com.google.gson.Gson;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author MEA
 */
public class QuestionBankTest {
    @Test
    public void ListQuestionsByTest(){
        
        int testId=1;
        Message message=  new Response("","");
        Questions qb= new Questions(new MySqlDataSource(), message);
        List<QuestionItem> items= qb.ListTestQuestionBank(testId);
        System.out.print(message.ToJson());
    }
    
    @Test
    public void Testing(){
       String s="[{\"Number\":1,\"QuestionUniqueId\":49,\"QuestionText\":\"Plant has root\",\"QuestionTypeId\":1,\"QuestionType\":\"TrueOrFalse\",\"Selected\":true,\"LineItems\":[\"True\",\"False\"]},{\"Number\":2,\"QuestionUniqueId\":50,\"QuestionText\":\"Plant has steam\",\"QuestionTypeId\":1,\"QuestionType\":\"TrueOrFalse\",\"Selected\":false,\"LineItems\":[\"True\",\"False\"]},{\"Number\":3,\"QuestionUniqueId\":51,\"QuestionText\":\"Plant has leaf\",\"QuestionTypeId\":1,\"QuestionType\":\"TrueOrFalse\",\"Selected\":false,\"LineItems\":[\"True\",\"False\"]},{\"Number\":4,\"QuestionUniqueId\":52,\"QuestionText\":\"Steam is vegetative organ\",\"QuestionTypeId\":1,\"QuestionType\":\"TrueOrFalse\",\"Selected\":false,\"LineItems\":[\"True\",\"False\"]},{\"Number\":5,\"QuestionUniqueId\":53,\"QuestionText\":\"Steam can  transport water\",\"QuestionTypeId\":1,\"QuestionType\":\"TrueOrFalse\",\"Selected\":false,\"LineItems\":[\"True\",\"False\"]},{\"Number\":6,\"QuestionUniqueId\":48,\"QuestionText\":\"Which of the following are vegetative organ\",\"QuestionTypeId\":3,\"QuestionType\":\"MultipleChoice-MultipleAnswers\",\"Selected\":false,\"IsMultipleOptions\":true,\"LineItems\":[\"Root\",\"Leaf\",\"Steam\",\"None of the above\"]}]";
       Gson g= new Gson();
      QuestionItem[] d= (QuestionItem[])g.fromJson(s, QuestionItem[].class);
        System.out.print(d.length);
    }
}

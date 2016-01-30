/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels.QuestionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MEA
 */
public class QuestionItem { 
   public int Number;
   public int QuestionUniqueId;
   public String QuestionText;
   public int QuestionTypeId;
   public String QuestionType;
   public Boolean Selected;
   public Boolean IsMultipleOptions;
   public float Mark=0F;
   public List<String> LineItems;
   public QuestionItem()
   {
       LineItems= new ArrayList();
       if(QuestionTypeId ==3)
       {
           IsMultipleOptions=true;
       }
   }
    public QuestionItem(int questionTypeId) {
       LineItems= new ArrayList();
       if(questionTypeId ==3)
       {
           IsMultipleOptions=true;
       }
    }
}

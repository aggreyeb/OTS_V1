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
public class TestSheetItem {
    public int ItemNumber;
    public int ItemUniqueId;
    public String ItemText;
    public float Mark;
    public int ItemTypeId;
    public Boolean Selected =false;
    public String Type;
    public  Boolean IsTrueFalse;
    public  Boolean IsMultipleChoiceMultipleAnswers;
    public  Boolean IsMultipleChoiceSingleAnswer;
    public List<AnswerOptionItem> AnswerOptions;
    public TestSheetItem(){
        AnswerOptions= new ArrayList();
    }
    public TestSheetItem(int ItemTypeId) {
        this.ItemTypeId = ItemTypeId;
        AnswerOptions= new ArrayList();
    }
    
    
}

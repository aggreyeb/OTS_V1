/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels;

import OTS.DataModels.CourseAssignmentDescription;
import OTS.DataModels.Courseassignment;
import OTS.DataModels.DataSource;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MEA
 */
public class QuestionBanks {
     DataSource dataSource;

    public QuestionBanks(DataSource dataSource) {
        this.dataSource = dataSource;
    }
     
    public List<QuestionBankItem> List(int testId){
        
        List<QuestionBankItem> items = new ArrayList();
          try {

            String sql ="select qb.QuestionBankId, \n" +
                        " qb.QuestionText,\n" +
                        " qb.GroupId ,\n" +
                        " qb.CognitiveLevelTypeId,\n" +
                        " qb.TestId,\n" +
                        " qb.QuestionNatureTypeId,\n" +
                        " qb.QuestionTypeId,\n" +
                        " ct.Name as CognitiveTypeName,\n" +
                        " qn.Name as QuestionNatureTypeName,\n" +
                        " qt.Name as QuestionTypeName \n" +
                        "from  questionbank as qb inner join cognitiveleveltype as ct\n" +
                        " on qb.CognitiveLevelTypeId=ct.CognitiveLevel\n" +
                        " inner join questionnaturetype as qn on qb.QuestionNatureTypeId \n" +
                        " inner join questiontype as qt on qb.QuestionTypeId=qt.QuestionType\n" +
                        " where qb.TestId=" + testId;
            
            this.dataSource.Open();
            this.dataSource.ExecuteCustomDataSet(sql, items, QuestionBankItem.class);
          
        } catch (Throwable ex) {
           
        } finally {
            this.dataSource.Close();
        }

       return items;
    }
}

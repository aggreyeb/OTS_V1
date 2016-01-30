/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.UnitTesting;

import OTS.DataModels.MySqlDataSource;
import OTS.Message;
import OTS.ObjectModels.AcademicTests;
import OTS.ObjectModels.QuestionManagement.TestMarking;
import OTS.ObjectModels.Response;
import org.junit.Test;

/**
 *
 * @author Eb
 */
public class TestMarkingTest {
    
    @Test
    public void MarkCorrectAnswers(){
        //Arrange
        int studentId=3;
        int testId=1;
        Message message= new Response("","");
        TestMarking making= new TestMarking(new MySqlDataSource());
        //Act
        making.Mark(studentId, testId, message);
        //Assert
        System.out.print(message.ToJson());
    }
    
     @Test
    public void MarkAll(){
        //Arrange
      
        int testId=1;
        Response message= new Response("","");
        TestMarking making= new TestMarking(new MySqlDataSource());
        //Act
        making.Mark(testId, message);
        //Assert
        System.out.print(message.ToJson());
    }
    
     @Test
    public void CalculateTotalCorrentAnswersForTestItem(){
        //Arrange
      
        int testId=1;
        int testItemId=26;
        Response message= new Response("","");
        TestMarking making= new TestMarking(new MySqlDataSource());
        //Act
       int result= making.CalculateTotalCorrentAnswersForTestItem(testId, testItemId);
        //Assert
        System.out.print(result);
    }
    
     @Test
    public void CheckTestmarked(){
        //Arrange
      
        int testId=1;
        AcademicTests at = new AcademicTests(new MySqlDataSource());
        //Act
        Boolean result=  at.IsAllStudentTestMarked(testId);
        //Assert
        System.out.print(result);
    }
}

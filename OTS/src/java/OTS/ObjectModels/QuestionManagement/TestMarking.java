/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels.QuestionManagement;

import OTS.DataModels.DataSource;
import OTS.DataModels.Studenttestanswersheet;
import OTS.DataModels.Studenttesthistory;

import OTS.DataModels.Testitem;
import OTS.Message;
import OTS.ObjectModels.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eb
 */
public class TestMarking {
    
    private DataSource dataSource;
    private Message message;
   
   
   
    public TestMarking(DataSource dataSource) {
        this.dataSource = dataSource;
       
    }
    
     
    public void Mark(int studentId,int testId,Message response){
        
        try{
            // this.dataSource.Open();
           //  this.dataSource.BeginTransaction();
             String creteria="(q.QuestionType=1 or q.QuestionType=2)";
             String sqlCorrectTrueFalseMultipleSingleAnswer="select sta.StudentTestAnswerSheetId as AnswerSheetId, q.TestItemId,q.QuestionType, q.CognitiveLevelTypeId,q.QuestionNatureType, q.Mark  from testanswersheet ta \n" +
                                        "inner join studenttestanswersheet sta on ta.TestItemId=sta.TestItemId \n" +
                                        "inner join testitem q on q.TestItemId=ta.TestItemId  \n" +
                                        "Where sta.StudentId=" + studentId +  " and ta.TestId =" + testId +  " and ta.A=sta.A and ta.B=sta.B and ta.C=sta.C and ta.D=sta.D and " + creteria ;
             
              String sqlInCorrectMultipleSingleAnswer="select sta.StudentTestAnswerSheetId as AnswerSheetId, q.TestItemId,q.QuestionType,q.CognitiveLevelTypeId,q.QuestionNatureType,q.Mark from testanswersheet ta \n" +
                                                     "inner join studenttestanswersheet sta on ta.TestItemId=sta.TestItemId\n" +
                                                     "inner join testitem q on q.TestItemId=ta.TestItemId\n" +
                                                     "Where sta.StudentId=" + studentId +  " and ta.TestId =" + testId +  " and ta.A=sta.A and ta.B=sta.B and ta.C=sta.C and ta.D=sta.D and " + creteria ;
     
              
              String sqlMultipleChoiceMultipleAnswers="select sta.StudentTestAnswerSheetId as AnswerSheetId, q.TestItemId,q.QuestionType, q.CognitiveLevelTypeId,q.QuestionNatureType, q.Mark  from testanswersheet ta \n" +
                                                       "inner join studenttestanswersheet sta on ta.TestItemId=sta.TestItemId \n" +
                                                       "inner join testitem q on q.TestItemId=ta.TestItemId  \n" +
                                                       "Where sta.StudentId=" + studentId + " and ta.TestId = " + testId +  "and  q.QuestionType=3";
                 
             List<TestMarkingItem> correctList=new ArrayList();
             List<TestMarkingItem> inCorrectList=new ArrayList();
             this.dataSource.ExecuteCustomDataSet(sqlCorrectTrueFalseMultipleSingleAnswer, correctList, TestMarkingItem.class);
             this.MarkCorrectAnswers(testId, studentId, correctList, this.dataSource);
              
             this.dataSource.ExecuteCustomDataSet(sqlInCorrectMultipleSingleAnswer, correctList, TestMarkingItem.class);
             this.MarkInCorrectAnswers(testId, studentId, inCorrectList);
             this.MarkMultipleChoiceMultipleAnswers(testId, studentId);
             //this.dataSource.Commit();
             
             float totalMark=  this.CalculateTotalMark(testId, studentId);
             this.RecordMarking(studentId, testId, totalMark);
             
             
             response.ChangeStatus("ok");
        }
        catch(Throwable ex){
          //  this.dataSource.Rollback();
            response.ChangeStatus("fail");
            response.UpdateError(ex.toString());
        }
        finally{
           // this.dataSource.Close();
        }
    }
    
    public void Mark(int testId,Message response){
         try{
            // this.dataSource.Open();
           //  this.dataSource.BeginTransaction();
             
             String sql="select * from \n" +
" (select sta.StudentTestAnswerSheetId as AnswerSheetId,sta.StudentId, q.TestItemId,q.QuestionType, q.CognitiveLevelTypeId,q.QuestionNatureType, q.Mark  from testanswersheet ta \n" +
"                                                       inner join studenttestanswersheet sta on ta.TestItemId=sta.TestItemId \n" +
"                                                       inner join testitem q on q.TestItemId=ta.TestItemId  \n" +
"                                                       Where ta.TestId=" + testId + ") as p where p.StudentId    in (select  StudentId from studenttesthistory where   TotalMark<0);";
              
             /*
             String sql="select sta.StudentTestAnswerSheetId as AnswerSheetId,sta.StudentId, q.TestItemId,q.QuestionType, q.CognitiveLevelTypeId,q.QuestionNatureType, q.Mark  from testanswersheet ta \n" +
                                                       "inner join studenttestanswersheet sta on ta.TestItemId=sta.TestItemId \n" +
                                                       "inner join testitem q on q.TestItemId=ta.TestItemId  \n" +
                                                       "Where ta.TestId = " +  testId ;
                     */
            
            List<TestMarkingItem> items=new ArrayList();
            this.dataSource.ExecuteCustomDataSet(sql, items, TestMarkingItem.class);
             //this.dataSource.Commit();
             if(items.size()>0){
             for(TestMarkingItem a:items){
                 this.Mark(a.StudentId, testId, response);
             }
              
             }
             response.ChangeStatus("ok");
        }
        catch(Throwable ex){
            //this.dataSource.Rollback();
            response.ChangeStatus("fail");
            response.UpdateError(ex.toString());
        }
        finally{
           // this.dataSource.Close();
        }
    }
    
   
    
    protected Boolean MarkCorrectAnswers(int testId,int studentId, List<TestMarkingItem> correctItems,DataSource dataSource){
        
         for(TestMarkingItem t:correctItems){
           Studenttestanswersheet item= (Studenttestanswersheet)dataSource.Find(Studenttestanswersheet.class, new Integer(t.AnswerSheetId));
           
             if(item!=null){
               item.setTotalCorrectAnswers(1);
               item.setIsCorrect(Boolean.TRUE);
            }
         }
        return true;
    }
    
    protected Boolean MarkInCorrectAnswers(int testId,int studentId,List<TestMarkingItem> correctItems){
        
         for(TestMarkingItem t:correctItems){
           Studenttestanswersheet item= (Studenttestanswersheet)this.dataSource.Find(Studenttestanswersheet.class, new Integer(t.AnswerSheetId));
            if(item!=null){
               item.setTotalCorrectAnswers(0);
               item.setIsCorrect(Boolean.FALSE);
            }
         }
        return true;
    }
    
    protected Boolean MarkMultipleChoiceMultipleAnswers(int testId,int studentId){
       String sql="select   q.TestItemId,\n" +
                    "         q.QuestionType,\n" +
                    "         q.CognitiveLevelTypeId,\n" +
                    "	      q.QuestionNatureType,\n" +
                    "         ta.A,\n" +
                    "			ta.B,\n" +
                    "			ta.C,\n" +
                    "			ta.D,\n" +
                    "			q.Mark  from testanswersheet ta \n" +
                    "inner join testitem q on q.TestItemId=ta.TestItemId  \n" +
                    "Where  ta.TestId =" + testId  +   " and  q.QuestionType=3";
        
       //Test AnswerSheet
         List<TestMarkingAnswerSheet> answerSheetItems=new ArrayList();
         this.dataSource.ExecuteCustomDataSet(sql, answerSheetItems, TestMarkingAnswerSheet.class);
      
         String sqlStudentTestSheetItems="select ta.StudentTestAnswerSheetId as AnswerSheetId, q.TestItemId,\n" +
                                            "         q.QuestionType,\n" +
                                            "		   q.CognitiveLevelTypeId,\n" +
                                            "			q.QuestionNatureType,\n" +
                                            "		   ta.A,\n" +
                                            "			ta.B,\n" +
                                            "			ta.C,\n" +
                                            "			ta.D,\n" +
                                            "			q.Mark,IsCorrect,TotalCorrectAnswers  from studenttestanswersheet ta \n" +
                                            "inner join testitem q on q.TestItemId=ta.TestItemId  \n" +
                                            "Where  ta.TestId =" + testId +  " and ta.StudentId= " + studentId  + " and  q.QuestionType=3";
          
         //Student Answer Sheet
         List<TestMarkingAnswerSheet> studentAnswerSheetItems=new ArrayList();
         this.dataSource.ExecuteCustomDataSet(sqlStudentTestSheetItems, studentAnswerSheetItems, TestMarkingAnswerSheet.class);
         
        
         
         for(TestMarkingAnswerSheet t:studentAnswerSheetItems){
              int totalAnswerCorrect=0;
             
              
              TestMarkingAnswerSheet item=this.FindTestItem(t.TestItemId, answerSheetItems);
             
              
             if(item!=null){
                if(t.A){
                 if(item.A==t.A){
                     totalAnswerCorrect+=1;
                 }
                }
                if(t.B){
                 if(item.B==t.B){
                     totalAnswerCorrect+=1;
                 }
                }
                if(t.C){
                 if(item.C==t.C){
                     totalAnswerCorrect+=1;
                 }
                }
                 if(t.D){
                  if(item.D==t.D){
                     totalAnswerCorrect+=1;
                 }
                 }
             }
             Boolean isCorrect=false;
             if(totalAnswerCorrect>0){
                 isCorrect=true;
             }
             
             Studenttestanswersheet testSheetItem= (Studenttestanswersheet)dataSource.Find(Studenttestanswersheet.class, new Integer(t.AnswerSheetId));
            if(testSheetItem!=null){
               testSheetItem.setIsCorrect(isCorrect);
               testSheetItem.setTotalCorrectAnswers(totalAnswerCorrect);
               this.dataSource.Save(testSheetItem);
               
            }
            
           
         }
         
         return  true;
    }
    
    public TestMarkingAnswerSheet FindTestItem(int testItemId,List<TestMarkingAnswerSheet> answerSheetItems){
        TestMarkingAnswerSheet item =null;
        for(TestMarkingAnswerSheet a:answerSheetItems){
            if(a.TestItemId==testItemId){
                item=a;
                break;
            }
        }
        return item;
    }
    
    public float CalculateTotalMark(int testId,int studentId){
        try{
            // this.dataSource.Open();
          //   this.dataSource.BeginTransaction();
             String sqlStudentTestSheetItems="select ta.StudentTestAnswerSheetId as AnswerSheetId, q.TestItemId,\n" +
                                            "         q.QuestionType,\n" +
                                            "		   q.CognitiveLevelTypeId,\n" +
                                            "			q.QuestionNatureType,\n" +
                                            "		   ta.A,\n" +
                                            "			ta.B,\n" +
                                            "			ta.C,\n" +
                                            "			ta.D,\n" +
                                            "			q.Mark,IsCorrect,TotalCorrectAnswers  from studenttestanswersheet ta \n" +
                                            "inner join testitem q on q.TestItemId=ta.TestItemId  \n" +
                                            "Where  ta.TestId =" + testId +  " and ta.StudentId="  + studentId  ;
          
         //Student Answer Sheet
         List<TestMarkingAnswerSheet> studentAnswerSheetItems=new ArrayList();
         this.dataSource.ExecuteCustomDataSet(sqlStudentTestSheetItems, studentAnswerSheetItems, TestMarkingAnswerSheet.class);
       
         float totalMark=0F;
         float totalTestMark=0F;
         for(TestMarkingAnswerSheet t:studentAnswerSheetItems){
            
                Testitem item= (Testitem) this.dataSource.Find(Testitem.class, new Integer(t.TestItemId));
                int totalCorrectTestItemOptions=this.CalculateTotalCorrentAnswersForTestItem(testId, t.TestItemId);
                float subTotal=(float)t.TotalCorrectAnswers/(float)totalCorrectTestItemOptions;
                totalMark+= subTotal * item.getMark();
                totalTestMark+=item.getMark();
           }
          // Percentage marks
         return (totalMark/totalTestMark) * 100;
        }
        catch(Throwable ex){
         //  this.dataSource.Rollback();
            return 0;
        }
        finally{
           // this.dataSource.Close();
        }
        
      
    }
    
    public int CalculateTotalCorrentAnswersForTestItem(int testId,int TestItemId){
         int totalAnswerCorrect=0;
         
        try{
             //this.dataSource.Open();
            // this.dataSource.BeginTransaction();
             String sql="select   q.TestItemId,\n" +
                    "         q.QuestionType,\n" +
                    "         q.CognitiveLevelTypeId,\n" +
                    "	      q.QuestionNatureType,\n" +
                    "         ta.A,\n" +
                    "			ta.B,\n" +
                    "			ta.C,\n" +
                    "			ta.D,\n" +
                    "			q.Mark  from testanswersheet ta \n" +
                    "inner join testitem q on q.TestItemId=ta.TestItemId  \n" +
                    "Where  ta.TestId =" + testId  +   " and  q.TestItemId=" + TestItemId;
        
       //Test AnswerSheet
         List<TestMarkingAnswerSheet> answerSheetItems=new ArrayList();
         this.dataSource.ExecuteCustomDataSet(sql, answerSheetItems, TestMarkingAnswerSheet.class);
         // this.dataSource.Commit();
          for(TestMarkingAnswerSheet t:answerSheetItems){
              
                if(t.A){
                    totalAnswerCorrect+=1;
                }
                if(t.B){
                   totalAnswerCorrect+=1; 
                }
                
                if(t.C){
                    totalAnswerCorrect+=1;
                }
                if(t.D){
                    totalAnswerCorrect+=1;
                }
          }
          
          return totalAnswerCorrect;
        }
        catch(Throwable ex){
           // this.dataSource.Rollback();
            return totalAnswerCorrect;
        }
        finally{
           
        }
        
       
    }
    
    public Boolean RecordMarking(int studentId,int testId,float totalMark){
          Boolean result=false;
        try{
           // this.dataSource.Open();
             //this.dataSource.BeginTransaction();  
             String sql="Select * from  studenttesthistory where StudentId=" + studentId + " and TestId=" + testId;
             List<TestHistoryItem>  items= new ArrayList();
             this.dataSource.ExecuteCustomDataSet(sql, items, TestHistoryItem.class);
             for(TestHistoryItem t:items){
                Studenttesthistory  item= (Studenttesthistory)this.dataSource.Find(Studenttesthistory.class, new Integer(t.StudentTestHistoryId));
                item.setTotalMark(totalMark);
                this.dataSource.Update(item);
             }
             //this.dataSource.Commit();
              result=true;
              return result;
          }
          catch(Throwable ex){
            //  this.dataSource.Rollback();
              
          }
          finally{
           // this.dataSource.Close();
        }
        return result;
    }
    
}

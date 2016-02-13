/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels.QuestionManagement;

import OTS.DataModels.Academiccourse;
import OTS.DataModels.Cognitiveleveltype;
import OTS.DataModels.DataSource;
import OTS.DataModels.Knowledgemap;
import OTS.DataModels.Node;
import OTS.DataModels.NodeItem;
import OTS.DataModels.Question;
import OTS.DataModels.Questionlineitem;
import OTS.DataModels.Questionnaturetype;
import OTS.DataModels.Questiontype;
import OTS.DataModels.Studenttestanswersheet;
import OTS.DataModels.Studenttesthistory;
import OTS.DataModels.Test;
import OTS.DataModels.Testanswersheet;
import OTS.DataModels.Testitem;
import OTS.DataModels.Testitemoption;
import OTS.DataModels.User;
import OTS.Message;
import OTS.ObjectModels.TestGenerationInput;
import OTS.ObjectModels.TestItemGenerationOutput;
import com.google.gson.Gson;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MEA
 */
public class Questions {
    
     DataSource dataSource;
     Message message;
      TestSheetItem[] questionItems=null;
       protected String queryString="select * from knowledgemap m inner join user as u" +
                                              " on m.CreatedBy=u.UserId where"
                                             + " KnowledgemapId=";
     
    public Questions(DataSource dataSource,Message message) {
        this.dataSource = dataSource;
        this.message=message;
    }

    
     public Knowledgemap Find(int parentConceptNodeId){
       
         Knowledgemap knowledgemap=null;
         List<Object> entities=new ArrayList();
           entities.add(new Knowledgemap());
           entities.add(new User());
         try{
        String sql=this.queryString + parentConceptNodeId;
          Object[] knowledgemaps= new Object[1];
         // this.dataSource.BeginTransaction();
          //  this.dataSource.Open();
           this.dataSource.ExecuteDataSet(sql, entities, knowledgemaps);
            if(knowledgemaps.length>0){
                Object obj=knowledgemaps[0];
                 Object item= ((Object[])obj)[0];
                  knowledgemap=(Knowledgemap)item;
             //  this.dataSource.Commit();
                return knowledgemap; 
                
            }
         }
         catch(Throwable ex){
       
         
         }
         finally{
           // this.dataSource.Close();
         }
         return  knowledgemap;
    }
   
    public NodeItem FindNodeItem(Node parantNode,String Identity){
      
       return  parantNode.Find(Identity);
    }
    
    public Node FindRootNode(int id){
        Knowledgemap km=this.Find(id);
        Node node= new Node(km);
        return node;
    }
    
    protected Boolean IsParentNode(TestGenerationInput input){
       
      if(input.NodeIdentity==null){
          return true;
      }
      
      if(input.NodeIdentity.equals("")){
          return true;
      }
      
      return false;
   }
    
    public String RetriveNodeName(TestGenerationInput input){
        String name="";
        
        if(this.IsParentNode(input)){
          Node node=   this.FindRootNode(input.KnowledgeMapId);
          name= node.Name;
        }
        else{
          Node node=  this.FindRootNode(input.KnowledgeMapId); 
          
          NodeItem nodeItem=this.FindNodeItem(node, input.NodeIdentity);  
          name= nodeItem.Name;
        }
        return name;
    }
    
    public  Boolean SaveQTestItems(TestGenerationInput input, List<TestItemGenerationOutput> outputs){
        int QuestionId=0;
        String nodeName="";
        
        try{
             //Find test by id
        nodeName=  this.RetriveNodeName(input); 
            
      // this.dataSource.Open();
      // this.dataSource.BeginTransaction();
       Test test= (Test)this.dataSource.Find(Test.class, new Integer(input.TestId));
        //Find the conginitive level type
      Cognitiveleveltype cognitiveType=(Cognitiveleveltype)this.dataSource.Find(Cognitiveleveltype.class, new Integer(input.CognitiveType));
        //Find Question Nature type
      Questionnaturetype questionNature=(Questionnaturetype)this.dataSource.Find(Questionnaturetype.class, new Integer(input.NatureOfItem));
        //Find Question type
       
      Questiontype questiontype=(Questiontype)this.dataSource.Find(Questiontype.class, new Integer(input.ItemType));
      
      Academiccourse academicCourse=(Academiccourse)this.dataSource.Find(Academiccourse.class, new Integer(input.CourseId));
    
    
     
      
      
      // Create question object
     
       switch(questiontype.getQuestionType()) {
          
           case 1://true/false
          // case 2:
            Question q1=null;
               
            for(TestItemGenerationOutput o:outputs){
                for(String s:o.LineItems){ 
               
                q1 = new Question();
                //q1.setText(s);???
                q1.setText(o.Text);
               // q1.setTest(test);
                q1.setCognitiveleveltype(cognitiveType);
                q1.setQuestiontype(questiontype);
                q1.setQuestionnaturetype(questionNature);
                q1.setAcademiccourse(academicCourse);
                q1.setTest(test);
                
                if(this.CanSaveTestQuestion(cognitiveType, questionNature, questiontype, test, q1.getText(),input.CourseId))
                 {
                //this.dataSource.Commit();
                  this.dataSource.Save(q1);  
                 QuestionId=q1.getQuestionId();
                Questionlineitem item= new Questionlineitem();
                 item.setQuestion(q1);
                 item.setText("True");
                 this.dataSource.Save(item);
                 item= new Questionlineitem();
                 item.setQuestion(q1);
                 item.setText("False");
                  
                 this.dataSource.Save(item);
                 
                }
               
                 
               }
             }
           
             break;
           
           case 3: //MultipleChoice-MultipleAnswers
           case 2:
          
          Question   q = new Question();
             //q.setTest(test);
             q.setCognitiveleveltype(cognitiveType);
             q.setQuestiontype(questiontype);
             q.setQuestionnaturetype(questionNature);
             q.setAcademiccourse(academicCourse);
             q.setTest(test);
             //Classify or Describe
             if( cognitiveType.getCognitiveLevel()==2)
             {//Classify
                 q.setText("Which of the following statements are true about " +  nodeName.toLowerCase()); 
                 //q.setText(outputs.get(0).Text); 
             }else if(cognitiveType.getCognitiveLevel()==4){
               String s= outputs.get(0).Text;
                 q.setText(s);
             }
             else{
                 if(questiontype.getQuestionType()==2){
                    q.setText("Which of the following statement is true about " +  nodeName.toLowerCase());
                 }
                 if(questiontype.getQuestionType()==3){
                   q.setText("Which of the following statements are true about " +  nodeName.toLowerCase()); 
                  }
              }
             
          if(this.CanSaveTestQuestion(cognitiveType, questionNature, questiontype, test,  q.getText().toLowerCase(),input.CourseId)){
           
             List<String> duplicateCheckList= new ArrayList();
             for(TestItemGenerationOutput x:outputs){
                for(String z:x.LineItems){
                   if(duplicateCheckList.contains(z)) continue;
                    duplicateCheckList.add(z);
                  }
               }
              this.dataSource.Save(q);
              
              for(String z:duplicateCheckList){
               
                    Questionlineitem item= new Questionlineitem();
                    item.setQuestion(q);
                     item.setText(z);
                     this.dataSource.Save(item);
                  }
              }
             break;  
                
           default:
            break;
              
       } 
     
        return true;
        }
        catch(Throwable ex){
            // this.dataSource.Rollback();
            return false;
        }
        finally{
            //this.dataSource.Close();
            this.RemoveOphanQuestion(QuestionId);
        }
    } 
    
    
     public void RemoveOphanQuestion(int questionId){
        try{
             //this.dataSource.Open();
           //  this.dataSource.BeginTransaction();
             String sql="select * from questionlineitem where question_Id=" + questionId;
             String sqlDelete="Delete from question where QuestionId=" + questionId;
           List list=this.dataSource.Execute(sql);
           if(list.size()==0){
               this.dataSource.ExecuteNonQuery(sqlDelete);
           } 
          // this.dataSource.Commit();
        }
        catch(Throwable ex){
           // this.dataSource.Rollback();
        }
        finally{
           // this.dataSource.Close();
        }
    }
    
      protected Boolean CanSaveTestQuestion(Cognitiveleveltype congnitiveType,
            Questionnaturetype questionNatureType,Questiontype questionType,Test test,String questionText,int courseId){
        Boolean canSave=true;
       
       String sql="select * from question where QuestionTypeId=" + questionType.getQuestionType()  +
               " and QuestionNatureType_id= " + questionNatureType.getQuestionNatureType() + 
               " and CognitiveLevelType_id= " + congnitiveType.getCognitiveLevel()  + 
               " and Text=" + "'" + questionText + "'" + " and CourseId=" + courseId  + " and TestId=" + test.getTestId() ;
        
        //  String sql="select * from question where " +
              //  "Test_id=" + test.getTestId() +  " and Text=" + "'" + questionText + "'" ;
        
         List items= this.dataSource.Execute(sql);
         if(items.size()>0){
             canSave=false;
         }
        return canSave;  
    }
     
    
      public List<QuestionItem> ListQuestionBank(int CourseId,int testId){
        
        List<QuestionItem> list= new ArrayList();
      
       
           try {
          
      
            list=FilterSelectedQuestionBankItems(CourseId,testId);
            
            for(int i=0; i<list.size();i++){
                list.get(i).Number=i +1;
                list.get(i).Selected=false;
                if(list.get(i).QuestionTypeId==3){
                    list.get(i).IsMultipleOptions=true;
                }
            }
          
            for(QuestionItem a:list){
                List<AnswerOptionItem> lineItems= new ArrayList();
                String query="select qi.QuestionLineItemId as AnswerOptionId , \n" +
                            "        qi.Text  as AnswerOptionText\n" +
                            " from  questionlineitem  as qi where Question_id= "+ a.QuestionUniqueId;
                this.dataSource.ExecuteCustomDataSet(query, lineItems, AnswerOptionItem.class);
               // Collections.shuffle(lineItems);
                int i=0;
                for(AnswerOptionItem x:lineItems){
                     if(i==4) break;
                    a.LineItems.add(x.AnswerOptionText);
                   i=i+1;
                }
            }
           message.ChangeStatus("ok");
            message.ChangeContent(new Gson().toJson(list));
        } catch (Throwable ex) {
          
          
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }
        
       return list;
    }
      
    
      
    private List<QuestionItem> FilterSelectedQuestionBankItems(int courseId,int testId){
        List<QuestionItem>  list= new ArrayList();
        
        List<QuestionItem> questionList= new ArrayList();
        List<QuestionBankIdItem> testItemList= new ArrayList();
        
          String sql="select q.QuestionId as QuestionUniqueId,\n" +
"                      q.Text as QuestionText,q.CourseId,\n" +
"                      qt.QuestionType as QuestionTypeId,\n" +
"                      qt.Name as QuestionType \n" +
"                      from  question as q \n" +
"                      inner join questiontype as qt on q.QuestionTypeId=qt.QuestionType\n" +
"                       inner join questionnaturetype nt on q.QuestionNatureType_id=nt.QuestionNatureType\n" +
"                       inner join cognitiveleveltype ct on q.CognitiveLevelType_id=ct.CognitiveLevel\n" +
"                       where q.CourseId=" + courseId + " and TestId=" + testId;
          
          
       String sql2="select QuestionBankId from testitem  where  CourseId=" + courseId + " and Test_id=" + testId;


          
        this.dataSource.ExecuteCustomDataSet(sql, questionList, QuestionItem.class); 
        this.dataSource.ExecuteCustomDataSet(sql2, testItemList, QuestionBankIdItem.class); 
        if(testItemList.size()>0){
            /*
            for(QuestionBankIdItem a :testItemList){
               if(!HasTestItem(questionList,a.QuestionBankId)){
               QuestionItem item= RetreiveUnlestedTestItem(questionList,a.QuestionBankId);
               list.add(item);
              }
            }
            */
            for(QuestionItem a:questionList){
                if(!HasTestItem(testItemList,a.QuestionUniqueId)){
                    list.add(a);
                }
            }
        }
        else{
        
           list.addAll(questionList);
        }
        
        return list;
    }
      
    private Boolean HasTestItem(List<QuestionBankIdItem>  list,int itemId){
        Boolean found =false;
        for(QuestionBankIdItem q:list){
            if(q.QuestionBankId==itemId){
                found=true;
                break;
            }
        }
        return found;
    };
    
    private QuestionItem RetreiveUnlestedTestItem(List<QuestionItem>  list,int itemId){
        QuestionItem found =null;
        for(QuestionItem q:list){
            if(q.QuestionUniqueId==itemId){
                found=q;
                break;
            }
        }
        return found;
    };
    
    public List<QuestionItem> ListTestQuestionBank(int testid){
        
        List<QuestionItem> list= new ArrayList();
      
       
           try {
            //this.dataSource.Open();
           
            String sql="select q.QuestionId as QuestionUniqueId,\n" +
                        "      q.Text as QuestionText,\n" +
                        "      qt.QuestionType as QuestionTypeId,\n" +
                        "      qt.Name as QuestionType   \n" +
                        "from  question as q \n" +
                        "inner join questiontype as qt on q.QuestionTypeId=qt.QuestionType\n" +
                        "inner join questionnaturetype nt on q.QuestionNatureType_id=nt.QuestionNatureType\n" +
                        "inner join cognitiveleveltype ct on q.CognitiveLevelType_id=ct.CognitiveLevel\n" +
                        "where q.Test_id=" + testid ;
            this.dataSource.ExecuteCustomDataSet(sql, list, QuestionItem.class); 
            
            for(int i=0; i<list.size();i++){
                list.get(i).Number=i +1;
                list.get(i).Selected=false;
                if(list.get(i).QuestionTypeId==3){
                    list.get(i).IsMultipleOptions=true;
                }
            }
          
            for(QuestionItem a:list){
                List<AnswerOptionItem> lineItems= new ArrayList();
                String query="select qi.QuestionLineItemId as AnswerOptionId , \n" +
                            "        qi.Text  as AnswerOptionText\n" +
                            " from  questionlineitem  as qi where Question_id= "+ a.QuestionUniqueId;
                this.dataSource.ExecuteCustomDataSet(query, lineItems, AnswerOptionItem.class);
                for(AnswerOptionItem x:lineItems){
                   
                    a.LineItems.add(x.AnswerOptionText);
                   
                }
            }
           message.ChangeStatus("ok");
            message.ChangeContent(new Gson().toJson(list));
        } catch (Throwable ex) {
          
          
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }
        
       return list;
    }
    
    
    public List<TestSheetItem> ListTestSheet(int testid){
        
          List<TestSheetItem> testItemList= new ArrayList();
         List<QuestionItem> list= new ArrayList();
          String sql="select q.TestItemId as QuestionUniqueId,\n" +
                        "      q.Text as QuestionText,\n" +
                        "      q.Mark,\n" +
                        "      qt.QuestionType as QuestionTypeId,\n" +
                        "      qt.Name as QuestionType   \n" +
                        "from  testitem as q \n" +
                        "inner join questiontype as qt on q.QuestionType=qt.QuestionType\n" +
                        "inner join questionnaturetype nt on q.QuestionNatureType=nt.QuestionNatureType\n" +
                        "inner join cognitiveleveltype ct on q.CognitiveLevelTypeId=ct.CognitiveLevel\n" +
                        "where q.Test_id=" + testid ;
          
          String sqlOptionsTemplate="select TestItemOptionId,Text,IsCorrect,TestItem_id as TestItemId from testitemoption  where TestItem_id=";
           try {
           // this.dataSource.Open();
           // this.dataSource.BeginTransaction();
            this.dataSource.ExecuteCustomDataSet(sql, list, QuestionItem.class);
            int count=1;
            for(QuestionItem a:list){
              TestSheetItem item= new TestSheetItem();  
                item.ItemUniqueId=a.QuestionUniqueId;
                item.ItemText=a.QuestionText;
                item.ItemTypeId=a.QuestionTypeId;
                item.Type=a.QuestionType;
                item.Mark=a.Mark;
                item.ItemNumber=count;
                 if(a.QuestionTypeId==1){
                     item.IsTrueFalse=true;
                 }
                 else{
                      item.IsTrueFalse=false;
                 }
                 if(a.QuestionTypeId ==2){
                     item.IsMultipleChoiceSingleAnswer=true;
                     
                 }
                 else{
                    item.IsMultipleChoiceSingleAnswer=false; 
                 }
                 if(a.QuestionTypeId==3){
                     item.IsMultipleChoiceMultipleAnswers=true;
                 }
                 else{
                      item.IsMultipleChoiceMultipleAnswers=false;
                 }
                 
                 List<TestItemOptionViewModel> optionList= new ArrayList();
                 String query=sqlOptionsTemplate + a.QuestionUniqueId;
                 this.dataSource.ExecuteCustomDataSet(query, optionList, TestItemOptionViewModel.class);
                 String[] Labels= new String[]{"(A)","(B)","(C)","(D)"};
                 int i=0;
                 
                 for(TestItemOptionViewModel v:optionList){
                      if(i==4) break;
                     if(v.IsCorrect==null){
                         v.IsCorrect=false;
                     }
                     AnswerOptionItem ap= new AnswerOptionItem();
                     ap.AnswerOptionId=v.TestItemOptionId;
                     ap.AnswerOptionText=v.Text;
                     ap.IsCorrect=v.IsCorrect;
                     if(ap.IsCorrect){
                         ap.IsChecked="true";
                         ap.CheckedText="true".toUpperCase();
                         ap.CheckedStyle="Green";
                     }
                     if(!ap.IsCorrect){
                         ap.IsChecked="false";
                         ap.CheckedText="false".toUpperCase();
                         ap.CheckedStyle="";
                     }
                     ap.AnswerOptionValue=v.Text.toLowerCase();
                     ap.LabelText=Labels[i];
                     ap.UniqueId= "opt_"+ optionList.get(0).TestItemOptionId;
                     item.AnswerOptions.add(ap);
                     i=i+1;
                 }
                 testItemList.add(item);
                 count=count+1;
            }
             message.ChangeContent(new Gson().toJson(testItemList));
             message.ChangeStatus("ok");
           // this.dataSource.Commit();
        } catch (Throwable ex) {
           // this.dataSource.Rollback();
          
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
            //this.dataSource.Close();
        }
        
       return testItemList;
    }
    
    public List<TestAnswerSheetListItem> ListTestAnswerSheet(int testid){
        
          List<TestAnswerSheetListItem> list= new ArrayList();
       
           try {
           // this.dataSource.Open();
            //this.dataSource.BeginTransaction();
            String sql ="select t.QuestionType as QuestionTypeId , \n" +
                        "ts.TestAnswerSheetId,\n" +
                        "ts.LineNumber,\n" +
                        "ts.A,\n" +
                        "ts.B,\n" +
                        "ts.C,\n" +
                        "ts.D,\n" +
                        "ts.TestId,\n" +
                        "ts.TestItemId from testitem t\n" +
                        " inner join testanswersheet ts on t.TestItemId=ts.TestItemId where t.Test_id=" + testid;

           // this.dataSource.Commit();
          
            this.dataSource.ExecuteCustomDataSet(sql, list, TestAnswerSheetListItem.class);
           message.ChangeContent(new Gson().toJson(list));
           message.ChangeStatus("ok");
        } catch (Throwable ex) {
           // this.dataSource.Rollback();
          
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }
        
       return list;
    }
    
      public List<TestSheetItem> ListTestSheet(int testid,DataSource d){
        
        List<TestSheetItem> list= new ArrayList();
          
           d.ExecuteCustomDataSet(queryString, list, TestSheetItem.class);
        
          return list;
      }
    
      public List<TestSheetItem> AddTestSheetItems(String itemJsons ,int testid,int courseId){
        
           List<TestSheetItem> items= new ArrayList();
       
           try {
            //this.dataSource.Open();
           // this.dataSource.BeginTransaction();
             Gson g= new Gson();
             QuestionItem[] questionItems= (QuestionItem[])g.fromJson(itemJsons, QuestionItem[].class);
       
           
             Test t= (Test)this.dataSource.Find(Test.class, new Integer(testid));
             Academiccourse course= (Academiccourse)this.dataSource.Find(Academiccourse.class, new Integer(courseId));
             
              if(t!=null){
                //  Set testItems= new HashSet();
                  for(QuestionItem a:questionItems){
                   Question question=(Question)this.dataSource.Find(Question.class, new Integer(a.QuestionUniqueId));
                      
                      Testitem testItem= new Testitem();
                      testItem.setText(a.QuestionText);
                      testItem.setMark(1.0F); //default the mark to 1
                      testItem.setQuestionBankId(question.getQuestionId());
                      testItem.setCognitiveleveltype(question.getCognitiveleveltype());
                      testItem.setQuestionnaturetype(question.getQuestionnaturetype());
                      testItem.setQuestiontype(question.getQuestiontype());
                      testItem.setAcademiccourse(course);
                      testItem.setTest(t);
                      this.dataSource.Save(testItem);
                 //Create Test Answer sheet item
                  Testanswersheet ta= new Testanswersheet();
                  ta.setTest(t);
                  ta.setTestitem(testItem);
                  ta.setLineNumber(0);
                  ta.setA(false);
                  ta.setB(false);
                  ta.setC(false);
                  ta.setD(false);
                  dataSource.Save(ta);  
                  //Save Test Answer sheet item    
                  for(String s: a.LineItems){
                    Testitemoption tp= new Testitemoption();
                           tp.setTestitem(testItem);
                           tp.setText(s);
                           tp.setIsCorrect(Boolean.FALSE);
                           this.dataSource.Save(tp);
                       }
                 }  
              }
            
           // this.dataSource.Commit();
            message.ChangeStatus("ok");
            message.ChangeContent(new Gson().toJson(items));
        } catch (Throwable ex) {
           // this.dataSource.Rollback();
          
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }
        
       return items;
    }
    
   public Boolean RemoveTestSheetItem(int testid,String jsonData){
        
            Boolean result=false;
       
           try {
            //this.dataSource.Open();
            //this.dataSource.BeginTransaction();
            Gson g= new Gson();
            TestSheetItem[] questionItems= (TestSheetItem[])g.fromJson(jsonData, TestSheetItem[].class);
            for(TestSheetItem item:questionItems){
                //Delete all children
                for(AnswerOptionItem a: item.AnswerOptions){
                  Testitemoption op= (Testitemoption)this.dataSource.Find(Testitemoption.class, new Integer(a.AnswerOptionId));
                  if(op!=null){
                      this.dataSource.Delete(op);
                  }
                }
                
                //Check to see if there is additional ophan test item options
                List<TestItemOptionViewModel> OphanItems=new ArrayList();
                this.dataSource.ExecuteCustomDataSet("select TestItemOptionId,Text,IsCorrect,TestItem_id as TestItemId from testitemoption  where TestItem_id=" + item.ItemUniqueId, OphanItems,TestItemOptionViewModel.class);
                 if(OphanItems.size()>0){
                     for(TestItemOptionViewModel a:OphanItems){
                        Testitemoption op= (Testitemoption)this.dataSource.Find(Testitemoption.class, new Integer(a.TestItemOptionId));
                        if(op!=null){
                            this.dataSource.Delete(op);
                        }
                     }
                 }
                 Testitem ot= (Testitem)this.dataSource.Find(Testitem.class, new Integer(item.ItemUniqueId));
                 if(ot!=null){
                    // this.dataSource.Delete(ot);
                     String sqlDelete="Delete from testitem where TestItemId=" + ot.getTestItemId();
                     this.dataSource.ExecuteNonQuery(sqlDelete);
                 }
                
                //Remove the item from test answer sheet
                  String sql="select TestAnswerSheetId from testanswersheet where TestId= " + testid  + " and TestItemId=" +  item.ItemUniqueId;
                  List<Integer> items= (List<Integer>) this.dataSource.Execute(sql);
                  if(items.size()>0){
                        Testanswersheet ta= (Testanswersheet)this.dataSource.Find(Testanswersheet.class, new Integer(items.get(0)));
                        this.dataSource.Delete(ta);
                  }
                 
            }
           
             message.ChangeStatus("ok");
        } catch (Throwable ex) {
            //this.dataSource.Rollback();
          
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           
        }
        
       return result;
    }
   
   
   public void RemoveSelectedTestSheetItems(int testid,int courseId, String jsonData){
   
     try{
           Gson g= new Gson();
           TestSheetItem[] questionItems= (TestSheetItem[])g.fromJson(jsonData, TestSheetItem[].class);
           int count=questionItems.length;
          
           for(TestSheetItem t:questionItems){
              
                Testitem ot= (Testitem)this.dataSource.Find(Testitem.class, new Integer(t.ItemUniqueId));
                String sqlTestanswersheet="delete from testanswersheet where TestId=" + testid + " and TestItemId=" + ot.getTestItemId();
                String sqlTestitemoption="delete from testitemoption where TestItem_id=" +  ot.getTestItemId();
               // String sqlTestitem="delete from  testitem where Test_id=" + ot.getTestItemId() +  " and CourseId=" + courseId;
                this.dataSource.ExecuteNonQuery(sqlTestanswersheet);
                this.dataSource.ExecuteNonQuery(sqlTestitemoption);
               // this.dataSource.ExecuteNonQuery(sqlTestitem);
                this.dataSource.Delete(ot);
                
           }
          message.ChangeStatus("ok");
     }
     catch(Throwable ex){
           message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
     }
    
   }
   
    
    public  AnswerOptionItem FindTestSheetItem(int optionId){
        AnswerOptionItem item=null;
        for(TestSheetItem a:questionItems ){
            for(AnswerOptionItem p:a.AnswerOptions){
                if(p.AnswerOptionId==optionId){
                    item=p;
                    break;
                }
            }
        }
        return item;
    }
    
     public Boolean UpdateTestItemOptions(int testid,String jsonData){
        List<Testitemoption> testItemOptions= new  ArrayList();
        Boolean result=false;
        String sqlOptionsTemplate="select TestItemOptionId,Text,IsCorrect,TestItem_id as TestItemId from testitemoption  where TestItem_id=";
           try {
           // this.dataSource.Open();
           // this.dataSource.BeginTransaction();
              Gson g= new Gson();
              questionItems= (TestSheetItem[])g.fromJson(jsonData, TestSheetItem[].class);
              
              List<Integer> testItems= (List<Integer>)this.dataSource.Execute("select TestItemId from testitem  where Test_id=" + testid);
               
              for(Integer a:testItems){
                   List<TestItemOptionViewModel> items=new ArrayList();
                  this.dataSource.ExecuteCustomDataSet(sqlOptionsTemplate + a, items, TestItemOptionViewModel.class);
                  
                  for(TestItemOptionViewModel t:items ){
   
                    Testitemoption item=   (Testitemoption)this.dataSource.Find(Testitemoption.class, new Integer(t.TestItemOptionId));
                     AnswerOptionItem ap=  this.FindTestSheetItem(t.TestItemOptionId);
                     if(ap!=null){
                        item.setIsCorrect(ap.IsCorrect);
                        testItemOptions.add(item);
                        this.dataSource.Update(item);
                     }
                     
                  }
               }
             
              //Update Test Item Item Marks
              for(TestSheetItem p:questionItems){
                Testitem item=  (Testitem)this.dataSource.Find(Testitem.class,new Integer(p.ItemUniqueId) );
                if(item!=null){
                    item.setMark(p.Mark);
                    this.dataSource.Update(item);
                }
              }
              
               //update the Test Answer sheet item
              for(TestSheetItem x:questionItems){
              
                //Get the Test sheet item
                 String  sql ="select * from testanswersheet where TestId=" + testid + " and TestItemId=" +  x.ItemUniqueId;
                   List<TestAnswerSheetItem> answerSheetItems= new ArrayList();
                   this.dataSource.ExecuteCustomDataSet(sql, answerSheetItems, TestAnswerSheetItem.class);
                   if(answerSheetItems.size()==1){
                     Testanswersheet as=  (Testanswersheet)this.dataSource.Find(Testanswersheet.class, new Integer(answerSheetItems.get(0).TestAnswerSheetId));
                       
                     //Get the test sheet Item state from Test Item Option 
                    //  sql="select TestItemOptionId,Text,IsCorrect,TestItem_id as TestItemId from testitemoption  where TestItem_id=" + as.getTestitem().getTestItemId() + " order by TestItemOptionId";
                     // List<TestItemOptionViewModel> list=new ArrayList();
                     // this.dataSource.ExecuteCustomDataSet(sql,list,TestItemOptionViewModel.class);
                     List<AnswerOptionItem> list=x.AnswerOptions;
                    
                     if(as !=null){
                        if(list.size()==2){
                         as.setA(list.get(0).IsCorrect);
                         as.setB(list.get(1).IsCorrect);
                        }
                        if(list.size()==3){
                         as.setA(list.get(0).IsCorrect);
                         as.setB(list.get(1).IsCorrect);
                         as.setC(list.get(2).IsCorrect);
                        }
                        
                         if(list.size()==4){
                          as.setA(list.get(0).IsCorrect);
                          as.setB(list.get(1).IsCorrect);
                          as.setC(list.get(2).IsCorrect);
                          as.setD(list.get(3).IsCorrect);
                        }
                        this.dataSource.Update(as);
                       
                     }
                    
                   
                   }
              }
              
            // this.dataSource.Commit();
              message.ChangeStatus("ok");
              result=true;
        } catch (Throwable ex) {
           // this.dataSource.Rollback();
           result=false;
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }
        
       return result;
    }
     
     
    //******************** Student Answer Sheet ***************************//
     
   public  void SubmitStudentTestSheet(int testid, int studentId,String jsonData){
       
           try {
            
               Gson g= new Gson();
             TestSheetItem[]  studentQuestionItems= (TestSheetItem[])g.fromJson(jsonData, TestSheetItem[].class);
               //this.dataSource.Open();
              // this.dataSource.BeginTransaction();
               OTS.DataModels.User user=  (OTS.DataModels.User)this.dataSource.Find(OTS.DataModels.User.class, new Integer(studentId));
               Test test=  (Test)this.dataSource.Find(Test.class, new Integer(testid));
               
               for(TestSheetItem t:studentQuestionItems){
                   Testitem item=  (Testitem)this.dataSource.Find(Testitem.class, new Integer(t.ItemUniqueId));
                      Studenttestanswersheet st = new Studenttestanswersheet();
                      st.setTest(test);
                      st.setUser(user);
                      st.setTestitem(item);
                     
                     if( t.AnswerOptions.size()==2){
                         st.setA(t.AnswerOptions.get(0).IsCorrect);
                         st.setB(t.AnswerOptions.get(1).IsCorrect);
                         st.setC(false);
                         st.setD(false);
                         st.setIsCorrect(false);
                        st.setTotalCorrectAnswers(0);
                     }
                     
                      if( t.AnswerOptions.size()==3){
                         st.setA(t.AnswerOptions.get(0).IsCorrect);
                         st.setB(t.AnswerOptions.get(1).IsCorrect);
                         st.setC(t.AnswerOptions.get(2).IsCorrect);
                         st.setD(false);
                         st.setIsCorrect(false);
                         st.setTotalCorrectAnswers(0);
                     }
                     if( t.AnswerOptions.size()==4){
                         st.setA(t.AnswerOptions.get(0).IsCorrect);
                         st.setB(t.AnswerOptions.get(1).IsCorrect);
                         st.setC(t.AnswerOptions.get(2).IsCorrect);
                         st.setD(t.AnswerOptions.get(3).IsCorrect);
                         st.setIsCorrect(false);
                         st.setTotalCorrectAnswers(0);
                     }
                     this.dataSource.Save(st);
               }
               
               RecordStudentTestEndDateTime(testid,studentId);
              // this.dataSource.Commit();
                message.ChangeStatus("ok");
           } 
           catch (Throwable ex) {
           // this.dataSource.Rollback();
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }
   }
   
   
   public void RecordStudentTestStartDateTime(int testId,int studentId){
       
       try{
          // this.dataSource.Open();
          // this.dataSource.BeginTransaction();
           
           // Check if Student has record in the history  table for a particular test
           //if record count is 0 then save
           if(!HasRecordInHistoryTable(testId,studentId)){
                Studenttesthistory h= new Studenttesthistory();
           h.setStudentId(studentId);
           h.setTestId(testId);
           DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
           Date date = new Date();
          
           h.setStartDate(new Date(dateFormat.format(date)));
         
           h.setTotalMark(-1F);
           this.dataSource.Save(h);
          //  this.dataSource.Commit();
           }
          
           message.ChangeStatus("ok");
       }
        catch (Throwable ex) {
            //this.dataSource.Rollback();
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
           // this.dataSource.Close();
        }
   }
   
   public void RecordStudentTestEndDateTime(int testId,int studentId){
       
       try{
          String sql="Select * from  studenttesthistory where StudentId=" + studentId + " and TestId=" + testId;
             List<TestHistoryItem>  items= new ArrayList();
             this.dataSource.ExecuteCustomDataSet(sql, items, TestHistoryItem.class);
             for(TestHistoryItem t:items){
                Studenttesthistory  item= (Studenttesthistory)this.dataSource.Find(Studenttesthistory.class, new Integer(t.StudentTestHistoryId));
                  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                   Date date = new Date();
                   item.setEndDate(new Date(dateFormat.format(date)));
                   this.dataSource.Update(item);
             }
       }
        catch (Throwable ex) {
           // this.dataSource.Rollback();
            message.ChangeStatus("exception");
            message.UpdateError(ex.toString());
        } finally {
          
        }
   }
  
     public Boolean HasRecordInHistoryTable(int testId,int studentId){
       
       try{
          String sql="Select * from  studenttesthistory where StudentId=" + studentId + " and TestId=" + testId;
             List<TestHistoryItem>  items= new ArrayList();
             this.dataSource.ExecuteCustomDataSet(sql, items, TestHistoryItem.class);
             if(items.size()>0){
                 return true;
             }
             return false;
       }
        catch (Throwable ex) {
            return false;
        } finally {
          
        }
   }
   
   
}

   
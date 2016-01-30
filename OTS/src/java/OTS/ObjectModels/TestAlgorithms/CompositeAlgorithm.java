/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels.TestAlgorithms;

import OTS.DataModels.Cognitiveleveltype;
import OTS.DataModels.DataSource;
import OTS.DataModels.Question;
import OTS.DataModels.Questionlineitem;
import OTS.DataModels.Questionnaturetype;
import OTS.DataModels.Questiontype;
import OTS.DataModels.Test;
import OTS.ObjectModels.ITestItemGeneration;
import OTS.ObjectModels.TestGenerationInput;
import OTS.ObjectModels.TestItemGenerationOutput;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aggreeb
 */
public abstract class CompositeAlgorithm extends Algorithm  {

    protected List<ITestItemGeneration> Items;
    protected String QuestionText="";
    protected String Name;
    String appendLast="";
    public CompositeAlgorithm(DataSource dataSource, String name,String appendLastQuestionLineItem) {
        super(dataSource, name);
        this.Items= new ArrayList();
        this.appendLast=appendLastQuestionLineItem;
    }

   
    @Override
    public Boolean HasId(String name) {
        return this.Name.equals(name);
    }

    @Override
    public List<TestItemGenerationOutput> Generate(TestGenerationInput... args) {
       TestGenerationInput input=args[0];
       List<TestItemGenerationOutput> outputs= new ArrayList();
       for(ITestItemGeneration item:Items){
         List<TestItemGenerationOutput> results=  item.Generate(input);
         outputs.addAll(results);
       }
      
       if(!this.appendLast.equals("")){
       TestItemGenerationOutput  s= new TestItemGenerationOutput();
     
         s.LineItems.add(appendLast);
           outputs.add(s);
       }
       
     List<TestItemGenerationOutput> items=  this.MergeTestItemGenerationOuput(outputs);
       TestItemGenerationOutput a=new TestItemGenerationOutput();
       /*
       if(input.ItemType==2){
             a.LineItems.add("None of the above statement is true");
       }
      if(input.ItemType==3){
             a.LineItems.add("None of the above statements are true");
       }
        */
      if(!items.contains(a))
        items.add(a );
       return items;
    }
    
    public List<TestItemGenerationOutput>  MergeTestItemGenerationOuput(List<TestItemGenerationOutput> items){
        List<TestItemGenerationOutput> outputs= new ArrayList();
        for(TestItemGenerationOutput a:items){
            for(String s : a.LineItems){
                TestItemGenerationOutput l= new TestItemGenerationOutput();
                l.Text=a.Text;
                l.LineItems.add(s);
                outputs.add(l);
            }
            
        }
        
        return outputs;
    }
    public void Add(ITestItemGeneration item){
        Items.add(item);
    }
    
    public void Remove(ITestItemGeneration item){
       Items.remove(item);
    }
    
    public void Clear(){
        Items.clear();
    }
    
    
    protected  Boolean Save(TestGenerationInput input, List<TestItemGenerationOutput> outputs){
        int QuestionId=0;
        try{
             //Find test by id
       this.dataSource.Open();
       this.dataSource.BeginTransaction();
       Test test= (Test)this.dataSource.Find(Test.class, new Integer(input.TestId));
        //Find the conginitive level type
      Cognitiveleveltype cognitiveType=(Cognitiveleveltype)this.dataSource.Find(Cognitiveleveltype.class, new Integer(input.CognitiveType));
        //Find Question Nature type
      Questionnaturetype questionNature=(Questionnaturetype)this.dataSource.Find(Questionnaturetype.class, new Integer(input.NatureOfItem));
        //Find Question type
       
      Questiontype questiontype=(Questiontype)this.dataSource.Find(Questiontype.class, new Integer(input.ItemType));
     
      // Create question object
     
       switch(questiontype.getQuestionType()) {
          
           case 1://true/false
           case 2:
            Question q1=null;
               for(TestItemGenerationOutput o:outputs){
                for(String s:o.LineItems){ 
               
                q1 = new Question();
                q1.setText(s);
               // q1.setTest(test);
                q1.setCognitiveleveltype(cognitiveType);
                q1.setQuestiontype(questiontype);
                q1.setQuestionnaturetype(questionNature);
                 if(!this.CanSaveTestQuestion(cognitiveType, questionNature, questiontype, test, s))
                     continue;
                Questionlineitem item= new Questionlineitem();
                 item.setQuestion(q1);
                 item.setText("True");
                this.dataSource.Save(item);
                 item= new Questionlineitem();
                 item.setQuestion(q1);
                 item.setText("False");
                  
                this.dataSource.Save(item);
                QuestionId=q1.getQuestionId();
                this.dataSource.Save(q1);
                
                 }
             }
             this.dataSource.Commit();
            // this.RemoveOphanQuestion(q1.getQuestionId());
             
             break;
           
           case 3: //MultipleChoice-MultipleAnswers
          Question   q = new Question();
            // q.setTest(test);
             q.setCognitiveleveltype(cognitiveType);
             q.setQuestiontype(questiontype);
             q.setQuestionnaturetype(questionNature);
             q.setText(outputs.get(0).Text);
             
             List<String> duplicateCheckList= new ArrayList();
             for(TestItemGenerationOutput x:outputs){
                for(String z:x.LineItems){
                   if(!duplicateCheckList.contains(z)) continue;
                    duplicateCheckList.add(z);
                  }
               }
              for(String z:duplicateCheckList){
                    if(z.equals(outputs.get(0).Text)) continue;
                    Questionlineitem item= new Questionlineitem();
                    item.setQuestion(q);
                     item.setText(z);
                     this.dataSource.Save(item);
                  }
              this.dataSource.Save(q);
              this.dataSource.Commit();
             break;  
                
           default:
            break;
              
       } 
      
    
        return true;
        }
        catch(Throwable ex){
             this.dataSource.Rollback();
            return false;
        }
        finally{
            this.dataSource.Close();
            this.RemoveOphanQuestion(QuestionId);
        }
    }
   
}

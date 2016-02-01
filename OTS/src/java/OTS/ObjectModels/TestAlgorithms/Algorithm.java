/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels.TestAlgorithms;

import OTS.DataModels.Cognitiveleveltype;
import OTS.DataModels.DataSource;
import OTS.DataModels.Knowledgemap;
import OTS.DataModels.Node;
import OTS.DataModels.NodeItem;
import OTS.DataModels.Question;
import OTS.DataModels.Questionlineitem;
import OTS.DataModels.Questionnaturetype;
import OTS.DataModels.Questiontype;
import OTS.DataModels.Test;
import OTS.DataModels.User;
import OTS.ObjectModels.ITestItemGeneration;
import OTS.ObjectModels.TestGenerationInput;
import OTS.ObjectModels.TestItemGenerationOutput;
import OTS.ObjectModels.TestItemGenerationOutputs;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MEA
 */
public abstract class Algorithm implements ITestItemGeneration {
    protected DataSource dataSource;
    protected  String Name;
    protected Node ParentNode;
    protected String queryString="select * from knowledgemap m inner join user as u" +
                                              " on m.CreatedBy=u.UserId where"
                                             + " KnowledgemapId=";
    
    protected Algorithm(DataSource dataSource,String name) {
        this.dataSource = dataSource;
        this.Name=name;
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
           // this.dataSource.Open();
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
            //this.dataSource.Close();
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
    
    
    
     @Override
    public Boolean HasId(String name) {
         if(name.equals("")) return false;
          Boolean  result= name.toLowerCase().equals(this.Name.toLowerCase());
          return result;
       // return name.equals(this.Name);
    }
    
     @Override
    public List<TestItemGenerationOutput> Generate(TestGenerationInput... args) {
      List<TestItemGenerationOutput> ouputs= new ArrayList();
      List<String> items= new ArrayList();
       TestGenerationInput input=args[0];
       TestItemGenerationOutput output;
       Node node=null;
       if(this.IsParentNode(input)){
          node=  this.FindRootNode(input.KnowledgeMapId);
          this.ParentNode=node;
          output=  this.BuildOuput(node, input);
       }
       else{
          node=  this.FindRootNode(input.KnowledgeMapId); 
          this.ParentNode=node;
          NodeItem nodeItem=this.FindNodeItem(node, input.NodeIdentity);
           output= this.BuildOuput(nodeItem, input);
          
       }
       if(!output.IsCollection){
     // this.SaveTestQuestion(input, output);
       ouputs.add(output);
       }
       else{
           TestItemGenerationOutputs outs=(TestItemGenerationOutputs)output;
           for(TestItemGenerationOutput o:outs.Items()){
             
                 ouputs.add(o);
           }
       }
       
       for(TestItemGenerationOutput e:ouputs){
          for(String s:e.LineItems){
              if(!items.contains(s) )
               items.add(s);
          }
       }
     // this.SaveTestQuestion(input, items);
     //  this.Save(input, output);
       return    ouputs;
    }
    
    protected Boolean CanSaveTestQuestion(Cognitiveleveltype congnitiveType,
            Questionnaturetype questionNatureType,Questiontype questionType,Test test,String questionText){
        Boolean canSave=true;
      
        String sql="select * from question  where " 
                + " Test_id=" + test.getTestId() +  " and Text=" + "'" + questionText + "'" ;
               
         List items= this.dataSource.Execute(sql);
         if(items.size()>0){
             canSave=false;
         }
        return canSave;  
    }
    
    protected Boolean Save(TestGenerationInput input,TestItemGenerationOutput output){
         int QuestionId=0;
     try{
             //Find test by id
      // this.dataSource.Open();
      // this.dataSource.BeginTransaction();
       Test test= (Test)this.dataSource.Find(Test.class, new Integer(input.TestId));
        //Find the conginitive level type
      Cognitiveleveltype cognitiveType=(Cognitiveleveltype)this.dataSource.Find(Cognitiveleveltype.class, new Integer(input.CognitiveType));
        //Find Question Nature type
      Questionnaturetype questionNature=(Questionnaturetype)this.dataSource.Find(Questionnaturetype.class, new Integer(input.NatureOfItem));
        //Find Question type
       
      Questiontype questiontype=(Questiontype)this.dataSource.Find(Questiontype.class, new Integer(input.ItemType));
        Question q1=null;
       switch(questiontype.getQuestionType()) {
           case 1://true/false
           case 2:
             for(String s:output.LineItems){ 
                q1 = new Question();
                q1.setText(s);
                //q1.setTest(test);
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
                this.dataSource.Save(q1);
                QuestionId=q1.getQuestionId();
             }
             // this.dataSource.Commit();
            
             break;
           
           case 3: //MultipleChoice-MultipleAnswers
              Question  qqq = new Question();
                qqq.setText(output.Text);
               // qqq.setTest(test);
                qqq.setCognitiveleveltype(cognitiveType);
                qqq.setQuestiontype(questiontype);
                qqq.setQuestionnaturetype(questionNature);
                qqq.setText(output.Text);
               
               for(String a:output.LineItems){
                if(a.equals(output.Text)) continue;
                Questionlineitem item= new Questionlineitem();
                 item.setQuestion(qqq);
                  item.setText(a);
                  this.dataSource.Save(item);
               }
               this.dataSource.Save(qqq);
             
              // this.dataSource.Commit();
             
             break;  
               
           default:
            break;
              
       } 
      
        return true;
        }
        catch(Throwable ex){
           //  this.dataSource.Rollback();
            return false;
        }
        finally{
           // this.dataSource.Close();
            this.RemoveOphanQuestion(QuestionId);
        }
    }
    
    public void RemoveOphanQuestion(int questionId){
        try{
             //this.dataSource.Open();
           String sql="select * from questionlineitem where question_Id=" + questionId;
             String sqlDelete="Delete from question where QuestionId=" + questionId;
           List list=this.dataSource.Execute(sql);
           if(list.size()==0){
               this.dataSource.ExecuteNonQuery(sqlDelete);
           } 
          // this.dataSource.Commit();
        }
        catch(Throwable ex){
            //this.dataSource.Rollback();
        }
        finally{
          //  this.dataSource.Close();
        }
    }
     
   protected List<NodeItem> ExcludeNode(Node node){
         List<NodeItem> list= new ArrayList();
        for(NodeItem item :this.ParentNode.ListChildren() ){
            if(item.RootId!=node.Id){
                list.add(item);
            }
        }
      return list;
    }   
     
    protected List<NodeItem> ExcludeNode(NodeItem nodeItem){
         List<NodeItem> list= new ArrayList();
        for(NodeItem item :this.ParentNode.ListChildren() ){
            if(item.Identity!=nodeItem.Identity){
                list.add(item);
            }
        }
      return list;
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
    
  
    
  protected abstract TestItemGenerationOutput BuildOuput(NodeItem node,
       TestGenerationInput input);
  
  
 
  protected abstract TestItemGenerationOutput BuildOuput(Node node,
        TestGenerationInput input);
   
}

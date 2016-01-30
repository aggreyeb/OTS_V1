package OTS.DataModels;
// Generated Jan 28, 2016 10:02:56 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Testitem generated by hbm2java
 */
public class Testitem  implements java.io.Serializable {


     private Integer testItemId;
     private Cognitiveleveltype cognitiveleveltype;
     private Questionnaturetype questionnaturetype;
     private Questiontype questiontype;
     private Test test;
     private Integer questionBankId;
     private String text;
     private Float mark;
     private Set testitemoptions = new HashSet(0);
     private Set studenttestanswersheets = new HashSet(0);
     private Set testanswersheets = new HashSet(0);

    public Testitem() {
    }

    public Testitem(Cognitiveleveltype cognitiveleveltype, Questionnaturetype questionnaturetype, Questiontype questiontype, Test test, Integer questionBankId, String text, Float mark, Set testitemoptions, Set studenttestanswersheets, Set testanswersheets) {
       this.cognitiveleveltype = cognitiveleveltype;
       this.questionnaturetype = questionnaturetype;
       this.questiontype = questiontype;
       this.test = test;
       this.questionBankId = questionBankId;
       this.text = text;
       this.mark = mark;
       this.testitemoptions = testitemoptions;
       this.studenttestanswersheets = studenttestanswersheets;
       this.testanswersheets = testanswersheets;
    }
   
    public Integer getTestItemId() {
        return this.testItemId;
    }
    
    public void setTestItemId(Integer testItemId) {
        this.testItemId = testItemId;
    }
    public Cognitiveleveltype getCognitiveleveltype() {
        return this.cognitiveleveltype;
    }
    
    public void setCognitiveleveltype(Cognitiveleveltype cognitiveleveltype) {
        this.cognitiveleveltype = cognitiveleveltype;
    }
    public Questionnaturetype getQuestionnaturetype() {
        return this.questionnaturetype;
    }
    
    public void setQuestionnaturetype(Questionnaturetype questionnaturetype) {
        this.questionnaturetype = questionnaturetype;
    }
    public Questiontype getQuestiontype() {
        return this.questiontype;
    }
    
    public void setQuestiontype(Questiontype questiontype) {
        this.questiontype = questiontype;
    }
    public Test getTest() {
        return this.test;
    }
    
    public void setTest(Test test) {
        this.test = test;
    }
    public Integer getQuestionBankId() {
        return this.questionBankId;
    }
    
    public void setQuestionBankId(Integer questionBankId) {
        this.questionBankId = questionBankId;
    }
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    public Float getMark() {
        return this.mark;
    }
    
    public void setMark(Float mark) {
        this.mark = mark;
    }
    public Set getTestitemoptions() {
        return this.testitemoptions;
    }
    
    public void setTestitemoptions(Set testitemoptions) {
        this.testitemoptions = testitemoptions;
    }
    public Set getStudenttestanswersheets() {
        return this.studenttestanswersheets;
    }
    
    public void setStudenttestanswersheets(Set studenttestanswersheets) {
        this.studenttestanswersheets = studenttestanswersheets;
    }
    public Set getTestanswersheets() {
        return this.testanswersheets;
    }
    
    public void setTestanswersheets(Set testanswersheets) {
        this.testanswersheets = testanswersheets;
    }




}



package OTS.DataModels;
// Generated 10-Jun-2015 6:26:54 PM by Hibernate Tools 4.3.1



/**
 * Questionbank generated by hbm2java
 */
public class Questionbank  implements java.io.Serializable {


     private Integer questionBankId;
     private Cognitiveleveltype cognitiveleveltype;
     private Questionnaturetype questionnaturetype;
     private Questiontype questiontype;
     private Test test;
     private String questionText;
     private String groupId;

    public Questionbank() {
    }

    public Questionbank(Cognitiveleveltype cognitiveleveltype, Questionnaturetype questionnaturetype, Questiontype questiontype, Test test, String questionText, String groupId) {
       this.cognitiveleveltype = cognitiveleveltype;
       this.questionnaturetype = questionnaturetype;
       this.questiontype = questiontype;
       this.test = test;
       this.questionText = questionText;
       this.groupId = groupId;
    }
   
    public Integer getQuestionBankId() {
        return this.questionBankId;
    }
    
    public void setQuestionBankId(Integer questionBankId) {
        this.questionBankId = questionBankId;
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
    public String getQuestionText() {
        return this.questionText;
    }
    
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }




}



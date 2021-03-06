package OTS.DataModels;
// Generated 6-Feb-2016 4:41:34 PM by Hibernate Tools 4.3.1



/**
 * Answer generated by hbm2java
 */
public class Answer  implements java.io.Serializable {


     private Integer answerId;
     private Question question;
     private String text;

    public Answer() {
    }

    public Answer(Question question, String text) {
       this.question = question;
       this.text = text;
    }
   
    public Integer getAnswerId() {
        return this.answerId;
    }
    
    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }
    public Question getQuestion() {
        return this.question;
    }
    
    public void setQuestion(Question question) {
        this.question = question;
    }
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }




}



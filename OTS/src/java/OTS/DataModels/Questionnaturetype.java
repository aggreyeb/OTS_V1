package OTS.DataModels;
// Generated 6-Feb-2016 4:41:34 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Questionnaturetype generated by hbm2java
 */
public class Questionnaturetype  implements java.io.Serializable {


     private Integer questionNatureType;
     private String name;
     private Set testitems = new HashSet(0);
     private Set questions = new HashSet(0);

    public Questionnaturetype() {
    }

    public Questionnaturetype(String name, Set testitems, Set questions) {
       this.name = name;
       this.testitems = testitems;
       this.questions = questions;
    }
   
    public Integer getQuestionNatureType() {
        return this.questionNatureType;
    }
    
    public void setQuestionNatureType(Integer questionNatureType) {
        this.questionNatureType = questionNatureType;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set getTestitems() {
        return this.testitems;
    }
    
    public void setTestitems(Set testitems) {
        this.testitems = testitems;
    }
    public Set getQuestions() {
        return this.questions;
    }
    
    public void setQuestions(Set questions) {
        this.questions = questions;
    }




}



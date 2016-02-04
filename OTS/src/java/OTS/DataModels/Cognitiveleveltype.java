package OTS.DataModels;
// Generated Feb 3, 2016 3:28:45 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Cognitiveleveltype generated by hbm2java
 */
public class Cognitiveleveltype  implements java.io.Serializable {


     private Integer cognitiveLevel;
     private String name;
     private String description;
     private Set questions = new HashSet(0);
     private Set testitems = new HashSet(0);

    public Cognitiveleveltype() {
    }

	
    public Cognitiveleveltype(String name) {
        this.name = name;
    }
    public Cognitiveleveltype(String name, String description, Set questions, Set testitems) {
       this.name = name;
       this.description = description;
       this.questions = questions;
       this.testitems = testitems;
    }
   
    public Integer getCognitiveLevel() {
        return this.cognitiveLevel;
    }
    
    public void setCognitiveLevel(Integer cognitiveLevel) {
        this.cognitiveLevel = cognitiveLevel;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Set getQuestions() {
        return this.questions;
    }
    
    public void setQuestions(Set questions) {
        this.questions = questions;
    }
    public Set getTestitems() {
        return this.testitems;
    }
    
    public void setTestitems(Set testitems) {
        this.testitems = testitems;
    }




}



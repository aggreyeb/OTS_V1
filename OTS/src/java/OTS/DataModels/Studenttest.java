package OTS.DataModels;
// Generated 6-Feb-2016 4:41:34 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Studenttest generated by hbm2java
 */
public class Studenttest  implements java.io.Serializable {


     private Integer studentTestId;
     private Academiccourse academiccourse;
     private Test test;
     private User user;
     private Float mark;
     private String grade;
     private Boolean isTestCompleted;
     private Date dateCompleted;

    public Studenttest() {
    }

    public Studenttest(Academiccourse academiccourse, Test test, User user, Float mark, String grade, Boolean isTestCompleted, Date dateCompleted) {
       this.academiccourse = academiccourse;
       this.test = test;
       this.user = user;
       this.mark = mark;
       this.grade = grade;
       this.isTestCompleted = isTestCompleted;
       this.dateCompleted = dateCompleted;
    }
   
    public Integer getStudentTestId() {
        return this.studentTestId;
    }
    
    public void setStudentTestId(Integer studentTestId) {
        this.studentTestId = studentTestId;
    }
    public Academiccourse getAcademiccourse() {
        return this.academiccourse;
    }
    
    public void setAcademiccourse(Academiccourse academiccourse) {
        this.academiccourse = academiccourse;
    }
    public Test getTest() {
        return this.test;
    }
    
    public void setTest(Test test) {
        this.test = test;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Float getMark() {
        return this.mark;
    }
    
    public void setMark(Float mark) {
        this.mark = mark;
    }
    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public Boolean getIsTestCompleted() {
        return this.isTestCompleted;
    }
    
    public void setIsTestCompleted(Boolean isTestCompleted) {
        this.isTestCompleted = isTestCompleted;
    }
    public Date getDateCompleted() {
        return this.dateCompleted;
    }
    
    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }




}



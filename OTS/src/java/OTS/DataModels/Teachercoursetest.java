package OTS.DataModels;
// Generated 6-Feb-2016 4:41:34 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Teachercoursetest generated by hbm2java
 */
public class Teachercoursetest  implements java.io.Serializable {


     private Integer courseTestId;
     private Academiccourse academiccourse;
     private Test test;
     private User user;
     private Date createdOn;

    public Teachercoursetest() {
    }

	
    public Teachercoursetest(Academiccourse academiccourse, Test test, User user) {
        this.academiccourse = academiccourse;
        this.test = test;
        this.user = user;
    }
    public Teachercoursetest(Academiccourse academiccourse, Test test, User user, Date createdOn) {
       this.academiccourse = academiccourse;
       this.test = test;
       this.user = user;
       this.createdOn = createdOn;
    }
   
    public Integer getCourseTestId() {
        return this.courseTestId;
    }
    
    public void setCourseTestId(Integer courseTestId) {
        this.courseTestId = courseTestId;
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
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }




}



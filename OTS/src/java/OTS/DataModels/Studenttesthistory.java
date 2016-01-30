package OTS.DataModels;
// Generated Jan 28, 2016 10:02:56 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Studenttesthistory generated by hbm2java
 */
public class Studenttesthistory  implements java.io.Serializable {


     private Integer studentTestHistoryId;
     private Integer testId;
     private Integer studentId;
     private Date startDate;
     private Date endDate;
     private Float totalMark;

    public Studenttesthistory() {
    }

    public Studenttesthistory(Integer testId, Integer studentId, Date startDate, Date endDate, Float totalMark) {
       this.testId = testId;
       this.studentId = studentId;
       this.startDate = startDate;
       this.endDate = endDate;
       this.totalMark = totalMark;
    }
   
    public Integer getStudentTestHistoryId() {
        return this.studentTestHistoryId;
    }
    
    public void setStudentTestHistoryId(Integer studentTestHistoryId) {
        this.studentTestHistoryId = studentTestHistoryId;
    }
    public Integer getTestId() {
        return this.testId;
    }
    
    public void setTestId(Integer testId) {
        this.testId = testId;
    }
    public Integer getStudentId() {
        return this.studentId;
    }
    
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Float getTotalMark() {
        return this.totalMark;
    }
    
    public void setTotalMark(Float totalMark) {
        this.totalMark = totalMark;
    }




}



package OTS.DataModels;
// Generated Feb 4, 2016 2:42:16 PM by Hibernate Tools 4.3.1



/**
 * Testanswersheet generated by hbm2java
 */
public class Testanswersheet  implements java.io.Serializable {


     private Integer testAnswerSheetId;
     private Test test;
     private Testitem testitem;
     private Integer lineNumber;
     private Boolean a;
     private Boolean b;
     private Boolean c;
     private Boolean d;

    public Testanswersheet() {
    }

    public Testanswersheet(Test test, Testitem testitem, Integer lineNumber, Boolean a, Boolean b, Boolean c, Boolean d) {
       this.test = test;
       this.testitem = testitem;
       this.lineNumber = lineNumber;
       this.a = a;
       this.b = b;
       this.c = c;
       this.d = d;
    }
   
    public Integer getTestAnswerSheetId() {
        return this.testAnswerSheetId;
    }
    
    public void setTestAnswerSheetId(Integer testAnswerSheetId) {
        this.testAnswerSheetId = testAnswerSheetId;
    }
    public Test getTest() {
        return this.test;
    }
    
    public void setTest(Test test) {
        this.test = test;
    }
    public Testitem getTestitem() {
        return this.testitem;
    }
    
    public void setTestitem(Testitem testitem) {
        this.testitem = testitem;
    }
    public Integer getLineNumber() {
        return this.lineNumber;
    }
    
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }
    public Boolean getA() {
        return this.a;
    }
    
    public void setA(Boolean a) {
        this.a = a;
    }
    public Boolean getB() {
        return this.b;
    }
    
    public void setB(Boolean b) {
        this.b = b;
    }
    public Boolean getC() {
        return this.c;
    }
    
    public void setC(Boolean c) {
        this.c = c;
    }
    public Boolean getD() {
        return this.d;
    }
    
    public void setD(Boolean d) {
        this.d = d;
    }




}



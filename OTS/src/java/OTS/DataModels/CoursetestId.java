package OTS.DataModels;
// Generated Dec 20, 2014 11:09:02 AM by Hibernate Tools 4.3.1



/**
 * CoursetestId generated by hbm2java
 */
public class CoursetestId  implements java.io.Serializable {


     private String studentId;
     private String testId;

    public CoursetestId() {
    }

    public CoursetestId(String studentId, String testId) {
       this.studentId = studentId;
       this.testId = testId;
    }
   
    public String getStudentId() {
        return this.studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getTestId() {
        return this.testId;
    }
    
    public void setTestId(String testId) {
        this.testId = testId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CoursetestId) ) return false;
		 CoursetestId castOther = ( CoursetestId ) other; 
         
		 return ( (this.getStudentId()==castOther.getStudentId()) || ( this.getStudentId()!=null && castOther.getStudentId()!=null && this.getStudentId().equals(castOther.getStudentId()) ) )
 && ( (this.getTestId()==castOther.getTestId()) || ( this.getTestId()!=null && castOther.getTestId()!=null && this.getTestId().equals(castOther.getTestId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getStudentId() == null ? 0 : this.getStudentId().hashCode() );
         result = 37 * result + ( getTestId() == null ? 0 : this.getTestId().hashCode() );
         return result;
   }   


}



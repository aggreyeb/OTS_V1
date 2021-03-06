package OTS.DataModels;
// Generated Dec 20, 2014 11:09:02 AM by Hibernate Tools 4.3.1



/**
 * CourseregistrationId generated by hbm2java
 */
public class CourseregistrationId  implements java.io.Serializable {


     private int studentId;
     private int courseId;

    public CourseregistrationId() {
    }

    public CourseregistrationId(int studentId, int courseId) {
       this.studentId = studentId;
       this.courseId = courseId;
    }
   
    public int getStudentId() {
        return this.studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public int getCourseId() {
        return this.courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CourseregistrationId) ) return false;
		 CourseregistrationId castOther = ( CourseregistrationId ) other; 
         
		 return (this.getStudentId()==castOther.getStudentId())
 && (this.getCourseId()==castOther.getCourseId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getStudentId();
         result = 37 * result + this.getCourseId();
         return result;
   }   


}



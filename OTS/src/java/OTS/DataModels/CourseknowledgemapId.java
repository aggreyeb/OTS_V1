package OTS.DataModels;
// Generated 6-Feb-2016 4:41:34 PM by Hibernate Tools 4.3.1



/**
 * CourseknowledgemapId generated by hbm2java
 */
public class CourseknowledgemapId  implements java.io.Serializable {


     private int courseId;
     private int knowledgeMapId;

    public CourseknowledgemapId() {
    }

    public CourseknowledgemapId(int courseId, int knowledgeMapId) {
       this.courseId = courseId;
       this.knowledgeMapId = knowledgeMapId;
    }
   
    public int getCourseId() {
        return this.courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public int getKnowledgeMapId() {
        return this.knowledgeMapId;
    }
    
    public void setKnowledgeMapId(int knowledgeMapId) {
        this.knowledgeMapId = knowledgeMapId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CourseknowledgemapId) ) return false;
		 CourseknowledgemapId castOther = ( CourseknowledgemapId ) other; 
         
		 return (this.getCourseId()==castOther.getCourseId())
 && (this.getKnowledgeMapId()==castOther.getKnowledgeMapId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getCourseId();
         result = 37 * result + this.getKnowledgeMapId();
         return result;
   }   


}



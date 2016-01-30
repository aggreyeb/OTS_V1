/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.Callback;
import OTS.CourseId;
import OTS.DataModels.Academiccourse;
import OTS.Department;
import OTS.KnowledgeMapId;
import OTS.TeacherId;

/**
 *
 * @author MEA
 */
public class GraduateCourse  {
    Academiccourse accademicCourse;
    Department department;
    CourseId courseId;
    KnowledgeMapId  knowledgeMapId;
    TeacherId teacherId;
    public GraduateCourse(Department department,CourseId courseId) {
        this.department = department;
    }

   
    public Boolean Exist(){
    
       return   department.HasCourse(this.courseId);
    }
    
    public void Create(Academiccourse academicCourse){
       if(academicCourse!=null){
          this.accademicCourse=academicCourse;
       }
       else{
           
       }
    }
  
    public void Modify( Academiccourse academicCourse) {
        if(academicCourse!=null){
          this.accademicCourse=academicCourse;
          this.department.ModifyCourse(academicCourse);
       }
       else{
           
       }
    }

    public void AssignKnowledgemap( KnowledgeMapId  knowledgeMapId,Response response){
         Callback callback=null;
        if(  knowledgeMapId !=null) {
          
            callback= new TransactionCallback();
          department.Associate(courseId,knowledgeMapId,callback);
        }
        else{
           callback.WriteDescription(response);
        }
        
    }
    
    public void UnAssignKnowledgemap( KnowledgeMapId  knowledgeMapId,Response response){
          Callback callback=null;
           callback= new TransactionCallback();
         if(knowledgeMapId !=null){
           this.knowledgeMapId=knowledgeMapId;
            department.DeAsssociate(courseId, knowledgeMapId,callback);
        }
        else{
         callback.WriteDescription(response);
        }
      
    }

   
}

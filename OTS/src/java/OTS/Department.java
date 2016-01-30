/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS;

import OTS.DataModels.Academiccourse;
import OTS.DataModels.Knowledgemap;

/**
 *
 * @author MEA
 */
public abstract  class Department {
  public abstract void Create(University univeristy);
  public abstract void CreateCourse(Academiccourse academicCourse);
  public abstract void RegisterStudentCourse(Identity studentId, Identity CourseId);
  public abstract void DropStudentCourse(Identity studentId, Identity CourseId);
  public abstract void  AssignRole(Identity roleIdentity);
  public abstract void AssignTeacherCourse(TeacherId id,Academiccourse academicCourse);
  public abstract void UnassignTeacherCourse(TeacherId teacherId, KnowledgeMapId knowledgeMapId); 
  public abstract void Associate(CourseId courseId, KnowledgeMapId knowledgeMapid,Callback callback) ;
  public abstract void DeAsssociate(CourseId courseId, KnowledgeMapId knowledgeMapId,Callback callback); 
  public abstract void ModifyCourse(Academiccourse academicCourse);   
  public abstract Boolean HasKnowledgeMapAssigment(CourseId courseId, KnowledgeMapId knowledgeMapId) ;

   public Boolean HasCourse(CourseId courseId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   
}

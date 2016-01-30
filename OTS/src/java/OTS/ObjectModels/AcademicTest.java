/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels;

import OTS.Message;

/**
 *
 * @author MEA
 */
public class AcademicTest implements IAcademicTest {

    AcademicTests accademicTests;
    public  AcademicTest(AcademicTests accademicTests){
        this.accademicTests=accademicTests;
    }
    @Override
    public void Save(int courseId, int teacherId, AcademicTestDescription desc, Message callBackMessage) {
          if(this.CanSave(courseId,teacherId)){
               this.accademicTests.CreateNew(courseId, teacherId, desc, callBackMessage);
          }
          else{
            callBackMessage.ChangeStatus("fail");
            callBackMessage.ChangeContent("");
            callBackMessage.UpdateError("Bussiness Rule: Invalid CourseId or TeacherId");
          }
    }

    @Override
    public void Modify(AcademicTestDescription desc, Message callBackMessage) {
         if(this.CanModify(desc)){
             this.accademicTests.Update(desc, callBackMessage);
         }
         else{
            callBackMessage.ChangeStatus("fail");
            callBackMessage.ChangeContent("");
            callBackMessage.UpdateError("Bussiness Rule: Invalid Test Id");
         }
    }

    @Override
    public void Delete(int testId, Message callBackMessage) {
      
         if(this.CanDelete(testId)){
             this.accademicTests.Delete(testId, callBackMessage);
         }
         else{
            callBackMessage.ChangeStatus("fail");
            callBackMessage.ChangeContent("");
            callBackMessage.UpdateError("Bussiness Rule: Invalid Test Id");
         }
    }
    
    
    protected Boolean CanSave(int courseId, int teacherId){
        
        if(courseId>0 && teacherId>0){
            return true;
        }
        return false;
    }
    
    
    protected Boolean CanModify(AcademicTestDescription desc){
         if(desc.TestId >0){
          return true;
         }
         return false;
    }
    
     protected Boolean CanDelete(int testId){
        
        if(testId>0){
            return true;
        }
        return false;
    }

    @Override
    public void Activate(int testId, Message callBackMessage) {
        this.accademicTests.ActivateTest(testId, callBackMessage);
    }

    @Override
    public void DeActivate(int testId, Message callBackMessage) {
         this.accademicTests.DeActivateTest(testId, callBackMessage);
    }

    @Override
    public void Mark(int testId, Message callBackMessage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

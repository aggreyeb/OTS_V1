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
public interface IAcademicTest {
  public void Save(int courseId, int teacherId,AcademicTestDescription desc,Message callBackMessage );
  public void Modify(AcademicTestDescription desc,Message callBackMessage);
  public void Delete(int testId,Message callBackMessage);
  public void Activate(int testId,Message callBackMessage);
  public void DeActivate(int testId,Message callBackMessage);
  public void Mark(int testId,Message callBackMessage);
}

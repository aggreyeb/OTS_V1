/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels;

import java.util.Date;

/**
 *
 * @author MEA
 */
public class StudentRegistedCourseItem {
    public int StudentCourseId;
    public int CourseTypeId;
    public String Number;
    public String CourseName;
    public int TestId;
    public String TestName;
    public Date StartDate;
    public String StartTime;
    public String EndTime;
    public String Professor;
    public Boolean IsActivated;
    public Boolean IsTestTeken;
    public String TestScore="Unknown";
}




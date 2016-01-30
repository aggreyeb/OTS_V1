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
public class AcademicTestDescription {
     public Integer TestId;
     public String Name;
     public Float TotalMark;
     public Integer NumberOfQuestion;
     public Date StartDate;
     public String StartTime;
     public String EndTime;
     public Boolean IsActivated;
     public String ActivatedStyle="";
     public Boolean IsAllMarked=false;
     public String MarkTestStyle="";
   
}

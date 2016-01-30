/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.DataModels;

import java.util.Date;

/**
 *
 * @author MEA
 */
public class KnowledgeMapDescription {
     public int KnowledgeMapId;
     public int CreatedBy;
     public String Name;
     public String Description;
     public Date CreateOn;
     public String Concepts;
     public Date LastUpdated;
     public Boolean IsPublic;
     public Boolean IsSelected;
     
}

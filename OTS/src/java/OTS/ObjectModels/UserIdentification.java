/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.Identification;

/**
 *
 * @author MEA
 */
public class UserIdentification  extends Identification{
    
    private UserId userId;
    private int userTypeId;
    private int departamentId;

  
    public void  Update(UserId userId, int userTypeId){
        this.userId=userId;
        this.userTypeId=userTypeId;
    }
    
    public void  Update(UserId userId, int userTypeId, int departamentId){
        this.userId=userId;
        this.userTypeId=userTypeId;
        this.departamentId=departamentId;
    }
    public UserId getId() {
        return userId;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public int getDepartamentId() {
        return departamentId;
    }
    
    
}

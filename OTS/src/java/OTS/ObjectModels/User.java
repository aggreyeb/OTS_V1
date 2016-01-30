/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.DataModels.Useraccount;
import OTS.IUserIdentity;
import OTS.Identification;

/**
 *
 * @author MEA
 */
public class User implements IUserIdentity {
  private final UserId userId;
  private final int userType ;
  private int departmentId;
  private Users users;

    public User(UserId userId, int userType, Users users) {
        this.userId = userId;
        this.userType = userType;
        this.users = users;
    }

    public User(UserId userId, int userType, int departmentId, Users users) {
        this.userId = userId;
        this.userType = userType;
        this.departmentId = departmentId;
        this.users = users;
    }

    User(Useraccount ua, String FirstName, String LastName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
  
   
   public Boolean Exit(){
         return true;
   }

    @Override
    public void Show(Identification identification) {
        identification.Update(userId, userType, departmentId);
    }

}

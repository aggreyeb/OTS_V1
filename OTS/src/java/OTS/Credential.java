/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS;

import OTS.ObjectModels.UserProfile;
import com.google.gson.Gson;

/**
 *
 * @author MEA
 */
public final class Credential {
    public String UserName;
    public String Password;
    public String  Status;
    public UserProfile  userProfile;
    public Credential(String userName,String password){
        this.UserName=userName;
        this.Password=password;
    }
    
    public void OnAthenticated(UserProfile userProfile,String status){
          this.Status=status;
          this.userProfile=userProfile;
    }
    public void OnFailAthenticated(String status){
          this.Status=status;
    }
    
   
}

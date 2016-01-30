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
public class AuthenticationResponse extends UserProfile {
   
    public String Status;

    public AuthenticationResponse(Credential credential) {
         
        if(credential.Status.equals("ok")){
         this.DepartmentName=credential.userProfile.DepartmentName;
         this.DepartmentTypeId=credential.userProfile.DepartmentTypeId;
         this.FirstName=credential.userProfile.FirstName;
         this.LastName=credential.userProfile.LastName;
         this.HomePageName=credential.userProfile.HomePageName;
         this.UserAccountId=credential.userProfile.UserAccountId;
         this.UserId=credential.userProfile.UserId;
         this.UserTypeId=credential.userProfile.UserTypeId;
         this.UserTypeName=credential.userProfile.UserTypeName;
        }
      this.Status=credential.Status;
    }
    
    public String ToJsonResponse(){
    String s="";
    Gson g= new Gson();
     s= g.toJson(this);
      return s;
    
    }
}

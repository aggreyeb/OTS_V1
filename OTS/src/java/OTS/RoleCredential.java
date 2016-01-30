/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS;

/**
 *
 * @author MEA
 */
public final class RoleCredential {
    public final String UserName;
    public final String Password;
    public final int RoleTypeId;
    public final String FirstName;
    public final String LastName;
    public final String MiddelName;
    
    public RoleCredential(Credential credential,int roleTypeId,PersonName personName){
        this.UserName=credential.UserName;
        this.Password=credential.Password;
        this.RoleTypeId=roleTypeId;
        this.FirstName=personName.FirstName;
        this.LastName=personName.LastName;
        this.MiddelName=personName.MiddleName;
    }
}

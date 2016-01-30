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
public final class PersonName {
    public String FirstName;
    public String LastName;
    public String MiddleName;
    public PersonName(String firstName,String middleName, String lastName) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.MiddleName=middleName;  
    }
    
}

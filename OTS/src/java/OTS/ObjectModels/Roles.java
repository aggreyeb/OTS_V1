/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.DataModels.DataSource;
import OTS.IListable;
import OTS.Message;

/**
 *
 * @author MEA
 */
public class Roles {
    private final int userId;
    private final Message message;
    private final DataSource dataSource;
    private IListable listable;

    public Roles(int userId,  DataSource dataSource,Message message) {
        this.userId = userId;
        this.message = message;
        this.dataSource = dataSource;
    }
    
    
    public Boolean HasRole(int userId,int userTypeId){
    
        return true;
    
    }
   
 
}

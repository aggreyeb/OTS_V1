/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.IListable;
import OTS.Message;

/**
 *
 * @author MEA
 */
public class Role {
    private int userId;
    private Message message ;

    public Role(int userId, Message message) {
        this.userId = userId;
        this.message = message;
    }
    
    public void ListKnowledgeMap(IListable listable){
        try{
           listable.List(userId);
            String result=  listable.ToJson();
            message.ChangeContent(result);
             message.ChangeStatus("ok");
        
        }
        catch(Throwable ex){
            message.ChangeStatus("fail");
            message.ChangeContent("");
            message.UpdateError(ex.toString());
        }
       
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.Session;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MEA
 */
public class UserSession implements Session{
    int userId;
    Date date;
    HttpServletRequest request;
    HttpServletResponse response;
    public UserSession(int userId,HttpServletRequest request) {
        this.userId = userId;
        this.request=request;
        this.response=response;
    }
    
    
    @Override
    public void Start(Date date) {
      this.date=date;
    }

    @Override
    public void End() {
     this.userId=-1;
     request.getSession().invalidate();
    
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author MEA
 */
public class DatabaseTest {
    
    
    public Boolean ExecuteCommand(){
        
        
        //Open the hibernate session
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.getTransaction();
       
        try
        {
            //Update record using named query
            tx.begin();
            //Object role = session.get(OTS.ObjectModels.States.Role.class, new Integer(1));
           // query.executeUpdate();
          // ArrayList list= query.list();
            tx.commit();
            return true;
        }
        finally
        {
           session.close();
         
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.DataModels;

import App.NewHibernateUtil;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author MEA
 */
public final class MySqlDataSource extends DataSource{

  SessionFactory sessionFactory=null;
    public MySqlDataSource() {
     
    }
    
    @Override
    public void Save(Object object) {
         Session session=null;
         Transaction  tx=null;
        try{
              sessionFactory =NewHibernateUtil.getSessionFactory();
             session=  sessionFactory.openSession();
             tx= session.getTransaction();
            tx.begin();
             session.save(object);
             tx.commit();
        }
        catch(Throwable ex){
            if(tx!=null)
              tx.rollback();
        }
        finally{
            if(session!=null){
                session.close();
            }
          
        }
       
       
    }

    @Override
    public void Update(Object object) {
           Session session=null;
           Transaction  tx=null;
          try{
              sessionFactory =NewHibernateUtil.getSessionFactory();
             session=  sessionFactory.openSession();
             tx= session.getTransaction();
            tx.begin();
             session.update(object);
             tx.commit();
         }
         catch(Throwable ex){
             if(tx!=null)
              tx.rollback();
         }
         finally{
             if(session!=null){
                session.close();
            }
          
         }
       
    }

   @Override
    public Object Find(Class type, Serializable obj) {
        Session session=null;
         
          try{
               sessionFactory =NewHibernateUtil.getSessionFactory();
             session=  sessionFactory.openSession();
             return  session.get(type, obj);
         }
         finally{
              if(session!=null){
                session.close();
            }
           
              
         }
        
    }

    @Override
    public void ExecuteScalar(String sql, int[] returnValue) {
         Session session=null;
        try{
             sessionFactory =NewHibernateUtil.getSessionFactory();
             session=  sessionFactory.openSession();
            int value =-1; 
         Query query=session.createSQLQuery(sql);
         List result= query.list();
          value  = ((BigInteger )result.get(0)).intValue();
          returnValue[0]= value;  
         }
         finally{
            if(session!=null){
                session.close();
            }
          
         }
        
    }

    @Override
    public void ExecuteDataSet(String sql, List items) {
        Session session=null;
        try{
           sessionFactory =NewHibernateUtil.getSessionFactory();
             session=  sessionFactory.openSession();
             Query query=session.createSQLQuery(sql);
             items.addAll(query.list())  ;
         }
         finally{
              if(session!=null){
                session.close();
            }
           
         }
         
    
    }

    @Override
    public void ExecuteNonQuery(String sql) { 
           Session session=null;
           Transaction  tx=null;
          try{
              sessionFactory =NewHibernateUtil.getSessionFactory();
             session=  sessionFactory.openSession();
             tx= session.getTransaction();
             tx.begin();
             Query query=session.createSQLQuery(sql);
             query.executeUpdate(); 
             tx.commit();
          }
          catch(Throwable ex){
              if(tx!=null)
              {
                  tx.rollback();
              }
          }
          finally{
            if(session!=null){
                session.close();
            }
           
          }
       
    }

    @Override
    public void ExecuteDataSet(String sql, List<Object> entities,Object[] list) {
        Session session=null;
         
        try{
             sessionFactory =NewHibernateUtil.getSessionFactory();
             session=  sessionFactory.openSession();
            
              SQLQuery query=session.createSQLQuery(sql);
            for(Object a: entities){
               query.addEntity(a.getClass());
           }
              query.list().toArray(list);
         }
         finally{
             if(session!=null){
                session.close();
            }
            
         }
           
    }

    @Override
    public void Delete(Object object) {
          
         Session session=null;
           Transaction  tx=null;
        try{
              sessionFactory =NewHibernateUtil.getSessionFactory();
             session=  sessionFactory.openSession();
             tx= session.getTransaction();
             tx.begin();
             session.delete(object);
              tx.commit();
         }
         catch(Throwable ex){
             if(tx!=null){
                 tx.rollback();
             }
         }
         finally{
               if(session!=null){
                session.close();
            }
           
         }
       
    }

    @Override
    public void ExecuteCustomDataSet(String sql, List<?> items,Class<?> type) {   
          Session session=null;
        try{
              sessionFactory =NewHibernateUtil.getSessionFactory();
             session=  sessionFactory.openSession();
           Query query=session.createSQLQuery(sql);
           query.list();
           query.setResultTransformer(Transformers.aliasToBean(type));
           items.addAll(query.list());
         }
         finally{
           if(session!=null){
                session.close();
            }
         
         }
          
    } 
    
    @Override
    public List Execute(String sql) {   
        Session session=null;
        try{
              sessionFactory =NewHibernateUtil.getSessionFactory();
             session=  sessionFactory.openSession();
             Query query=session.createSQLQuery(sql);
             return query.list();
         }
         finally{
             if(session!=null){
                session.close();
            }
          
         }
       
    } 
    
   
}

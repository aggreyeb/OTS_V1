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
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.transform.Transformers;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author MEA
 */
public final class MySqlDataSource extends DataSource{

    private SessionFactory sessionFactory;
    private Transaction  tx;
     private  Session session;
     
    public MySqlDataSource() {
        //this.Create();
    }
    
    @Override
    public void Save(Object object) {
        try{
            this.Open();
            this.BeginTransaction();
             this.session.save(object);
             this.Commit();
        }
        catch(Throwable ex){
            this.Rollback();
        }
        finally{
          this.Close();
        }
       
       
    }

    @Override
    public void Update(Object object) {
         try{
             this.Open();
             this.BeginTransaction();
            this.session.update(object);
            this.Commit();
         }
         catch(Throwable ex){
             this.Rollback();
         }
         finally{
             this.Close();
         }
       
    }

   @Override
    public Object Find(Class type, Serializable obj) {
       
          try{
              this.Open();
             return  this.session.get(type, obj);
         }
         finally{
             this.Close();
         }
        
    }

  
     @Override
    public void BeginTransaction() {
     
        this.tx.begin();
    }

    @Override
    public void Rollback() {
       this.tx.rollback();
    }

    @Override
    public void Commit() {
      
        tx.commit();
    }
   

    @Override
    protected void Open() {
          
         // if(this.sessionFactory==null){
              //  this.Create();
          //  }
           this.sessionFactory= NewHibernateUtil.getSessionFactory();
            this.session= this.sessionFactory.openSession();
            this.tx=this.session.getTransaction();
           
            
    }

    @Override
    protected void Close() {
        
        if(this.session.isOpen()){
         this.session.close();
        
       }
        
    }

    @Override
    public void ExecuteScalar(String sql, int[] returnValue) {
       
        try{
            this.Open();
            int value =-1; 
         Query query=session.createSQLQuery(sql);
         List result= query.list();
          value  = ((BigInteger )result.get(0)).intValue();
          returnValue[0]= value;  
         }
         finally{
            this.Close();
         }
        
    }

    @Override
    public void ExecuteDataSet(String sql, List items) {
        try{
            this.Open();
             Query query=session.createSQLQuery(sql);
             items.addAll(query.list())  ;
         }
         finally{
             this.Close();
         }
         
    
    }

    @Override
    public void ExecuteNonQuery(String sql) { 
          try{
              this.Open();
              this.BeginTransaction();
             Query query=session.createSQLQuery(sql);
             query.executeUpdate(); 
             this.Commit();
          }
          finally{
              this.Close();
          }
       
    }

    @Override
    public void ExecuteDataSet(String sql, List<Object> entities,Object[] list) {
         try{
             this.Open();
              SQLQuery query=session.createSQLQuery(sql);
            for(Object a: entities){
               query.addEntity(a.getClass());
           }
              query.list().toArray(list);
         }
         finally{
            this.Close();
         }
           
    }

    @Override
    public void Delete(Object object) {
          try{
              this.Open();
              this.BeginTransaction();
              this.session.delete(object);
              this.Commit();
         }
         catch(Throwable ex){
             this.Rollback();
         }
         finally{
             this.Close();
         }
       
    }

    @Override
    public void ExecuteCustomDataSet(String sql, List<?> items,Class<?> type) {   
         try{
           this.Open();
           Query query=session.createSQLQuery(sql);
           query.list();
           query.setResultTransformer(Transformers.aliasToBean(type));
           items.addAll(query.list());
         }
         finally{
             this.Close();
         }
          
    } 
    
    @Override
    public List Execute(String sql) {   
         try{
             this.Open();
             Query query=session.createSQLQuery(sql);
             return query.list();
         }
         finally{
            this.Close();
         }
       
    } 
    
   
}

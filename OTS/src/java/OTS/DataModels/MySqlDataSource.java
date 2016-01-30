/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.DataModels;

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
        this.Create();
    }
    
    @Override
    public void Save(Object object) {
       
        this.session.save(object);
    }

    @Override
    public void Update(Object object) {
      
        
        this.session.update(object);
    }

   @Override
    public Object Find(Class type, Serializable obj) {
       
        return  this.session.get(type, obj);
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
     protected  void Create() {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
          this.sessionFactory=  new AnnotationConfiguration().configure().buildSessionFactory();
         
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    @Override
    public void Open() {
            this.session= this.sessionFactory.openSession();
            this.tx=this.session.getTransaction(); 
    }

    @Override
    public void Close() {
       if(this.session.isOpen()){
         this.session.close();
       }
       
    }

    @Override
    public void ExecuteScalar(String sql, int[] returnValue) {
       
         int value =-1; 
         Query query=session.createSQLQuery(sql);
         List result= query.list();
          value  = ((BigInteger )result.get(0)).intValue();
          returnValue[0]= value;
    }

    @Override
    public void ExecuteDataSet(String sql, List items) {
      
         Query query=session.createSQLQuery(sql);
         items.addAll(query.list())  ;
    
    }

    @Override
    public void ExecuteNonQuery(String sql) { 
          Query query=session.createSQLQuery(sql);
          query.executeUpdate();
    }

    @Override
    public void ExecuteDataSet(String sql, List<Object> entities,Object[] list) {
       
            SQLQuery query=session.createSQLQuery(sql);
            for(Object a: entities){
               query.addEntity(a.getClass());
           }
              query.list().toArray(list);
    }

    @Override
    public void Delete(Object object) {
        
        this.session.delete(object);
    }

    @Override
    public void ExecuteCustomDataSet(String sql, List<?> items,Class<?> type) {   
           Query query=session.createSQLQuery(sql);
           query.list();
           query.setResultTransformer(Transformers.aliasToBean(type));
           items.addAll(query.list());
    } 
    
    @Override
    public List Execute(String sql) {   
           Query query=session.createSQLQuery(sql);
          return query.list();
    } 
}

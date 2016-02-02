/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.DataModels;


import java.io.Serializable;
import java.util.List;

/**
 *
 * @author MEA
 */
public abstract class DataSource {
   
    public  abstract void Save(Object object);
    public  abstract void Update(Object object);
    public abstract void Delete(Object object);
    public  abstract Object Find(Class type,Serializable obj);
    public abstract void ExecuteScalar(String sql,int[] returnValue); 
    public abstract void ExecuteDataSet(String sql,List items);
    public abstract void ExecuteNonQuery(String sql);
    public abstract void ExecuteDataSet(String sql,List<Object> entities,Object[] list);
    public abstract void ExecuteCustomDataSet(String sql,List<?> items,Class<?> type);
    public abstract List Execute(String sql);
  
   
}

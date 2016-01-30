/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.UnitTesting;

import App.DatabaseSchema;
import App.DatabaseTest;
import App.NewHibernateUtil;
import OTS.DataModels.MySqlDataSource;
import OTS.ObjectModels.PublicUniversity;
import OTS.ObjectModels.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 * @author MEA
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void DatabaseConnection(){
    SessionFactory sf=   NewHibernateUtil.getSessionFactory();
     Session session=   sf.openSession();
    session.close();
    }
    
    
     @Test
    public void CreateSchema(){
       DatabaseSchema sc= new DatabaseSchema();
       sc.CreateTables();
    }
    
     @Test
    public void ExecuteNamedQery(){
       DatabaseTest ts= new DatabaseTest();
       ts.ExecuteCommand();
    }
    
    @Test
    public void MySqlDataSource(){
      MySqlDataSource ds= new MySqlDataSource();
      ds.BeginTransaction();
      ds.Rollback();
      
    }
   
    @Test
    public void UserProfile(){
          String userName="eb";
          String password="eb";
          MySqlDataSource ds= new MySqlDataSource();
          PublicUniversity u= new PublicUniversity(ds);
          UserProfile p=  u.LoadUserProfile(userName, password);
          System.out.print(p.FirstName + " " + p.LastName);
    }
}

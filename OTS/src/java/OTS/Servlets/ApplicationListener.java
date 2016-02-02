package OTS.Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import App.NewHibernateUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Eb
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContextListener contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
         //System.out.println("ServletContextListener contextDestroyed");
      if( NewHibernateUtil.getSessionFactory()!=null){
          NewHibernateUtil.getSessionFactory().close();
      }
    }
    
}

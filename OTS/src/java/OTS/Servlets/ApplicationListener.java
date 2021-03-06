package OTS.Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import App.NewHibernateUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.hibernate.SessionFactory;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;

/**
 *
 * @author Eb
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
      
        //  Log("contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
         //System.out.println("ServletContextListener contextDestroyed");
       try{
           closeSessionFactory(NewHibernateUtil.getSessionFactory());
       }
       catch(Throwable ex){
          if( !NewHibernateUtil.getSessionFactory().isClosed()){
          NewHibernateUtil.getSessionFactory().close();
        }
           
       }
    
      
    }
    
    private void closeSessionFactory(SessionFactory factory) { 
   if(factory instanceof SessionFactoryImpl) {
      SessionFactoryImpl sf = (SessionFactoryImpl)factory;
      ConnectionProvider conn = sf.getConnectionProvider();
      if(conn instanceof C3P0ConnectionProvider) { 
        ((C3P0ConnectionProvider)conn).close(); 
      }
   }
   factory.close();
}
    
    public void Log(String text){
        try {

			

			File file = new File("c:/junk/otslog.txt");
                     

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
                       
			bw.write(text);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

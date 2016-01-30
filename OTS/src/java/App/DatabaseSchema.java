package App;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author MEA
 */
public class DatabaseSchema {
   
    public void CreateTables(){
         Configuration cfg=new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SchemaExport se=new SchemaExport(cfg);
       
        se.create(true, true);

    }
}

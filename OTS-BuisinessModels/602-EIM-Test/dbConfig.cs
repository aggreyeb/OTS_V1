using System.Reflection;
using FluentNHibernate.Cfg;
using FluentNHibernate.Cfg.Db;
using NHibernate;
using NHibernate.Tool.hbm2ddl;

namespace FFM.DataAccessModel
{
    public class DbConfig
    {

        public static ISession Session()
        {
            return CreateSessionFactory().OpenSession();
        }
        /*
         private  static ISessionFactory CreateSessionFactory()
         {
             ISessionFactory sessionFactory = Fluently.Configure()
            .Database(MsSqlConfiguration.MsSql2008
            .ConnectionString(m => m.Server(@"MEA-PC\MEADB")
            .Database("testdb")
            .TrustedConnection()))
            .Mappings(m =>
            m.FluentMappings.AddFromAssembly(Assembly.GetExecutingAssembly()))
           //.ExposeConfiguration(config => new SchemaExport(config).Create(false, true))
            .BuildSessionFactory();
            return sessionFactory;
         }
        */
        /*
         private static ISessionFactory CreateSessionFactory()
         {
             ISessionFactory sessionFactory = Fluently.Configure()
                         .Database(PostgreSQLConfiguration.PostgreSQL82.ShowSql().Raw("hibernate.generate_statistics", "true")
                         .ConnectionString(c => c
                         .Host("localhost")
                         .Port(5432)
                         .Database("eimdb")
                         .Username("postgres")
                         .Password("agg12kzz")))
                         .Mappings(x => x.FluentMappings.AddFromAssembly(Assembly.GetExecutingAssembly()))
                         .BuildSessionFactory();
             return sessionFactory;
         }
        */

        //My SQL Provider
         private static ISessionFactory CreateSessionFactory()
         {
             ISessionFactory sessionFactory = Fluently.Configure()
                         .Database(MySQLConfiguration.Standard.ShowSql()
                         .ConnectionString(c => c
                         .Server("localhost")
                         .Database("OTSDb")
                         .Username("appdbuser")
                         .Password("appdbuser")))
                        // .Username("root")
                         //.Password("root")))
                         .Mappings(x => x.FluentMappings.AddFromAssembly(Assembly.GetExecutingAssembly()))
                         .BuildSessionFactory();
             return sessionFactory;
         }
    }
}
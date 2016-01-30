using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Globalization;
using System.Linq;
using System.Text;
using FFM.DataAccessModel;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using NHibernate;
using _602_EIMDomainModel;

namespace FFM.Test
{
    [TestClass]
    public class DatabaseConnectionTest
    {

        [TestMethod]
        public void CreateDatabaseSchema_Test()
        {
            DbSehema.Generate();
        }


        [TestMethod]
        public void DatabaseConnection_Test()
        {
            ISession session = DbConfig.Session();
       
        }

        /*
        [TestMethod]
        public void AddUser_Test()
        {
            ISession session = null;
            ITransaction tx = null;
            try
            {
                session = DbConfig.Session();
                tx = session.BeginTransaction();
                User user = new User{
                              UserName ="username",Password ="password"
                        };
                session.Save(user);
                tx.Commit();
                Trace.Write("==========Person Saved =============== ");
            }
            catch (Exception ex)
            {
                if (tx != null) tx.Rollback();
                Trace.Write(ex.ToString());
            }
            finally
            {
                if (session != null) session.Close();
            }
        }
        */
    }
}

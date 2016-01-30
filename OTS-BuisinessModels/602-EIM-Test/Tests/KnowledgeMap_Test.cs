using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OTS.DataModel;
using NHibernate;
using FFM.DataAccessModel;
using System.Diagnostics;
using System.Xml.Serialization;
using System.IO;
using System.Runtime.Serialization;

namespace _602_EIM_Test.Tests
{
    [TestClass]
    public  class KnowledgeMap_Test
    {
        [TestMethod]
        public void SaveKnowlegeMap()
        {
            ISession session = null;
            ITransaction tx = null;
            try
            {
                session = DbConfig.Session();
                tx = session.BeginTransaction();
                
                tx.Commit();
                Trace.Write("==========Knowledge Map Saved =============== ");
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



        [TestMethod]
        public void AddConceptNodeToLeafKnowlegeMap()
        {
            ISession session = null;
            ITransaction tx = null;
            try
            {
                session = DbConfig.Session();
                tx = session.BeginTransaction();
               
             
                tx.Commit();
                Trace.Write("==========Knowledge Map Updates =============== ");
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

       


    }
}

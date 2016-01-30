using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OTS.DataModel;
using NHibernate;
using System.Diagnostics;
using FFM.DataAccessModel;

namespace _602_EIM_Test.Tests
{
    [TestClass]
    public class SeedLookupTablesTest
    {
        [TestMethod]
        public void SeedLookupTables()
        {
            ISession session = null;
            ITransaction tx = null;
            try
            {
                session = DbConfig.Session();
                tx = session.BeginTransaction();

                QuestionType questionType1 = new QuestionType() { Name = "TrueOrFalse" };
                QuestionType questionType2 = new QuestionType() { Name = "Multiple Choice" };
              //  QuestionType questionType3 = new QuestionType() { Name = "Short Answer" };

                session.Save(questionType1);
                session.Save(questionType2);
               // session.Save(questionType3);

                QuestionNatureType questionNatureType1 = new QuestionNatureType() { Name = "Correct" };
                QuestionNatureType questionNatureType2 = new QuestionNatureType() { Name = "Negative" };
                QuestionNatureType questionNatureType3 = new QuestionNatureType() { Name = "Incorrect" };
                QuestionNatureType questionNatureType4 = new QuestionNatureType() { Name = "Negative-Incorrect"};

                session.Save(questionNatureType1);
                session.Save(questionNatureType2);
                session.Save(questionNatureType3);
                session.Save(questionNatureType4);

                tx.Commit();
                Trace.Write("==========Successfully Done!!! =============== ");
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

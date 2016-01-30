using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using NHibernate;
using OTS.DataModel;
using System.Diagnostics;
using FFM.DataAccessModel;

namespace _602_EIM_Test.Tests
{
    [TestClass]
    public class CourseKnowledgeMapTest
    {
        [TestMethod]
        public void AsignCourseKnowlegeMap() {
            ISession session = null;
            ITransaction tx = null;
            try
            {
                session = DbConfig.Session();
                tx = session.BeginTransaction();
                int   TeacherId=1;
                var academicCourse = session.Load<AcademicCourse>(1);
                var academicCourse2 = session.Load<AcademicCourse>(2);
                KnowledgeMap  km= session.Load<KnowledgeMap>(1);

                CourseKnowledgeMap courseKnowledge = new CourseKnowledgeMap()
                {
                    CourseId = academicCourse.Id,
                    KnowledgeMapId = km.Id,
                    AssignBy=TeacherId,
                    AssignOn = DateTime.Now
                };

                session.Save(courseKnowledge);
           
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

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
    public class CourseAssignmentTest
    {
        [TestMethod]
        public void AssignTeacherCourse() {
            ISession session = null;
            ITransaction tx = null;
            try
            {
                session = DbConfig.Session();
                tx = session.BeginTransaction();

                var academicCourse = session.Load<AcademicCourse>(1);

                CourseAssignment ca1 = new CourseAssignment()
                                       {  
                                          TeacherId=1,
                                          CourseId = academicCourse, 
                                          AssignOn=DateTime.Now, 
                                          IsCompleted=false};

                var academicCourse2 = session.Load<AcademicCourse>(2);
                CourseAssignment ca2 = new CourseAssignment()
                {
                    TeacherId = 1,
                    CourseId = academicCourse2,
                    AssignOn = DateTime.Now,
                    IsCompleted = false
                };

                session.Save(ca1);
                session.Save(ca2);
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

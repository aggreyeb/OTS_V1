using FluentNHibernate.Mapping;
using OTS.DataModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _602_EIM_Test.Maps
{
    public  class StudentTestMap:ClassMap<StudentTest>
    {
        public StudentTestMap()
        {
            Table("StudentTest");
            Id(x => x.StudentTestId).GeneratedBy.Identity().Column("StudentTestId");
            Map(x => x.Mark);
            Map(x => x.Grade);
            Map(x => x.IsTestCompleted);
            Map(x => x.DateCompleted);
            References(x => x.Student).Column("StudentId");
            References(x => x.Course).Column("CourseId") ;
            References(x => x.Test).Column("TestId");

        }
    }
}

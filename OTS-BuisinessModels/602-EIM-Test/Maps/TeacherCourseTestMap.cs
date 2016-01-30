using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OTS.DataModel;
using FluentNHibernate.Mapping;

namespace _602_EIM_Test.Maps
{
    
    public class TeacherCourseTestMap:ClassMap<TeacherCourseTest>
    {
        public TeacherCourseTestMap() {
            Table("TeacherCourseTest");
            Id(x => x.Id).GeneratedBy.Identity().Column("CourseTestId");
            Map(x => x.CreatedOn);
            References(x => x.Course).Not.Nullable().Column("CourseId");
            References(x => x.Test).Not.Nullable().Column("TestId");
            References(x => x.CreatedBy).Not.Nullable().Column("TeacherId");
         
        }
    }
   
}

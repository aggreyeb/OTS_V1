using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OTS.DataModel;
using FluentNHibernate.Mapping;

namespace _602_EIM_Test.Maps
{

    public class StudentCourseRegistrationMap : ClassMap<StudentCourseRegistration>
    {
        public StudentCourseRegistrationMap()
        {
            Table("StudentCourseRegistration");
            Id(x => x.Id).GeneratedBy.Identity().Column("StudentCourseId");
            References(x => x.StudentId).Not.Nullable().Column("StudentId");
            References(x => x.CourseId).Not.Nullable().Column("CourseId");
            /*
            CompositeId( )
            .KeyProperty(x => x.StudentId)
            .KeyReference(x => x.CourseId);
             */
            Map(x => x.Date);
        
           
        }
    }
}

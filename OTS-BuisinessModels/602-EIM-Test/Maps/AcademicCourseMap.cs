using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OTS.DataModel;
using FluentNHibernate.Mapping;

namespace _602_EIM_Test.Maps
{
    public class AcademicCourseMap:ClassMap<AcademicCourse>
    {
        public AcademicCourseMap() {
            Table("AcademicCourse");
            Id(x => x.Id).GeneratedBy.Identity().Column("CourseTypeId");
            Map(x=>x.Number).Length(15);
            Map(x => x.Name).Length(100);
            Map(x => x.Description).Length(100);
        }
    }
}

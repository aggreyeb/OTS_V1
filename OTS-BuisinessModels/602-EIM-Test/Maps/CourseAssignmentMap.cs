using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OTS.DataModel;
using FluentNHibernate.Mapping;

namespace _602_EIM_Test.Maps
{

    public class CourseAssignmentMap : ClassMap<CourseAssignment>
    {
        public CourseAssignmentMap()
        {
            Table("CourseAssignment");
            CompositeId( )
            .KeyProperty(x => x.TeacherId)
            .KeyReference(x => x.CourseId);
            Map(x => x.AssignOn);
            Map(x => x.EndDate);
            Map(x => x.IsCompleted);
           
        }
    }
}

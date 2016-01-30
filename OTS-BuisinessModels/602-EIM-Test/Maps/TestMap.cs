using FluentNHibernate.Mapping;
using OTS.DataModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _602_EIM_Test.Maps
{
    public  class TestMap:ClassMap<Test>
    {
        public TestMap()
        {
            Table("Test");
            Id(x => x.Id).GeneratedBy.Identity().Column("TestId");
            Map(x => x.Name);
            Map(x => x.TotalMark);
            Map(x => x.NumberOfQuestion);
            Map(x => x.StartDate);
            Map(x => x.StartTime).Length(50);
            Map(x => x.EndTime).Length(50);
            Map(x => x.IsActivated);
            HasMany(x => x.Questions);
           
        }
    }
}

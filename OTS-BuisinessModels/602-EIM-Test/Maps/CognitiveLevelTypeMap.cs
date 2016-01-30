using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OTS.DataModel;
using FluentNHibernate.Mapping;

namespace _602_EIM_Test.Maps
{
    public class CognitiveLevelTypeMap : ClassMap<CognitiveLevelType> 
    {
        public CognitiveLevelTypeMap() {

            Id(x => x.Id).GeneratedBy.Identity().Column("CognitiveLevel");
            Map(x => x.Name).Length(100).Not.Nullable() ;
            Map(x => x.Description).Length(100);
        }
    }
}

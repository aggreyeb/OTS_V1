using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FluentNHibernate.Mapping;
using OTS.DataModel;

namespace _602_EIM_Test.Maps
{
    public class KnowledgeMapMap:ClassMap<KnowledgeMap>
    {
        public KnowledgeMapMap() {
            Table("KnowledgeMap");
            Id(x => x.Id).GeneratedBy.Identity().Column("KnowledgeMapId") ;
            Map(x => x.Name).Not.Nullable();
            Map(x => x.Description);
            Map(x => x.CreateOn).Not.Nullable();
            Map(x => x.Concepts).Length(65535);
            References(x => x.CreatedBy).Column("CreatedBy");
            Map(x => x.LastUpdated);
            Map(x => x.IsPublic);
        }
    }
}

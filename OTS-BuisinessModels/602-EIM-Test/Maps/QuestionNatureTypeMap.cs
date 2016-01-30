using FluentNHibernate.Mapping;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public  class QuestionNatureTypeMap:ClassMap<QuestionNatureType>
    {
        public QuestionNatureTypeMap()
        {
            Table("QuestionNatureType");
            Id(x => x.Id).GeneratedBy.Identity().Column("QuestionNatureType");
            Map(x => x.Name);
        }
    }
}

using FluentNHibernate.Mapping;
using OTS.DataModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _602_EIM_Test.Maps
{
    public class QuestionTypeMap : ClassMap<QuestionType>
    {
        public QuestionTypeMap()
        {
            Table("QuestionType");
            Id(x => x.Id).GeneratedBy.Identity().Column("QuestionType");
            Map(x => x.Name);
        }
    }
}

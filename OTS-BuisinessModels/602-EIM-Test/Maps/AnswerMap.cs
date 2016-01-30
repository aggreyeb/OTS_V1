using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FluentNHibernate.Mapping;
using OTS.DataModel;

namespace _602_EIM_Test.Maps
{
    public  class AnswerMap:ClassMap<Answer>
    {
        public AnswerMap() {
            Table("Answer");
            Id(x => x.Id).GeneratedBy.Identity().Column("AnswerId");
            Map(x => x.Text);
         
        }
    }
}

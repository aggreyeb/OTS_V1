using FluentNHibernate.Mapping;
using OTS.DataModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _602_EIM_Test.Maps
{
    public class QuestionLineItemMap : ClassMap<QuestionLineItem>
    {
        public  QuestionLineItemMap()
        {
            Id(x => x.Id).GeneratedBy.Identity().Column("QuestionLineItemId");
            Map(x => x.Text);
            Map(x => x.IsCorrect);
        }
    }
}

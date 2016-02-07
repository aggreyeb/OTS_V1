using FluentNHibernate.Mapping;
using OTS.DataModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _602_EIM_Test.Maps
{
    public  class QuestionMap:ClassMap<Question>
    {
        public  QuestionMap()
        {
            Table("Question");
            Id(x => x.Id).GeneratedBy.Identity().Column("QuestionId");
            Map(x => x.Text).Length(100);
            References(x => x.QuestionType).Column("QuestionTypeId");
            References(x => x.QuestionNatureType);
            References(x => x.CognitiveLevelType);
            HasMany(x => x.QuestioLineItems);
            HasMany(x => x.Answers);
            References(x => x.Course).Column("CourseId");
            References(x => x.Test).Column("TestId");
        }
    }
}

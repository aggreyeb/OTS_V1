using FluentNHibernate.Mapping;
using OTS.DataModel;

namespace _602_EIM_Test.Maps
{
    public class TestItemMap:ClassMap<TestItem>
    {
        public TestItemMap()
        {
            Table("TestItem");
            Id(x => x.Id).GeneratedBy.Identity().Column("TestItemId");
            Map(x => x.QuestionBankId);
            Map(x => x.Text).Length(255);
            Map(x => x.Mark);
            References(x => x.CognitiveLevelType).Column("CognitiveLevelTypeId");
            References(x => x.QuestionNatureType).Column("QuestionNatureType");
            References(x => x.QuestionType).Column("QuestionType");
            HasMany(x => x.TestItemOptions).Cascade.AllDeleteOrphan().AsBag();
        }
    }
}
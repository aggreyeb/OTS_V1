using FluentNHibernate.Mapping;
using OTS.DataModel;

namespace _602_EIM_Test.Maps
{
    public class TestItemOptionMap:ClassMap<TestItemOption>
    {
        public TestItemOptionMap()
        {
            Table("TestItemOption");
            Id(x => x.Id).GeneratedBy.Identity().Column("TestItemOptionId");
            Map(x => x.Text);
            Map(x => x.IsCorrect);
        }
    }
}
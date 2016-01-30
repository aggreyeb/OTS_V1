using FluentNHibernate.Mapping;
using OTS.DataModel;

namespace _602_EIM_Test.Maps
{
    public class TestAnswerSheetMap:ClassMap<TestAnswerSheet>
    {
        public TestAnswerSheetMap()
        {
            Table("TestAnswerSheet");
            Id(x => x.Id).GeneratedBy.Identity().Column("TestAnswerSheetId");
            References(x => x.Test).Column("TestId");
            References(x => x.TestItem).Column("TestItemId");
            Map(x => x.LineNumber);
            Map(x => x.A);
            Map(x => x.B);
            Map(x => x.C);
            Map(x => x.D);
        }
    }
}

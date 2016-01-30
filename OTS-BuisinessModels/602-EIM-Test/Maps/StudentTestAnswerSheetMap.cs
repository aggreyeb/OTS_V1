using FluentNHibernate.Mapping;
using OTS.DataModel;

namespace _602_EIM_Test.Maps
{
    public class StudentTestAnswerSheetMap : ClassMap<StudentTestAnswerSheet>
    {
        public StudentTestAnswerSheetMap()
        {
            Table("StudentTestAnswerSheet");
            Id(x => x.StudentTestAnswerSheetId).GeneratedBy.Identity();
            References(x => x.User).Column("StudentId").Not.Nullable();
            References(x => x.TestItem).Column("TestItemId").Not.Nullable();
            References(x => x.Test).Column("TestId").Not.Nullable();
            Map(x => x.A);
            Map(x => x.B);
            Map(x => x.C);
            Map(x => x.D);
            Map(x => x.IsCorrect);
            Map(x => x.TotalCorrectAnswers).Default("0");
        }
    }
}

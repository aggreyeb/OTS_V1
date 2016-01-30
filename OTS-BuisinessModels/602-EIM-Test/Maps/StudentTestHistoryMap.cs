using FluentNHibernate.Mapping;
using OTS.DataModel;

namespace _602_EIM_Test.Maps
{
    public class StudentTestHistoryMap : ClassMap<StudentTestHistory>
    {
        public StudentTestHistoryMap()
        {
            Table("StudentTestHistory");
            Id(x => x.Id).GeneratedBy.Identity().Column("StudentTestHistoryId");
            Map(x => x.TestId);
            Map(x => x.StudentId);
            Map(x => x.StartDate);
            Map(x => x.EndDate);
            Map(x => x.TotalMark);
        }
    }
}
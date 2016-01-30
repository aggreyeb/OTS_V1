using FluentNHibernate.Mapping;
using OTS.DataModel;

namespace _602_EIM_Test.Maps
{
    public class CourseKnowledgeMapMap:ClassMap<CourseKnowledgeMap>
    {
        public CourseKnowledgeMapMap() {
            Table("CourseKnowledgeMap");
            CompositeId()
            .KeyProperty(x => x.CourseId)
            .KeyProperty(x => x.KnowledgeMapId);
            Map(x => x.AssignBy);
            Map(x => x.AssignOn);
            Map(x => x.ActionText);
            Map(x => x.CanEnableSelect);
        }
    }
}

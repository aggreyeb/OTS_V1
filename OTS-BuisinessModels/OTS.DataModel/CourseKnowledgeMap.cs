using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public  class CourseKnowledgeMap
    {
        public virtual  int CourseId{get;set;}
        public virtual int KnowledgeMapId{get;set;}
        public virtual int AssignBy { get; set; }
        public virtual DateTime AssignOn { get; set; }
        public virtual string ActionText { get; set; }
        public virtual bool CanEnableSelect { get; set; }
         
        public override bool Equals(object obj)
        {
            var other = obj as CourseKnowledgeMap;

            if (ReferenceEquals(null, other)) return false;
            if (ReferenceEquals(this, other)) return true;

            return this.CourseId== other.CourseId &&
                this.KnowledgeMapId == other.KnowledgeMapId;
        }

        public override int GetHashCode()
        {
            unchecked
            {
                int hash = GetType().GetHashCode();
                hash = (hash * 31) ^ this.CourseId.GetHashCode();
                hash = (hash * 31) ^ this.KnowledgeMapId.GetHashCode();

                return hash;
            }
        }
    }
}

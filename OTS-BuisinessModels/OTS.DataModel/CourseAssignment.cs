using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public   class CourseAssignment
    {
        public virtual int TeacherId { get; set; }
        public virtual AcademicCourse CourseId { get; set; }
        public virtual DateTime AssignOn { get; set; }
        public virtual DateTime EndDate { get; set; }
        public virtual bool IsCompleted { get; set; }
     

        public override bool Equals(object obj)
        {
            var other = obj as CourseAssignment;

            if (ReferenceEquals(null, other)) return false;
            if (ReferenceEquals(this, other)) return true;

            return this.TeacherId == other.TeacherId &&
                this.CourseId == other.CourseId;
        }

        public override int GetHashCode()
        {
            unchecked
            {
                int hash = GetType().GetHashCode();
                hash = (hash * 31) ^ this.TeacherId.GetHashCode();
                hash = (hash * 31) ^ this.CourseId.GetHashCode();

                return hash;
            }
        }
    }
}

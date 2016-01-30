using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    /// <summary>
    /// Student Course Registration
    /// </summary>
    public class StudentCourseRegistration:Record 
    {
        public virtual User StudentId { get; set; }
        public virtual  AcademicCourse CourseId { get; set; }
        public virtual DateTime Date { get; set; }
      
      
    
        public override bool Equals(object obj)
        {
            var other = obj as StudentCourseRegistration;

            if (ReferenceEquals(null, other)) return false;
            if (ReferenceEquals(this, other)) return true;

            return this.StudentId == other.StudentId  &&
                this.CourseId  == other.CourseId ;
        }

        public override int GetHashCode()
        {
            unchecked
            {
                int hash = GetType().GetHashCode();
                hash = (hash * 31) ^ this.StudentId.GetHashCode();
                hash = (hash * 31) ^ this.CourseId.GetHashCode();

                return hash;
            }
        }
    }
}

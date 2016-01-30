using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public class StudentTest
    {
        public virtual int StudentTestId { get; set; }
        public virtual User Student { get; set; }
        public virtual AcademicCourse Course { get; set; }
        public virtual Test Test { get; set; }
        public virtual DateTime DateCompleted { get; set; }
        public virtual bool IsTestCompleted { get; set; }
        public virtual float Mark { get; set; }
        public virtual string Grade { get; set; }
    }
}

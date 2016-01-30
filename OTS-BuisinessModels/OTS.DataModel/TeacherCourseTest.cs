using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public   class TeacherCourseTest:Record 
    {
   
        public virtual  AcademicCourse Course { get; set; }
        public virtual  Test Test { get; set; }
        public virtual  DateTime CreatedOn { get; set; }
        public virtual User CreatedBy { get; set; }
    }
}

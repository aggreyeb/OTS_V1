using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public class TestItem:Record
    {
        public virtual int QuestionBankId { get; set; }
        public virtual string Text { get; set; }
        public virtual float Mark { get; set; }
        public virtual QuestionNatureType QuestionNatureType { get; set; }
        public virtual QuestionType QuestionType { get; set; }
        public virtual CognitiveLevelType CognitiveLevelType { get; set; }
        public virtual List<TestItemOption> TestItemOptions { get; set; }

        public virtual AcademicCourse Course { get; set; }
    }
}

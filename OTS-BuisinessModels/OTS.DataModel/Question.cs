using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public class Question:Record 
    {
      
        public virtual string Text { get; set; }
        public virtual float Mark { get; set; }
        public virtual QuestionNatureType QuestionNatureType { get; set; }
        public virtual QuestionType QuestionType { get; set; }
        public virtual CognitiveLevelType CognitiveLevelType { get; set; }
        public virtual List<QuestionLineItem> QuestioLineItems { get; set; }
        public virtual List<Answer> Answers { get; set; }
        public virtual AcademicCourse Course { get; set; }

    }
}
